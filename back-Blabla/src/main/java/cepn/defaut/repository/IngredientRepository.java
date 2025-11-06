package cepn.defaut.repository;

import cepn.defaut.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /**
     * Trouve un ingrédient par son nom
     * @param nom le nom de l'ingrédient
     * @return Optional contenant l'ingrédient si trouvé
     */
    Optional<Ingredient> findByNom(String nom);

    /**
     * Trouve tous les ingrédients dont le nom contient la chaîne donnée (insensible à la casse)
     * @param nom partie du nom à rechercher
     * @return liste des ingrédients correspondants
     */
    List<Ingredient> findByNomContainingIgnoreCase(String nom);

    /**
     * Vérifie si un ingrédient existe par son nom
     * @param nom le nom de l'ingrédient
     * @return true si l'ingrédient existe
     */
    boolean existsByNom(String nom);
}