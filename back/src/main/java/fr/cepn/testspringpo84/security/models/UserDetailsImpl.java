package fr.cepn.testspringpo84.security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole().getAllRoles().stream()
                        .map(role -> (GrantedAuthority) () -> "ROLE_" + role.name())
                        .toList()
        );
    }

}
