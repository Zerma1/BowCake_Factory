package cepn.defaut.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "type_produit", uniqueConstraints = @UniqueConstraint(name = "pk_type_produit", columnNames = {"pk_type_produit"}))
@NoArgsConstructor
public class TypeProduit {

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length( max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

}
