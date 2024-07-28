package com.carbigdata.ms.repositories.address.jpa.model;


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


@Entity(name = "Address")
@Table(name = "tb_address")
@Getter
@Setter
public class AddressJpaModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    public AddressJpaModel(){

    }
    
    
    public AddressJpaModel(int id, String state, String city, String district, String zipCode,
            LocalDateTime createdAt) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.district = district;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    }


    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<OccurrencesJpaModel> occurrences;



}
