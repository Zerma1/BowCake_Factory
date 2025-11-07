package cepn.defaut.repository;

import cepn.defaut.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends GeneralRepository<Ingredient, Long> {

}