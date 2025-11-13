package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class RecetteDto {
    private String id;
    private String nom;
    private String description;
    private String instructions;
    private Integer tempsPreparation;
    private Integer tempsCuisson;
    private Integer tempsTotal;
    private Integer portions;
    private String imageUrl;
    private Set<IngredientDto> ingredients;
    private boolean contientAllergenes;
}
