package fr.cepn.testspringpo84.controllers.dtos;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserDto {
    private Long id;

}