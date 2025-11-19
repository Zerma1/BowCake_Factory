package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "statutCommande", uniqueConstraints = @UniqueConstraint(name = "uk__statutCommande_id", columnNames = {"id"}))
@ToString(of = {"id", "statut"}, callSuper = true)
@EqualsAndHashCode(of = {"id", "statut"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatutCommande extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "statut ne doit pas etre null")
    //JPA
    @Column(name = "statut", nullable = false)
    private String statut;
}
