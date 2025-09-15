package fr.cepn.testspringpo84.services;


import fr.cepn.testspringpo84.models.Produit;
import fr.cepn.testspringpo84.models.TypeProduit;
import fr.cepn.testspringpo84.repository.ProduitRepository;
import fr.cepn.testspringpo84.services.commons.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService extends AbstractService<Produit, Long, ProduitRepository> {

    public ProduitService(ProduitRepository repository) {
        super(repository);
    }

    public List<Produit> findByTypeProduit(TypeProduit typeProduit){
        return repository.findByTypeProduit(typeProduit);
    }
}
