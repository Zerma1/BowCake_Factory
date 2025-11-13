package cepn.defaut.controllers.dto.assemblers;

import cepn.defaut.controllers.dto.TypeProduitDto;
import cepn.defaut.models.TypeProduit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class TypeProduitDtoAssembler {

    public TypeProduitDto toDto(final TypeProduit type){
        return TypeProduitDto.builder()
                .id(String.valueOf(type.getId()))
                .nom(type.getNom())
                .description(type.getDescription())
                .produits(type.getProduits())
                .build();
    }

    public Collection<TypeProduitDto> toDtoList(final TypeProduit[] types) {
        Collection<TypeProduitDto> typeDtos = new ArrayList<>();
        for (TypeProduit type : types) {
            typeDtos.add(toDto(type));
        }
        return typeDtos;
    }
}
