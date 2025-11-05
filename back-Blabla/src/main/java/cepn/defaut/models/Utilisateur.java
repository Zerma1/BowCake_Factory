package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(name = "uk_utilisateur_email", columnNames = {"email"}),
        @UniqueConstraint(name = "uk_utilisateur_username", columnNames = {"username"})
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Email
    @Length(max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "actif", nullable = false)
    private boolean actif = true;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Commande> commandes = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}