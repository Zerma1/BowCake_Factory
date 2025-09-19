package fr.cepn.testspringpo84.services;


import fr.cepn.testspringpo84.models.Produit;
import fr.cepn.testspringpo84.repository.ProduitRepository;
import fr.cepn.testspringpo84.services.commons.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ProduitService extends AbstractService<Produit, Long, ProduitRepository> {
    public ProduitService(ProduitRepository repository) {
        super(repository);
    }
}
