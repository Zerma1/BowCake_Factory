package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(name = "pk_tag", columnNames = {"pk_tag"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Tag extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    private Set<Produit> produits = new HashSet<>();
}