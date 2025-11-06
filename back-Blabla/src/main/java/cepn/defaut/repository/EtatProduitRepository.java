package cepn.defaut.repository;

import cepn.defaut.models.EtatProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtatProduitRepository extends JpaRepository<EtatProduit, Long> {

    /**
     * Trouve un état de produit par son libellé
     * @param etat le libellé de l'état
     * @return Optional contenant l'état si trouvé
     */
    Optional<EtatProduit> findByEtat(String etat);

    /**
     * Trouve tous les états limités (promotions, offres limitées, etc.)
     * @return liste des états limités
     */
    List<EtatProduit> findByLimiteTrue();

    /**
     * Trouve tous les états avec une date de fin
     * @return liste des états avec date de fin
     */
    List<EtatProduit> findByDateFinIsNotNull();

    /**
     * Trouve tous les états dont la date de fin est après une date donnée
     * @param date la date de référence
     * @return liste des états encore valides
     */
    List<EtatProduit> findByDateFinAfter(LocalDate date);

    /**
     * Trouve tous les états dont la date de fin est avant une date donnée (expirés)
     * @param date la date de référence
     * @return liste des états expirés
     */
    List<EtatProduit> findByDateFinBefore(LocalDate date);

    /**
     * Trouve tous les états limités et encore valides
     * @param date la date de référence (aujourd'hui généralement)
     * @return liste des états limités et valides
     */
    @Query("SELECT e FROM EtatProduit e WHERE e.limite = true AND (e.dateFin IS NULL OR e.dateFin >= :date)")
    List<EtatProduit> findActiveLimitedStates(LocalDate date);
}