package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(name = "uk__tags_id", columnNames = {"id"}))
@ToString(of = {"nom"}, callSuper = true)
@EqualsAndHashCode(of = {"nom"}, callSuper = true)
@NoArgsConstructor
public class Tags extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "nom ne doit pas etre null")
    //JPA
    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable(
            name = "ingredients_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    private Set<Produit> produits = new HashSet<>();
}
