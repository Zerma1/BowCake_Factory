package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "panier", uniqueConstraints = @UniqueConstraint(name = "pk_panier", columnNames = {"pk_panier"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Panier extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Min(1)
    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @DecimalMin(value = "0.0")
    @Column(name = "prix_unitaire", nullable = false, precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Transient
    public BigDecimal getSousTotal() {
        return prixUnitaire.multiply(BigDecimal.valueOf(quantite));
    }

    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (prixUnitaire == null && produit != null) {
            prixUnitaire = produit.getPrixTTC();
        }
    }
}