package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produit", uniqueConstraints = @UniqueConstraint(name = "pk_produit", columnNames = {"pk_produit"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Produit extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 100)
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NonNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "prix_ht", nullable = false, precision = 10, scale = 2)
    private BigDecimal prixHT;

    @NonNull
    @DecimalMin(value = "0.0")
    @Column(name = "tva", nullable = false, precision = 5, scale = 2)
    private BigDecimal tva;

    @NonNull
    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock = 0;

    @Length(max = 255)
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "type_produit_id", nullable = false)
    private TypeProduit typeProduit;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "etat_produit_id", nullable = false)
    private EtatProduit etatProduit;

    @ManyToOne
    @JoinColumn(name = "recette_id")
    private Recette recette;

    @ManyToMany
    @JoinTable(
            name = "produit_tag",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    @Transient
    public BigDecimal getPrixTTC() {
        return prixHT.multiply(BigDecimal.ONE.add(tva));
    }
}