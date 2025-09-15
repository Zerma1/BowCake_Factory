package fr.cepn.testspringpo84.services;


import fr.cepn.testspringpo84.models.User;
import fr.cepn.testspringpo84.repository.UserRepository;
import fr.cepn.testspringpo84.services.commons.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, Long, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}

