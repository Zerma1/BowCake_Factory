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
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Recette extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 100)
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "temps_preparation")
    private Integer tempsPreparation; // en minutes

    @Column(name = "temps_cuisson")
    private Integer tempsCuisson; // en minutes

    @Column(name = "portions")
    private Integer portions;

    @Length(max = 255)
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "recette_ingredient",
            joinColumns = @JoinColumn(name = "recette_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Builder.Default
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "recette")
    @Builder.Default
    private Set<Produit> produits = new HashSet<>();
}