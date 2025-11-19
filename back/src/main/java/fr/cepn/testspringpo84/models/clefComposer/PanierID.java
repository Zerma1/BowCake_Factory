package fr.cepn.testspringpo84.models.clefComposer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"fk_produit", "fk_commande"})
@Embeddable
public class PanierID implements Serializable {

    @Column(name = "fk_produit")
    public Long fk_produit;

    @Column(name = "fk_commande")
    public Long fk_commande;
}
