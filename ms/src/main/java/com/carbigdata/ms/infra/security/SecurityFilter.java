package com.carbigdata.ms.infra.security;

import java.io.IOException;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.carbigdata.ms.core.exception.client.ClientNotFoundException;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.repositories.client.jpa.mapper.ClientJpaModel2Client;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ClientJpaRepository repository;

    public SecurityFilter(TokenService tokenService, ClientJpaRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);
        var cpf = tokenService.validateToken(token);
        if (cpf != null) {
            ClientJpaModel user = this.repository.findByCpf(cpf).orElseThrow(() -> new ClientNotFoundException());

            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(ClientJpaModel2Client.mapper(user), null,
                    authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");

        if (header == null) {
            return null;
        }

        return header.replace("Bearer ", "");
    }

}
