package cepn.defaut.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "etat_produit", uniqueConstraints = @UniqueConstraint(name = "pk_type-produit", columnNames = {"pk_type_produit"}))
@NoArgsConstructor
public class EtatProduit {

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Length(max = 50)
    @Column(name = "etat", length = 50, nullable = false)
    private String etat;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Column(name = "limite", nullable = false)
    private boolean limite = false;

    //LBK
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    @Column(name = "date-fin", nullable = true)
    @FutureOrPresent
    private LocalDate dateFin;

}
