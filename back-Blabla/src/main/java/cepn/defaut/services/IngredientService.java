package cepn.defaut.services;

import cepn.defaut.models.Ingredient;
import cepn.defaut.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    /**
     * Récupère tous les ingrédients
     */
    @Transactional(readOnly = true)
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    /**
     * Récupère un ingrédient par son ID
     */
    @Transactional(readOnly = true)
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé avec l'ID: " + id));
    }

    /**
     * Récupère un ingrédient par son nom
     */
    @Transactional(readOnly = true)
    public Ingredient findByNom(String nom) {
        return ingredientRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException("Ingrédient non trouvé: " + nom));
    }

    /**
     * Recherche des ingrédients par nom
     */
    @Transactional(readOnly = true)
    public List<Ingredient> searchByNom(String nom) {
        return ingredientRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Crée un nouvel ingrédient
     */
    public Ingredient create(Ingredient ingredient) {
        if (ingredient.getId() != null) {
            throw new IllegalArgumentException("Un nouvel ingrédient ne peut pas avoir d'ID");
        }

        // Vérifier que le nom n'existe pas déjà
        if (ingredientRepository.existsByNom(ingredient.getNom())) {
            throw new IllegalArgumentException("Un ingrédient avec ce nom existe déjà");
        }

        return ingredientRepository.save(ingredient);
    }

    /**
     * Met à jour un ingrédient existant
     */
    public Ingredient update(Long id, Ingredient ingredientDetails) {
        Ingredient ingredient = findById(id);

        // Vérifier que le nouveau nom n'est pas déjà utilisé par un autre ingrédient
        if (!ingredient.getNom().equals(ingredientDetails.getNom()) &&
                ingredientRepository.existsByNom(ingredientDetails.getNom())) {
            throw new IllegalArgumentException("Un ingrédient avec ce nom existe déjà");
        }

        ingredient.setNom(ingredientDetails.getNom());
        ingredient.setDescription(ingredientDetails.getDescription());
        ingredient.setAllergene(ingredientDetails.isAllergene());

        return ingredientRepository.save(ingredient);
    }

    /**
     * Supprime un ingrédient
     */
    public void delete(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingrédient non trouvé avec l'ID: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    /**
     * Marque un ingrédient comme allergène
     */
    public Ingredient markAsAllergene(Long id) {
        Ingredient ingredient = findById(id);
        ingredient.setAllergene(true);
        return ingredientRepository.save(ingredient);
    }

    /**
     * Retire la marque allergène d'un ingrédient
     */
    public Ingredient unmarkAsAllergene(Long id) {
        Ingredient ingredient = findById(id);
        ingredient.setAllergene(false);
        return ingredientRepository.save(ingredient);
    }
}