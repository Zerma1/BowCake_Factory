package cepn.defaut.repository;

import cepn.defaut.models.Commande;
import cepn.defaut.models.StatuCommande;
import cepn.defaut.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends GeneralRepository<Commande, Long> {

    /**
     * Trouve une commande par son numéro
     */
    Optional<Commande> findByNumeroCommande(String numeroCommande);

    /**
     * Trouve toutes les commandes d'un utilisateur
     */
    List<Commande> findByUtilisateur(Utilisateur utilisateur);

    /**
     * Trouve toutes les commandes d'un utilisateur triées par date décroissante
     */
    List<Commande> findByUtilisateurOrderByDateCommandeDesc(Utilisateur utilisateur);

    /**
     * Trouve les N dernières commandes d'un utilisateur
     */
    List<Commande> findTop5ByUtilisateurOrderByDateCommandeDesc(Utilisateur utilisateur);

    /**
     * Trouve toutes les commandes par statut
     */
    List<Commande> findByStatut(StatuCommande statut);

    /**
     * Trouve toutes les commandes entre deux dates
     */
    List<Commande> findByDateCommandeBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Trouve toutes les commandes d'un montant supérieur à une valeur
     */
    List<Commande> findByMontantTotalGreaterThan(BigDecimal montant);

    /**
     * Calcule le montant total des ventes entre deux dates
     */
    @Query("SELECT SUM(c.montantTotal) FROM Commande c WHERE c.dateCommande BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalSalesBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    /**
     * Compte le nombre de commandes par statut
     */
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut = :statut")
    Long countByStatut(@Param("statut") StatuCommande statut);

    /**
     * Trouve les commandes récentes (dernières 24h)
     */
    @Query("SELECT c FROM Commande c WHERE c.dateCommande >= :since")
    List<Commande> findRecentCommandes(@Param("since") LocalDateTime since);

    /**
     * Vérifie si une commande existe par son numéro
     */
    boolean existsByNumeroCommande(String numeroCommande);
}