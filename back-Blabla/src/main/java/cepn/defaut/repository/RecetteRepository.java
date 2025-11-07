package cepn.defaut.repository;

import cepn.defaut.models.Ingredient;
import cepn.defaut.models.Recette;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetteRepository extends GeneralRepository<Recette, Long> {

    /**
     * Trouve les recettes contenant un ingrédient spécifique.
     * Utilise la relation ManyToMany entre Recette et Ingredient.
     *
     * @param ingredient l’ingrédient recherché
     * @return liste des recettes contenant cet ingrédient
     */
    List<Recette> findByIngredientsContaining(Ingredient ingredient);

    /**
     * Trouve les recettes dont le temps de préparation est inférieur ou égal à une valeur donnée.
     *
     * @param maxTemps temps de préparation maximum
     * @return liste des recettes correspondantes
     */
    List<Recette> findByTempsPreparationLessThanEqual(Integer maxTemps);
}
