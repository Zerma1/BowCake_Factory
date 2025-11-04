package cepn.defaut.models;

import cepn.defaut.models.common.AbstractPersistableWithIdSetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(name = "pk_role", columnNames = {"pk_role"}))
@NoArgsConstructor
public class Role extends AbstractPersistableWithIdSetter<Long> {

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;
}
