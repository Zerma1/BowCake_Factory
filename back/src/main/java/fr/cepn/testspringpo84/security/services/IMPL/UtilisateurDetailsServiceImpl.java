package fr.cepn.testspringpo84.security.services.IMPL;

import fr.cepn.testspringpo84.models.Utilisateur;
import fr.cepn.testspringpo84.repository.UserRepository;
import fr.cepn.testspringpo84.security.models.UtilisateurDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UtilisateurDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = userRepository.findByNom(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UtilisateurDetailsImpl.build(utilisateur);
    }
}
