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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Récupère tous les utilisateurs
     */
    @Transactional(readOnly = true)
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son ID
     */
    @Transactional(readOnly = true)
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
    }

    /**
     * Récupère un utilisateur par son username
     */
    @Transactional(readOnly = true)
    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    /**
     * Récupère un utilisateur par son email
     */
    @Transactional(readOnly = true)
    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    /**
     * Crée un nouvel utilisateur
     */
    public Utilisateur create(Utilisateur utilisateur, Set<String> roleNames) {
        if (utilisateur.getId() != null) {
            throw new IllegalArgumentException("Un nouvel utilisateur ne peut pas avoir d'ID");
        }

        // Vérifier que le username n'existe pas déjà
        if (utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà");
        }

        // Vérifier que l'email n'existe pas déjà
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'adresse email existe déjà");
        }

        // Encoder le mot de passe
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        // Assigner les rôles
        if (roleNames != null && !roleNames.isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                Role role = roleRepository.findByNom(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));
                roles.add(role);
            }
            utilisateur.getRoles().addAll(roles);
        } else {
            // Rôle par défaut: USER
            Role userRole = roleRepository.findByNom("USER")
                    .orElseThrow(() -> new EntityNotFoundException("Rôle USER non trouvé"));
            utilisateur.getRoles().add(userRole);
        }

        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Met à jour un utilisateur existant
     */
    public Utilisateur update(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = findById(id);

        // Vérifier que le nouveau username n'est pas déjà utilisé par un autre utilisateur
        if (!utilisateur.getUsername().equals(utilisateurDetails.getUsername()) &&
                utilisateurRepository.existsByUsername(utilisateurDetails.getUsername())) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà");
        }

        // Vérifier que le nouvel email n'est pas déjà utilisé par un autre utilisateur
        if (!utilisateur.getEmail().equals(utilisateurDetails.getEmail()) &&
                utilisateurRepository.existsByEmail(utilisateurDetails.getEmail())) {
            throw new IllegalArgumentException("L'adresse email existe déjà");
        }

        utilisateur.setUsername(utilisateurDetails.getUsername());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setPrenom(utilisateurDetails.getPrenom());
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setActif(utilisateurDetails.isActif());

        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Change le mot de passe d'un utilisateur
     */
    public void changePassword(Long id, String oldPassword, String newPassword) {
        Utilisateur utilisateur = findById(id);

        if (!passwordEncoder.matches(oldPassword, utilisateur.getPassword())) {
            throw new IllegalArgumentException("L'ancien mot de passe est incorrect");
        }

        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }

    /**
     * Réinitialise le mot de passe d'un utilisateur (par admin)
     */
    public void resetPassword(Long id, String newPassword) {
        Utilisateur utilisateur = findById(id);
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }

    /**
     * Supprime un utilisateur
     */
    public void delete(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
        utilisateurRepository.deleteById(id);
    }

    /**
     * Ajoute un rôle à un utilisateur
     */
    public Utilisateur addRole(Long userId, String roleName) {
        Utilisateur utilisateur = findById(userId);
        Role role = roleRepository.findByNom(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));

        utilisateur.getRoles().add(role);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Retire un rôle à un utilisateur
     */
    public Utilisateur removeRole(Long userId, String roleName) {
        Utilisateur utilisateur = findById(userId);
        Role role = roleRepository.findByNom(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé: " + roleName));

        utilisateur.getRoles().remove(role);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Recherche des utilisateurs par nom ou prénom
     */
    @Transactional(readOnly = true)
    public List<Utilisateur> searchByName(String searchTerm) {
        return utilisateurRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
                searchTerm, searchTerm);
    }
}