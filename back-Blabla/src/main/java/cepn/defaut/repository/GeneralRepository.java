package cepn.defaut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Repository générique avec les méthodes communes
 * à toutes les entités ayant un champ 'nom'.
 */
@NoRepositoryBean // <- Empêche Spring de l’instancier directement
public interface GeneralRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * Trouve une entité par son nom.
     * @param nom le nom à rechercher
     * @return Optional contenant l'entité si trouvée
     */
    Optional<T> findByNom(String nom);

    /**
     * Trouve toutes les entités dont le nom contient une chaîne donnée, insensible à la casse.
     * @param nom partie du nom à rechercher
     * @return liste des entités correspondantes
     */
    List<T> findByNomContainingIgnoreCase(String nom);

    /**
     * Vérifie si une entité existe par son nom.
     * @param nom le nom à vérifier
     * @return true si l'entité existe
     */
    boolean existsByNom(String nom);
}
