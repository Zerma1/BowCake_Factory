package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Table(name = "commande", uniqueConstraints = @UniqueConstraint(name = "uk__commande_id", columnNames = {"id"}))
@ToString(of = {"fk_satut_commande", "fk_user"}, callSuper = true)
@EqualsAndHashCode(of = {"fk_satut_commande", "fk_user"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commande extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "fk_satut_commande ne doit pas etre null")
    //JPA
    @Column(name = "fk_satut_commande", nullable = false)
    private String fkSatutCommande;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "fk_user ne doit pas etre null")
    //JPA
    @Column(name = "fk_user", nullable = false)
    private String fkUser;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "date_commande ne doit pas etre null")
    @PastOrPresent
    //JPA
    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    //BV
    @PastOrPresent
    //JPA
    @Column(name = "date_livraison", nullable = false)
    private LocalDate dateLivraison;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "fk_user ne doit pas etre null")
    //JPA
    @Column(name = "fk_user", nullable = false)
    private Integer prixFacture;
}
