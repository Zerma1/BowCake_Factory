package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "produit", uniqueConstraints = @UniqueConstraint(name = "uk__produit_id", columnNames = {"id"}))
@ToString(of = {"nom"}, callSuper = true)
@EqualsAndHashCode(of = {"id","nom"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Produit extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "nom ne doit pas etre null")
    //JPA
    @Column(name = "nom", nullable = false)
    private String nom;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "quantite ne doit pas etre null")
    //JPA
    @Column(name = "quantite", nullable = false)
    @Min(0)
    private Integer quantite;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "prix ne doit pas etre null")
    //JPA
    @Column(name = "prix", nullable = false)
    @Min(0)
    private Integer prix;

//    //LBK
//    @Getter
//    @Setter(value = AccessLevel.PRIVATE)
//    @NonNull
//    //BV
//    @NotNull(message = "etat produit ne doit pas etre null")
//    //JPA
//    @Column(name = "pk_etat_produit", nullable = false)
//    private String pkEtatProduit;
}
