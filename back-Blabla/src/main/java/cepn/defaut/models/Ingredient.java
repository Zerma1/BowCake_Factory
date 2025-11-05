package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient", uniqueConstraints = @UniqueConstraint(name = "pk_ingredient", columnNames = {"pk_ingredient"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "allergene", nullable = false)
    private boolean allergene = false;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "ingredients")
    @Builder.Default
    private Set<Recette> recettes = new HashSet<>();
}