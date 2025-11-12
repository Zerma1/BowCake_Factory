package cepn.defaut.services;

import cepn.defaut.models.Role;
import cepn.defaut.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public Role findByNom(String nom) {
        return roleRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + nom));
    }

    public Role create(Role role) {
        if (role.getId() != null) {
            throw new IllegalArgumentException("Un nouveau rôle ne peut pas avoir d'ID");
        }
        if (roleRepository.existsByNom(role.getNom())) {
            throw new IllegalArgumentException("Un rôle avec ce nom existe déjà");
        }
        return roleRepository.save(role);
    }

    public Role update(Long id, Role roleDetails) {
        Role role = findById(id);
        if (!role.getNom().equals(roleDetails.getNom()) &&
                roleRepository.existsByNom(roleDetails.getNom())) {
            throw new IllegalArgumentException("Un rôle avec ce nom existe déjà");
        }
        role.setNom(roleDetails.getNom());
        role.setDescription(roleDetails.getDescription());
        return roleRepository.save(role);
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Rôle non trouvé avec l'ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}