package fr.cepn.testspringpo84.models.clefComposer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = {"fk_ingredient","fk_produit"})
@Embeddable
public class RecetteID implements Serializable {

    @Column(name = "fk_ingredient")
    private String fk_ingredient;

    @Column(name = "fk_produit")
    private String fk_produit;

}
