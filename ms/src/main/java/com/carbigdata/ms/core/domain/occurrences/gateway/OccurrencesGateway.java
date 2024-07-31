package com.carbigdata.ms.core.domain.occurrences.gateway;

import java.util.List;

import com.carbigdata.ms.core.domain.occurrences.Occurrences;
import com.carbigdata.ms.core.domain.occurrences.OccurrencesList;


public interface OccurrencesGateway {
    
    public Occurrences save(Occurrences data);
    public Occurrences update(Occurrences data);
    public void delete(Integer id);
    
    
    public Occurrences findById(Integer id);
    public List<OccurrencesList> findAll(String fullName, String cpf, String city, Boolean orderByCity, Boolean orderByDate, String direction);

}
