package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IngredientDto {
    private String id;
    private String nom;
    private String description;
    private boolean allergene;
}
