package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "statu_commande", uniqueConstraints = @UniqueConstraint(name = "pk_statu_commande", columnNames = {"pk_statu_commande"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class StatuCommande extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 50)
    @Column(name = "statu", length = 50, nullable = false)
    private String statu;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "statut")
    @Builder.Default
    private Set<Commande> commandes = new HashSet<>();
}