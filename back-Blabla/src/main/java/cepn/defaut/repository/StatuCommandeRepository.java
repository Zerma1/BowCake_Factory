package cepn.defaut.repository;

import cepn.defaut.models.StatuCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatuCommandeRepository extends GeneralRepository<StatuCommande, Long> {

    /**
     * Trouve un statut de commande par son libellé
     * @param statu le libellé du statut
     * @return Optional contenant le statut si trouvé
     */
    Optional<StatuCommande> findByStatu(String statu);

    /**
     * Vérifie si un statut existe par son libellé
     * @param statu le libellé du statut
     * @return true si le statut existe
     */
    boolean existsByStatu(String statu);
}
