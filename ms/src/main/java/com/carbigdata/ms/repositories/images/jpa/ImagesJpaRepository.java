package com.carbigdata.ms.repositories.images.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

import java.util.List;



public interface ImagesJpaRepository extends JpaRepository<ImagesJpaModel, Integer> {
    List<ImagesJpaModel> findByOccurrenceId(int occurrenceId);

}
