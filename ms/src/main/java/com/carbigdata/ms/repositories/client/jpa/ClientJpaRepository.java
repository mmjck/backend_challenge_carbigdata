package com.carbigdata.ms.repositories.client.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;
import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<ClientJpaModel, Integer>{
    Optional<ClientJpaModel> findByCpf(String cpf);
}
