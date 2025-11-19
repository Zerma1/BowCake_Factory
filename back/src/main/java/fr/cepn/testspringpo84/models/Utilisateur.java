package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "utilisateur", uniqueConstraints = @UniqueConstraint(name = "uk__utilisateur_id", columnNames = {"id"}))
@ToString(of = {"nom", "role"}, callSuper = true)
@EqualsAndHashCode(of = {"nom", "role", "email"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Utilisateur extends AbstractPersistable<Long> {

    @NonNull
    @NotNull(message = "nom ne doit pas etre null")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NonNull
    @NotNull(message = "role ne doit pas etre null")
    @Column(name = "role", nullable = false)
    private String role;

    @NonNull
    @NotNull(message = "le email ne doit pas être null")
    @NotBlank(message = "le email ne doit pas être null ou vide")
    @Size(min = 5, max = 100, message = "Nom nombre caratères min = " + 5 + ", max = " + 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NonNull
    @NotNull(message = "le numerot de telephon ne doit pas être null")
    @NotBlank(message = "le numerot de telephon ne doit pas être null ou vide")
    @Size(min = 10, max = 10, message = "Nom nombre caratères doit etre egale a 10")
    @Column(name = "telephon", nullable = false, length = 10)
    private String telephon;

    @NonNull
    @NotNull(message = "le motdepasse ne doit pas être null")
    @NotBlank(message = "le motdepasse ne doit pas être null ou vide")
    @Size(min = 10, max = 255, message = "Le mot de passe doit contenir au moins 10 caractères")
    @Column(name = "motdepasse", nullable = false, length = 10)
    //TODO: eventuelement trouver un moyer de hacher le mdp en attendent on applique la metode TGCM
    private String motDePasse;

    @NonNull
    @Min(0)
    @Column(name = "points", nullable = false, length = 10)
    private Integer points = 0;
}
