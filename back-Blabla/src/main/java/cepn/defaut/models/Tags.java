package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(name = "uk__tags_id", columnNames = {"id"}))
@ToString(of = {"id", "nom"}, callSuper = true)
@EqualsAndHashCode(of = {"id", "nom"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tags extends AbstractPersistable<Long> {
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
