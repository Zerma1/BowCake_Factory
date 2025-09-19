package fr.cepn.testspringpo84.controllers.dtos;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AuthDto {
    private Long id;

    public Object getLogin() {
        return null;
    }

    public Object getPwd() {
        return null;
    }
}