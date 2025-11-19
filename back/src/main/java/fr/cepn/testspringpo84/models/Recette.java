package fr.cepn.testspringpo84.models;

import fr.cepn.testspringpo84.models.clefComposer.RecetteID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "recette", uniqueConstraints = @UniqueConstraint(name = "pk_recette", columnNames = {"pk_recette"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PUBLIC)
public class Recette {

    @EmbeddedId
    private RecetteID id;

    @NonNull
    @Min(0)
    @Column(name = "quantite")
    private Integer quantite;

}
