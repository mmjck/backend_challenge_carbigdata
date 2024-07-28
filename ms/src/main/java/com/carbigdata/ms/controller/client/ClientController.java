package com.carbigdata.ms.controller.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.controller.client.dto.UpdateClientRequestDTO;
import com.carbigdata.ms.domain.client.entities.Client;
import com.carbigdata.ms.repositories.client.ClientJpaGateway;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.service.client.ClientService;
import com.carbigdata.ms.service.client.impl.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientJpaRepository repository,  PasswordEncoder passwordEncoder) {
        ClientJpaGateway gateway = new ClientJpaGateway(repository);
        this.service = ClientServiceImpl.build(gateway, passwordEncoder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UpdateClientRequestDTO dto) {
        Client client = this.service.update(id, dto.name(), dto.cpf(), dto.birthDate());
        return ResponseEntity.ok().body(client);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        Client client = this.service.get(id);
        return ResponseEntity.ok().body(client);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
