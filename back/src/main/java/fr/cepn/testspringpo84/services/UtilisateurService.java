package fr.cepn.testspringpo84.services;


import fr.cepn.testspringpo84.models.Utilisateur;
import fr.cepn.testspringpo84.repository.UserRepository;
import fr.cepn.testspringpo84.services.commons.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService extends AbstractService<Utilisateur, Long, UserRepository> {
    public UtilisateurService(UserRepository repository) {
        super(repository);
    }
}

