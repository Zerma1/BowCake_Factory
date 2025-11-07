package cepn.defaut.repository;

import cepn.defaut.models.Commande;
import cepn.defaut.models.Panier;
import cepn.defaut.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

    /**
     * Trouve tous les paniers d'une commande
     */
    List<Panier> findByCommande(Commande commande);

    /**
     * Trouve tous les paniers contenant un produit spécifique
     */
    List<Panier> findByProduit(Produit produit);

    /**
     * Calcule le total d'une commande
     */
    @Query("SELECT SUM(p.quantite * p.prixUnitaire) FROM Panier p WHERE p.commande = :commande")
    BigDecimal calculateTotalForCommande(@Param("commande") Commande commande);

    /**
     * Compte le nombre d'items dans une commande
     */
    @Query("SELECT COUNT(p) FROM Panier p WHERE p.commande = :commande")
    Long countItemsInCommande(@Param("commande") Commande commande);
}