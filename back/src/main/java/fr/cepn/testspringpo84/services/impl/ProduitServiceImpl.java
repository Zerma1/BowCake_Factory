package fr.cepn.testspringpo84.services.impl;


import fr.cepn.testspringpo84.models.Produit;
import fr.cepn.testspringpo84.models.TypeProduit;
import fr.cepn.testspringpo84.repository.ProduitRepository;
import fr.cepn.testspringpo84.services.ProduitService;
import fr.cepn.testspringpo84.services.commons.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl extends ProduitService {

    public ProduitServiceImpl(ProduitRepository repository) {
        super(repository);
    }

    public List<Produit> findByTypeProduit(TypeProduit typeProduit){
        return repository.findByTypeProduit(typeProduit);
    }
}
