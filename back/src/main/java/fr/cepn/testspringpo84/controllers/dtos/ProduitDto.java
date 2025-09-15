package fr.cepn.testspringpo84.controllers.dtos;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProduitDto {
    private Long id;
    private String nom;
    private Integer quantite;
    private Integer prix;
//    private String pkEtatProduit;
}