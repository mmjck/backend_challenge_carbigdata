package com.carbigdata.ms.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.core.domain.client.gateway.ClientGateway;

import java.util.ArrayList;


@Component
public class CustomUserDetailsService implements UserDetailsService{

    private final ClientGateway gateway;
    
    private CustomUserDetailsService(ClientGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Client client = this.gateway.findByCpf(cpf);

        if(client == null){
            throw new UsernameNotFoundException("Client not Found");
        }
        
        return new org.springframework.security.core.userdetails.User(client.getCpf(), client.getPassword(), new ArrayList<>());
    }
    
}
