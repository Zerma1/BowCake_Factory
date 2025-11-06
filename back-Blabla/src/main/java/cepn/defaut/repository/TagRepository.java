package cepn.defaut.repository;

import cepn.defaut.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * Trouve un tag par son nom
     * @param nom le nom du tag
     * @return Optional contenant le tag si trouvé
     */
    Optional<Tag> findByNom(String nom);

    /**
     * Trouve tous les tags dont le nom contient la chaîne donnée (insensible à la casse)
     * @param nom partie du nom à rechercher
     * @return liste des tags correspondants
     */
    List<Tag> findByNomContainingIgnoreCase(String nom);

    /**
     * Vérifie si un tag existe par son nom
     * @param nom le nom du tag
     * @return true si le tag existe
     */
    boolean existsByNom(String nom);
}