package cepn.defaut.repository;

import cepn.defaut.models.EtatProduit;
import cepn.defaut.models.Produit;
import cepn.defaut.models.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /**
     * Trouve un produit par son nom
     * @param nom le nom du produit
     * @return Optional contenant le produit si trouvé
     */
    Optional<Produit> findByNom(String nom);

    /**
     * Trouve tous les produits dont le nom contient la chaîne donnée (insensible à la casse)
     * @param nom partie du nom à rechercher
     * @return liste des produits correspondants
     */
    List<Produit> findByNomContainingIgnoreCase(String nom);

    /**
     * Trouve tous les produits par type
     * @param typeProduit le type de produit
     * @return liste des produits de ce type
     */
    List<Produit> findByTypeProduit(TypeProduit typeProduit);

    /**
     * Trouve tous les produits par état
     * @param etatProduit l'état du produit
     * @return liste des produits dans cet état
     */
    List<Produit> findByEtatProduit(EtatProduit etatProduit);

    /**
     * Trouve tous les produits avec un stock supérieur à une valeur donnée
     * @param stock le stock minimum
     * @return liste des produits disponibles
     */
    List<Produit> findByStockGreaterThan(Integer stock);

    /**
     * Trouve tous les produits avec un stock inférieur à une valeur donnée
     * @param stock le stock maximum
     * @return liste des produits en rupture
     */
    List<Produit> findByStockLessThan(Integer stock);

    /**
     * Trouve tous les produits avec un stock égal à zéro
     * @return liste des produits en rupture de stock
     */
    List<Produit> findByStockEquals(Integer stock);

    /**
     * Trouve tous les produits dans une fourchette de prix HT
     * @param minPrice prix minimum HT
     * @param maxPrice prix maximum HT
     * @return liste des produits dans cette fourchette
     */
    List<Produit> findByPrixHTBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Compte le nombre de produits par type
     * @param typeProduit le type de produit
     * @return nombre de produits
     */
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.typeProduit = :typeProduit")
    Long countByTypeProduit(@Param("typeProduit") TypeProduit typeProduit);

    /**
     * Compte le nombre de produits par état
     * @param etatProduit l'état du produit
     * @return nombre de produits
     */
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.etatProduit = :etatProduit")
    Long countByEtatProduit(@Param("etatProduit") EtatProduit etatProduit);

    /**
     * Trouve les produits les plus vendus (basé sur le stock décroissant - à affiner)
     * @return liste des produits
     */
    @Query("SELECT p FROM Produit p ORDER BY p.stock DESC")
    List<Produit> findTopSellingProducts();

    /**
     * Trouve les produits disponibles triés par prix croissant
     * @return liste des produits
     */
    @Query("SELECT p FROM Produit p WHERE p.stock > 0 ORDER BY p.prixHT ASC")
    List<Produit> findAvailableProductsOrderByPriceAsc();

    /**
     * Trouve les produits disponibles triés par prix décroissant
     * @return liste des produits
     */
    @Query("SELECT p FROM Produit p WHERE p.stock > 0 ORDER BY p.prixHT DESC")
    List<Produit> findAvailableProductsOrderByPriceDesc();

    /**
     * Trouve les nouveaux produits (à personnaliser selon vos critères)
     * @return liste des nouveaux produits
     */
    @Query("SELECT p FROM Produit p ORDER BY p.id DESC")
    List<Produit> findNewProducts();

    /**
     * Trouve les produits en promotion
     * @return liste des produits en promotion
     */
    @Query("SELECT p FROM Produit p WHERE p.etatProduit.etat LIKE '%PROMO%' OR p.etatProduit.etat LIKE '%PROMOTION%'")
    List<Produit> findPromotionProducts();

    /**
     * Recherche avancée par nom ou description
     * @param searchTerm terme de recherche
     * @return liste des produits correspondants
     */
    @Query("SELECT p FROM Produit p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Produit> searchByNomOrDescription(@Param("searchTerm") String searchTerm);

    /**
     * Vérifie si un produit existe par son nom
     * @param nom le nom du produit
     * @return true si le produit existe
     */
    boolean existsByNom(String nom);

    /**
     * Compte le nombre total de produits disponibles
     * @return nombre de produits en stock
     */
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.stock > 0")
    Long countAvailableProducts();

    /**
     * Calcule la valeur totale du stock
     * @return valeur totale en BigDecimal
     */
    @Query("SELECT SUM(p.prixHT * p.stock) FROM Produit p")
    BigDecimal calculateTotalStockValue();
}