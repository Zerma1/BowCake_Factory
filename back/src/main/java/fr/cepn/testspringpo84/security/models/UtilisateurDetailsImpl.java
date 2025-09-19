package fr.cepn.testspringpo84.security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import fr.cepn.testspringpo84.models.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UtilisateurDetailsImpl implements UserDetails {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Collection<? extends GrantedAuthority> authorities;

    public static UtilisateurDetailsImpl build(Utilisateur utilisateur) {
        return new UtilisateurDetailsImpl(
                utilisateur.getNom(),
                utilisateur.getPassword(),
                utilisateur.getClass()
        );
        return null;
    }

}
