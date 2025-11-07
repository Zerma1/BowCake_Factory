package cepn.defaut.services;

import cepn.defaut.models.Ingredient;
import cepn.defaut.models.Recette;
import cepn.defaut.repository.IngredientRepository;
import cepn.defaut.repository.RecetteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class RecetteService {

    private final RecetteRepository recetteRepository;
    private final IngredientRepository ingredientRepository;

    /**
     * Récupère toutes les recettes
     */
    @Transactional(readOnly = true)
    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }

    /**
     * Récupère une recette par son ID
     */
    @Transactional(readOnly = true)
    public Recette findById(Long id) {
        return recetteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recette non trouvée avec l'ID: " + id));
    }

    /**
     * Recherche des recettes par nom
     */
    @Transactional(readOnly = true)
    public List<Recette> searchByNom(String nom) {
        return recetteRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Récupère les recettes contenant un ingrédient spécifique
     */
    @Transactional(readOnly = true)
    public List<Recette> findByIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé"));
        return recetteRepository.findByIngredientsContaining(ingredient);
    }

    /**
     * Récupère les recettes selon le temps de préparation
     */
    @Transactional(readOnly = true)
    public List<Recette> findByMaxPreparationTime(Integer maxMinutes) {
        return recetteRepository.findByTempsPreparationLessThanEqual(maxMinutes);
    }

    /**
     * Crée une nouvelle recette
     */
    public Recette create(Recette recette, Set<Long> ingredientIds) {
        if (recette.getId() != null) {
            throw new IllegalArgumentException("Une nouvelle recette ne peut pas avoir d'ID");
        }

        // Ajouter les ingrédients
        if (ingredientIds != null && !ingredientIds.isEmpty()) {
            for (Long ingredientId : ingredientIds) {
                Ingredient ingredient = ingredientRepository.findById(ingredientId)
                        .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé: " + ingredientId));
                recette.getIngredients().add(ingredient);
            }
        }

        return recetteRepository.save(recette);
    }

    /**
     * Met à jour une recette existante
     */
    public Recette update(Long id, Recette recetteDetails) {
        Recette recette = findById(id);

        recette.setNom(recetteDetails.getNom());
        recette.setDescription(recetteDetails.getDescription());
        recette.setInstructions(recetteDetails.getInstructions());
        recette.setTempsPreparation(recetteDetails.getTempsPreparation());
        recette.setTempsCuisson(recetteDetails.getTempsCuisson());
        recette.setPortions(recetteDetails.getPortions());
        recette.setImageUrl(recetteDetails.getImageUrl());

        return recetteRepository.save(recette);
    }

    /**
     * Supprime une recette
     */
    public void delete(Long id) {
        if (!recetteRepository.existsById(id)) {
            throw new EntityNotFoundException("Recette non trouvée avec l'ID: " + id);
        }
        recetteRepository.deleteById(id);
    }

    /**
     * Ajoute un ingrédient à une recette
     */
    public Recette addIngredient(Long recetteId, Long ingredientId) {
        Recette recette = findById(recetteId);
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé"));

        recette.getIngredients().add(ingredient);
        return recetteRepository.save(recette);
    }

    /**
     * Retire un ingrédient d'une recette
     */
    public Recette removeIngredient(Long recetteId, Long ingredientId) {
        Recette recette = findById(recetteId);
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé"));

        recette.getIngredients().remove(ingredient);
        return recetteRepository.save(recette);
    }

    /**
     * Calcule le temps total (préparation + cuisson)
     */
    @Transactional(readOnly = true)
    public Integer calculateTotalTime(Long recetteId) {
        Recette recette = findById(recetteId);
        int total = 0;

        if (recette.getTempsPreparation() != null) {
            total += recette.getTempsPreparation();
        }

        if (recette.getTempsCuisson() != null) {
            total += recette.getTempsCuisson();
        }

        return total;
    }
}