package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class UtilisateurDto {
    private String id;
    private String email;
    private String userName;
    private String nom;
    private String prenom;
    private LocalDateTime dateCreation;
    private Set<String> roleIds;
    private Set<String> roleNoms;
    // Ne jamais inclure le mot de passe dans un DTO de réponse
}
