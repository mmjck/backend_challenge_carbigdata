package com.carbigdata.ms.repositories.client.jpa.model;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "Client")
@Table(name = "tb_clients")
@Getter
@Setter
public class ClientJpaModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ClientJpaModel() {

    }
    
    public ClientJpaModel(int id,  String fullName, String cpf, String password, LocalDateTime birthDate, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }


    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<OccurrencesJpaModel> occurrences;



}
