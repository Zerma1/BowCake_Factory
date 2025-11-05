package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredient_tag",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_ingredient_tag",
                columnNames = {"ingredient_id", "tag_id"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
public class IngredientTag extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}