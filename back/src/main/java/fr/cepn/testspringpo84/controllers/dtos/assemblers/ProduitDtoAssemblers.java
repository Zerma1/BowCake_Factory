package fr.cepn.testspringpo84.controllers.dtos.assemblers;


import fr.cepn.testspringpo84.controllers.dtos.ProduitDto;
import fr.cepn.testspringpo84.models.Produit;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ProduitDtoAssemblers {

    public ProduitDto toDto(final Produit x) {
        if (x == null) {
            return null;
        }
        return ProduitDto.builder()
                .id(x.getId())
                .nom(x.getNom())
                .quantite(x.getQuantite())
                .prix(x.getPrix())
                .build();
    }

    public Collection<ProduitDto> toDtoList(final Collection<Produit> xs) {
        return xs.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Produit toEntity(final ProduitDto xDto) {
        if (xDto == null) {
            return null;
        }
        Produit x = new Produit();

        return x;
    }
}
