package fr.cepn.testspringpo84.models;

import fr.cepn.testspringpo84.models.clefComposer.RecetteID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PUBLIC)
@Entity
public class Panier {

    @EmbeddedId
    private RecetteID id;

    @NonNull
    @Min(0)
    @Column(name = "quantite")
    private String quantite;
}
