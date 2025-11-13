package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EtatProduitDto {
    private String id;
    private String etat;
    private boolean limite;
    private LocalDate dateFin;
    private boolean actif; // Calculé : si limite=true et dateFin >= aujourd'hui
}
