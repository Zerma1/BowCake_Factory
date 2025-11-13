package cepn.defaut.controllers.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatuCommandeDto {
    private String id;
    private String statu;
    private String description;
}
