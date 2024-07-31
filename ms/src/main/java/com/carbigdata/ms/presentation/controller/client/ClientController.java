package com.carbigdata.ms.presentation.controller.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.presentation.controller.client.dto.ClientResponseDto;
import com.carbigdata.ms.presentation.controller.client.dto.UpdateClientRequestDTO;
import com.carbigdata.ms.service.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable("id") int id, @RequestBody UpdateClientRequestDTO dto) {
        Client response = this.service.update(id, dto.name(), dto.cpf(), dto.birthDate());
        return ResponseEntity.ok().body(new ClientResponseDto(response.getFullName(), response.getCpf(), response.getBirthDate()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getById(@PathVariable("id") int id) {
        Client response = this.service.get(id);
        return ResponseEntity.ok().body(new ClientResponseDto(response.getFullName(), response.getCpf(), response.getBirthDate()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
