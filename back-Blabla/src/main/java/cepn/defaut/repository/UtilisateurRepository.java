package cepn.defaut.repository;

import cepn.defaut.models.Role;
import cepn.defaut.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends GeneralRepository<Utilisateur, Long> {

    /**
     * Trouve un utilisateur par son username
     */
    Optional<Utilisateur> findByUsername(String username);

    /**
     * Trouve un utilisateur par son email
     */
    Optional<Utilisateur> findByEmail(String email);

    /**
     * Vérifie si un utilisateur existe par son username
     */
    boolean existsByUsername(String username);

    /**
     * Vérifie si un utilisateur existe par son email
     */
    boolean existsByEmail(String email);

    /**
     * Trouve tous les utilisateurs actifs
     */
    List<Utilisateur> findByActifTrue();

    /**
     * Trouve tous les utilisateurs inactifs
     */
    List<Utilisateur> findByActifFalse();

    /**
     * Trouve les utilisateurs par rôle
     */
    @Query("SELECT u FROM Utilisateur u JOIN u.roles r WHERE r = :role")
    List<Utilisateur> findByRole(@Param("role") Role role);

    /**
     * Recherche par nom ou prénom
     */
    List<Utilisateur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
            String nom, String prenom
    );

    /**
     * Compte le nombre d'utilisateurs actifs
     */
    Long countByActifTrue();
}