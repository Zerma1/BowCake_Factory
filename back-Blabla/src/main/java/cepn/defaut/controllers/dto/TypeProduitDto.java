package cepn.defaut.controllers.dto;

import cepn.defaut.models.Produit;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class TypeProduitDto {
    private String id;
    private String nom;
    private String description;
    private Set<Produit> produits;
}
