package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "uk__user_id", columnNames = {"id"}))
@ToString(of = {"nom", "role"}, callSuper = true)
@EqualsAndHashCode(of = {"nom", "id"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends AbstractPersistable<Long> {
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
    @NotNull(message = "role ne doit pas etre null")
    //JPA
    @Column(name = "role", nullable = false)
    private String role;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    //BV
    @NotNull(message = "le email ne doit pas être null")
    @NotBlank(message = "le email ne doit pas être null ou vide")
    @Size(min = 5, max = 100, message = "Nom nombre caratères min = " + 5 + ", max = " + 100)
    //JPA
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    //BV
    @NotNull(message = "le numerot de telephon ne doit pas être null")
    @NotBlank(message = "le numerot de telephon ne doit pas être null ou vide")
    @Size(min = 10, max = 10, message = "Nom nombre caratères doit etre egale a 10")
    //JPA
    @Column(name = "telephon", nullable = false, length = 10)

    private Integer telephon;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    //BV
    @NotNull(message = "le nom ne doit pas être null")
    @NotBlank(message = "le nom ne doit pas être null ou vide")
    //JPA
    @Column(name = "points", nullable = false, length = 10)
    private Integer points = 0;
}
