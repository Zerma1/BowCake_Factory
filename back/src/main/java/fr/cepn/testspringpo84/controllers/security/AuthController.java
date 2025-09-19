package fr.cepn.testspringpo84.controllers.security;

import fr.cepn.testspringpo84.controllers.dtos.AuthDto;
import jakarta.validation.Valid;
import fr.cepn.testspringpo84.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login/")
    public ResponseEntity login(@Valid @RequestBody AuthDto authDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPwd())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}
