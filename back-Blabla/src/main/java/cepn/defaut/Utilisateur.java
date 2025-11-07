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
        @UniqueConstraint(name = "uk_utilisateur_userName", columnNames = {"userName"})
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Utilisateur extends AbstractPersistableWithIdSetter<Long> {

    /* #region Stoké en bdd */

    @NonNull
    @Email
    @Length(max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Length(max = 100)
    @Column(name = "userName", length = 100, nullable = false)
    private String userName;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    /* #endregion Stoké en bdd */

    /* #region Champs calculés (non stockés) */

    @Transient
    private String nom;

    @Transient
    private String prenom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Commande> commandes = new HashSet<>();

    /* #endregion Champs calculés (non stockés) */

    /* #region Génération automatiquement des champs */

    @PrePersist
    @PreUpdate
    protected void updateUserName() {
        if (nom != null && prenom != null) {
            this.userName = normalize(nom) + "_" + normalize(prenom);
        } else if (this.userName == null) {
            this.userName = "Inconnu";
        }

        if (dateCreation == null) {
            dateCreation = LocalDateTime.now();
        }
    }

    @PostLoad
    protected void splitUserName() {
        if (this.userName != null && this.userName.contains(" ")) {
            String[] parts = this.userName.split("_", 2);
            this.nom = parts[0];
            this.prenom = parts[1];
        } else {
            this.nom = this.userName;
            this.prenom = "";
        }
    }

    /* #endregion Génération automatiquement des champs */

    /* #region Méthodes utilitaires privées */

    private String normalize(String s) {
        if (s == null || s.isEmpty()) return "";
        // retire les underscores existants pour éviter les conflits
        s = s.replace("_", "-");
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    /* #endregion Méthodes utilitaires privées */
}
