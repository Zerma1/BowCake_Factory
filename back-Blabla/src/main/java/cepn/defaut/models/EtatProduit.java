package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Table(name = "etatProduit", uniqueConstraints = @UniqueConstraint(name = "uk__etatProduit_id", columnNames = {"id"}))
@ToString(of = {"id","etat", "temps"}, callSuper = true)
@EqualsAndHashCode(of = {"id","etat", "temps"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EtatProduit extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "etat ne doit pas etre null")
    //JPA
    @Column(name = "etat", nullable = false)
    private String etat;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    //BV
    @Future
    //JPA
    @Column(name = "temps", nullable = false)
    private LocalDate dateFin;
}
