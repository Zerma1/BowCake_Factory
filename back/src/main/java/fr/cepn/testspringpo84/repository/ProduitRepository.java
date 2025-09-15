package fr.cepn.testspringpo84.repository;


import fr.cepn.testspringpo84.models.Produit;
import fr.cepn.testspringpo84.models.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByTypeProduit(TypeProduit typeProduit);
}
