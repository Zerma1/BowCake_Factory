package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Table(name = "commande", uniqueConstraints = @UniqueConstraint(name = "uk__commande_id", columnNames = {"id"}))
@ToString(of = {"uk__commande_id","fk_satut_commande", "fk_user"}, callSuper = true)
@EqualsAndHashCode(of = {"uk__commande_id","fk_satut_commande", "fk_user"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

@Getter
@Setter(value = AccessLevel.PRIVATE)
public class Commande extends AbstractPersistable<Long> {

    //LBK
    @NonNull
    //BV
    @NotNull(message = "date_commande ne doit pas etre null")
    @PastOrPresent
    //JPA
    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande;

    //BV
    @PastOrPresent
    //JPA
    @Column(name = "date_livraison", nullable = false)
    private LocalDate dateLivraison;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "prix_facture ne doit pas etre null")
    //JPA
    @Column(name = "prix_facture", nullable = false)
    private Integer prixFacture;

    /* #region forgoing key */

    @NonNull
    @OneToOne
    @JoinColumn(name = "fk_satut_commande", nullable = false)
    private String fkSatutCommande;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private String fkUser;

    /* #endregion forgoing key */

}
