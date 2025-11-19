package fr.cepn.testspringpo84.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "ingredient", uniqueConstraints = @UniqueConstraint(name = "uk__ingredient_id", columnNames = {"id"}))
@ToString(of = {"nom", "prix"}, callSuper = true)
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class IngredientsTag {

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
    @Min(0)
    private Integer quantite;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "prix ne doit pas etre null")
    //JPA
    @Column(name = "prix", nullable = false)
    @Min(0)
    private Integer prix;
}
