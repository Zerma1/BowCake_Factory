package fr.cepn.testspringpo84.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produit", uniqueConstraints = @UniqueConstraint(name = "uk__produit_id", columnNames = {"id"}))
@ToString(of = {"nom"}, callSuper = true)
@EqualsAndHashCode(of = {"id","nom"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class Produit extends AbstractPersistable<Long> {

    //LBK
    @NonNull
    //BV
    @NotNull(message = "nom ne doit pas etre null")
    //JPA
    @Column(name = "nom", nullable = false)
    private String nom;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "quantite ne doit pas etre null")
    //JPA
    @Column(name = "quantite", nullable = false)
    @Min(0)
    private Integer quantite;

    //LBK
    @NonNull
    //BV
    @NotNull(message = "prix ne doit pas etre null")
    //JPA
    @Column(name = "prix", nullable = false)
    @Min(0)
    private Integer prix;

    /* #region produit_tag */

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(
                name = "produit_tags",
                joinColumns = @JoinColumn(name = "produit_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private List<Tags> tags = new ArrayList<>();

    /* #endregion produit_tag */

    //TODO: Methode a implementer
//    public void addTag(Tags tag) {
//        this.tags.add(tag);
//        tag.getProduits().add(this);
//    }
//
//    public void removeTag(Tags tag) {
//        this.tags.remove(tag);
//        tag.getProduits().remove(this);
//    }

}
