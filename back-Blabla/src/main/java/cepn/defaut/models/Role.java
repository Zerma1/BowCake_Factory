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
@Getter
@Setter(value = AccessLevel.PROTECTED)
public class Role extends AbstractPersistableWithIdSetter<Long> {

    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    private Set<Utilisateur> utilisateurs = new HashSet<>();
}