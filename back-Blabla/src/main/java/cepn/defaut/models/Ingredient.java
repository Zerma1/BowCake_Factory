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
@Getter
@Setter(AccessLevel.PROTECTED)
public class Ingredient extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "allergene", nullable = false)
    private boolean allergene = false;

    @ManyToMany(mappedBy = "ingredients")
    @Builder.Default
    private Set<Recette> recettes = new HashSet<>();
}