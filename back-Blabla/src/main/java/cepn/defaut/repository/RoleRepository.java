package cepn.defaut.repository;

import cepn.defaut.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Trouve un rôle par son nom
     * @param nom le nom du rôle
     * @return Optional contenant le rôle si trouvé
     */
    Optional<Role> findByNom(String nom);

    /**
     * Vérifie si un rôle existe par son nom
     * @param nom le nom du rôle
     * @return true si le rôle existe
     */
    boolean existsByNom(String nom);
}