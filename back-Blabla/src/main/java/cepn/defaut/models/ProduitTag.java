package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produit_tag",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_produit_tag",
                columnNames = {"produit_id", "tag_id"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitTag extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}