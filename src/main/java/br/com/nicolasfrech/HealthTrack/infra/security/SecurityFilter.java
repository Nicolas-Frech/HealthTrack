package br.com.nicolasfrech.HealthTrack.infra.security;

import br.com.nicolasfrech.HealthTrack.infra.security.token.TokenService;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserRepositoryJPA;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    private final UserRepositoryJPA userRepositoryJPA;

    public SecurityFilter(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);

        if(token != null) {
            var username = tokenService.getSubject(token);

            var user = userRepositoryJPA.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            var authenticationObj = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationObj);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if(authHeader != null) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
