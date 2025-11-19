package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingrediant", uniqueConstraints = @UniqueConstraint(name = "uk__ingrediant_id", columnNames = {"id"}))
@ToString(of = {"nom", "quantite","prix"}, callSuper = true)
@EqualsAndHashCode(of = {"id","nom", "quantite","prix"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder //ne comprend pas trop //TODO : demander PM
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class Ingredient extends AbstractPersistable<Long> {

    //LBK
    @NonNull
    //BV
    @NotNull(message = "nom ne doit pas etre null")
    //JPA
    @Column(name = "nom", nullable = false)
    private String nom;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "quantite ne doit pas etre null")
    //JPA
    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "prix ne doit pas etre null")
    //JPA
    @Column(name = "prix", nullable = false)
    private Integer prix;

    /* #region many to many */
        //TODO: verifier avec le PM:
                            //// ❌ Recette n'a pas de champ "ingredients" (c'est une table de jointure avec EmbeddedId)
                            //// ⚠️ La modélisation semble incorrecte : Recette devrait être une vraie entité avec des relations
        @ManyToMany(mappedBy = "ingredients")
        private Set<Tags> tags = new HashSet<>();

        @ManyToMany(mappedBy = "ingredients")
        //@Builder.Default
        private Set<Recette> recettes = new HashSet<>();

    /* #endregion many to many */
}
