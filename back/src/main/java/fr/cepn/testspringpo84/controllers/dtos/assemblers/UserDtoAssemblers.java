package fr.cepn.testspringpo84.controllers.dtos.assemblers;

import fr.cepn.testspringpo84.controllers.dtos.UserDto;
import fr.cepn.testspringpo84.models.Utilisateur;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserDtoAssemblers {

    public UserDto toDto(final Utilisateur x) {
        if (x == null) {
            return null;
        }
        return UserDto.builder()
                .id(x.getId())
                .build();
    }

    public Collection<UserDto> toDtoList(final Collection<Utilisateur> xs) {
        return xs.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Utilisateur toEntity(final UserDto xDto) {
        if (xDto == null) {
            return null;
        }
        Utilisateur x = new Utilisateur();
        //TODO: a reparé
//        x.setId(xDto.getId());
        return x;
    }
}
