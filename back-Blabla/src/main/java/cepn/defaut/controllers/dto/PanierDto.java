package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PanierDto {
    private String id;
    private String commandeId;
    private String produitId;
    private String produitNom;
    private String produitImageUrl;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal sousTotal;
}

