package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etat_produit", uniqueConstraints = @UniqueConstraint(name = "pk_etat_produit", columnNames = {"pk_etat_produit"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PROTECTED)
public class EtatProduit extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 50)
    @Column(name = "etat", length = 50, nullable = false)
    private String etat;

    @NonNull
    @Column(name = "limite", nullable = false)
    private boolean limite = false;

    @Column(name = "date_fin")
    @FutureOrPresent
    private LocalDate dateFin;

    @OneToMany(mappedBy = "etatProduit")
    @Builder.Default
    private Set<Produit> produits = new HashSet<>();
}