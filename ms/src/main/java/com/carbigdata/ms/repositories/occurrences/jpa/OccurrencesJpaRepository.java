package com.carbigdata.ms.repositories.occurrences.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carbigdata.ms.core.domain.occurrences.OccurrencesList;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;


import java.util.List;


public interface OccurrencesJpaRepository extends JpaRepository<OccurrencesJpaModel, Integer>{
     
    // @Query(name = "OccurrencesJpaModel.findAll", nativeQuery = true)
    // public List<OccurrencesList> findWithDetails(
    //         @Param("full_name") String fullName,
    //         @Param("cpf") String cpf,
    //         @Param("city") String city
            
    // );
}
