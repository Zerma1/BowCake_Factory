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
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Utilisateur extends AbstractPersistableWithIdSetter<Long> {


    @NonNull
    @Length(max = 50)
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @NonNull
    @Email
    @Length(max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Length(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Length(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "actif", nullable = false)
    private boolean actif = true;

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