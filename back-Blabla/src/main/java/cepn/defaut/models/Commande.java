package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commande", uniqueConstraints = @UniqueConstraint(name = "pk_commande", columnNames = {"pk_commande"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Column(name = "numero_commande", length = 50, nullable = false, unique = true)
    @Length(max = 50)
    private String numeroCommande;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @DecimalMin(value = "0.0")
    @Column(name = "montant_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @ManyToOne
    @JoinColumn(name = "statut_id", nullable = false)
    private StatuCommande statut;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 255)
    @Column(name = "adresse_livraison", length = 255)
    private String adresseLivraison;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 100)
    @Column(name = "ville", length = 100)
    private String ville;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 10)
    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Length(max = 50)
    @Column(name = "pays", length = 50)
    private String pays;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Panier> paniers = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (dateCommande == null) {
            dateCommande = LocalDateTime.now();
        }
        if (numeroCommande == null) {
            numeroCommande = "CMD-" + System.currentTimeMillis();
        }
    }
}