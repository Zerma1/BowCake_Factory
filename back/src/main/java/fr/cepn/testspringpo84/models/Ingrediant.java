package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ingrediant", uniqueConstraints = @UniqueConstraint(name = "uk__ingrediant_id", columnNames = {"id"}))
@ToString(of = {"nom", "quantite","prix"}, callSuper = true)
@EqualsAndHashCode(of = {"id","nom", "quantite","prix"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingrediant extends AbstractPersistable<Long> {
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
    private Integer quantite;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "prix ne doit pas etre null")
    //JPA
    @Column(name = "prix", nullable = false)
    private Integer prix;
}
