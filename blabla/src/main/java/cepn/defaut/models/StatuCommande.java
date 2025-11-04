package cepn.defaut.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "statu_commande", uniqueConstraints = @UniqueConstraint(name = "pk_statu_commande", columnNames = {"pk_role"}))
@NoArgsConstructor
public class StatuCommande {

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "statu", length = 50, nullable = false)
    private String statu;
}
