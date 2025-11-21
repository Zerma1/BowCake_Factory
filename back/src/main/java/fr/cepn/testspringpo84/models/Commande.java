package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Table(name = "commande", uniqueConstraints = @UniqueConstraint(name = "uk__commande_id", columnNames = {"id"}))
@ToString(of = {"fkSatutCommande", "fkUtilisateur","dateCommande","prixFacture"}, callSuper = true)
@EqualsAndHashCode(of = {}, callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class Commande extends AbstractPersistable<Long> {

    //LBK
    @NonNull
    //BV
    @NotNull(message = "date_commande ne doit pas etre null")
    @PastOrPresent
    //JPA
    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande = LocalDate.now();

    //BV
    @PastOrPresent
    //JPA
    @Column(name = "date_livraison", nullable = false)
    private LocalDate dateLivraison;

    //LBK
    @NonNull
    @Min(0)
    @Column(name = "prix_facture", nullable = false)
    private Integer prixFacture = 0; //peut etre calculer dans le fronte mais in finer doit etre calculer dans le back

    /* #region forgoing key */

    @NonNull
    @OneToOne
    @JoinColumn(name = "fk_satut_commande", nullable = false)
    private StatutCommande fkSatutCommande;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_utilisateur", nullable = false)
    private Utilisateur fkUtilisateur;

    /* #endregion forgoing key */

}
