package cepn.defaut.controllers.dto.assemblers;

import cepn.defaut.controllers.dto.*;
import cepn.defaut.models.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
class UtilisateurDtoAssembler {

    public UtilisateurDto toDto(final Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        return UtilisateurDto.builder()
                .id(String.valueOf(utilisateur.getId()))
                .email(utilisateur.getEmail())
                .userName(utilisateur.getUserName())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateCreation(utilisateur.getDateCreation())
                .roleIds(utilisateur.getRoles().stream()
                        .map(role -> String.valueOf(role.getId()))
                        .collect(Collectors.toSet()))
                .roleNoms(utilisateur.getRoles().stream()
                        .map(Role::getNom)
                        .collect(Collectors.toSet()))
                .build();
    }

    public List<UtilisateurDto> toDtoList(final List<Utilisateur> utilisateurs) {
        return utilisateurs.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}