package cepn.defaut.services;

import cepn.defaut.models.Role;
import cepn.defaut.models.Utilisateur;
import cepn.defaut.repository.RoleRepository;
import cepn.defaut.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService {

    /* #region Dépendances */
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    /* #endregion Dépendances */

    /* #region Récupération des utilisateurs */
    @Transactional(readOnly = true)
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    /* #endregion Récupération des utilisateurs */

    /* #region Création et mise à jour */
    public Utilisateur create(Utilisateur utilisateur, Set<String> roleNames) {
        if (utilisateur.getId() != null) {
            throw new IllegalArgumentException("Un nouvel utilisateur ne peut pas avoir d'ID");
        }

        if (utilisateurRepository.existsByUsername(utilisateur.getUserName())) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà");
        }

        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'adresse email existe déjà");
        }

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        if (roleNames != null && !roleNames.isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                Role role = roleRepository.findByNom(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));
                roles.add(role);
            }
            utilisateur.getRoles().addAll(roles);
        } else {
            Role userRole = roleRepository.findByNom("USER")
                    .orElseThrow(() -> new EntityNotFoundException("Rôle USER non trouvé"));
            utilisateur.getRoles().add(userRole);
        }

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur update(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = findById(id);

        if (!utilisateur.getUserName().equals(utilisateurDetails.getUserName()) &&
                utilisateurRepository.existsByUsername(utilisateurDetails.getUserName())) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà");
        }

        if (!utilisateur.getEmail().equals(utilisateurDetails.getEmail()) &&
                utilisateurRepository.existsByEmail(utilisateurDetails.getEmail())) {
            throw new IllegalArgumentException("L'adresse email existe déjà");
        }

        utilisateur.setUserName(utilisateurDetails.getUserName());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setPrenom(utilisateurDetails.getPrenom());
        utilisateur.setNom(utilisateurDetails.getNom());

        return utilisateurRepository.save(utilisateur);
    }
    /* #endregion Création et mise à jour */

    /* #region Gestion des mots de passe */
    public void changePassword(Long id, String oldPassword, String newPassword) {
        Utilisateur utilisateur = findById(id);

        if (!passwordEncoder.matches(oldPassword, utilisateur.getPassword())) {
            throw new IllegalArgumentException("L'ancien mot de passe est incorrect");
        }

        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }

    public void resetPassword(Long id, String newPassword) {
        Utilisateur utilisateur = findById(id);
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }
    /* #endregion Gestion des mots de passe */

    /* #region Suppression */
    public void delete(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
        utilisateurRepository.deleteById(id);
    }
    /* #endregion Suppression */

    /* #region Gestion des rôles */
    public Utilisateur addRole(Long userId, String roleName) {
        Utilisateur utilisateur = findById(userId);
        Role role = roleRepository.findByNom(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));

        utilisateur.getRoles().add(role);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur removeRole(Long userId, String roleName) {
        Utilisateur utilisateur = findById(userId);
        Role role = roleRepository.findByNom(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));

        utilisateur.getRoles().remove(role);
        return utilisateurRepository.save(utilisateur);
    }
    /* #endregion Gestion des rôles */

    /* #region Recherche */
    @Transactional(readOnly = true)
    public List<Utilisateur> searchByName(String searchTerm) {
        return utilisateurRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
                searchTerm, searchTerm);
    }
    /* #endregion Recherche */
}

