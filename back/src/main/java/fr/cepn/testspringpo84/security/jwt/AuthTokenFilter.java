package fr.cepn.testspringpo84.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fr.cepn.testspringpo84.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtUtils = jwtUtils;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (this.jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7).trim();
        }

        return null;
    }
}
