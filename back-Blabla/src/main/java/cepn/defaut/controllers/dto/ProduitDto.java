package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
public class ProduitDto {
    private String id;
    private String nom;
    private String description;
    private BigDecimal prixHT;
    private BigDecimal prixTTC;
    private BigDecimal tva;
    private Integer stock;
    private String imageUrl;
    private String typeProduitId;
    private String typeProduitNom;
    private String etatProduitId;
    private String etatProduitNom;
    private String recetteId;
    private String recetteNom;
    private Set<String> tagIds;
    private Set<String> tagNoms;
    private boolean disponible;
}
