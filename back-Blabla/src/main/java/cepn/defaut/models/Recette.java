package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recette", uniqueConstraints = @UniqueConstraint(name = "pk_recette", columnNames = {"pk_recette"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recette extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 100)
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "temps_preparation")
    private Integer tempsPreparation; // en minutes

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "temps_cuisson")
    private Integer tempsCuisson; // en minutes

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "portions")
    private Integer portions;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 255)
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @ManyToMany
    @JoinTable(
            name = "recette_ingredient",
            joinColumns = @JoinColumn(name = "recette_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Builder.Default
    private Set<Ingredient> ingredients = new HashSet<>();

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @OneToMany(mappedBy = "recette")
    @Builder.Default
    private Set<Produit> produits = new HashSet<>();
}