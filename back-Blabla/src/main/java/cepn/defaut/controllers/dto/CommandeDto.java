package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommandeDto {
    private String id;
    private String numeroCommande;
    private String utilisateurId;
    private String utilisateurNom;
    private LocalDateTime dateCommande;
    private BigDecimal montantTotal;
    private String statutId;
    private String statutNom;
    private String adresseLivraison;
    private String ville;
    private String codePostal;
    private String pays;
    private String commentaire;
    private List<PanierDto> items;
    private Integer nombreItems;
}
