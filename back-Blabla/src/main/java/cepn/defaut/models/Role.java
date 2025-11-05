package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(name = "pk_role", columnNames = {"pk_role"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends AbstractPersistableWithIdSetter<Long> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    private Set<Utilisateur> utilisateurs = new HashSet<>();
}