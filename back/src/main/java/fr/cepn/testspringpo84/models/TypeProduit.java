package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "TypeProduit", uniqueConstraints = @UniqueConstraint(name = "uk__TypeProduit_id", columnNames = {"id"}))
@ToString(of = {"nom", ""}, callSuper = true)
@EqualsAndHashCode(of = {"nom", ""}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeProduit extends AbstractPersistable<Long> {
    //LBK
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    @NonNull
    //BV
    @NotNull(message = "nom ne doit pas etre null")
    //JPA
    @Column(name = "nom", nullable = false)
    private String nom;
}
