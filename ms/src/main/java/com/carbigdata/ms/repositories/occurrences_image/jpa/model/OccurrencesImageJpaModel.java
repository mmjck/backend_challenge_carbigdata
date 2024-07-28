package com.carbigdata.ms.repositories.occurrences_image.jpa.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurencesJpaModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "OccurrencesImage")
@Table(name = "tb_occurrences_images")
@Getter
@Setter
@NoArgsConstructor
public class OccurrencesImageJpaModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "occurrence_id")
    private int occurenceId;

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "occurence_id", insertable = false, updatable = false)
    private OccurencesJpaModel occurrence;


    public OccurrencesImageJpaModel(int id, int occurenceId, String hash, String path, LocalDateTime createdAt) {
        this.id = id;
        this.occurenceId = occurenceId;
        this.hash = hash;
        this.path = path;
        this.createdAt = createdAt;
    }




}