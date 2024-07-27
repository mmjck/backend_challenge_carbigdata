package com.carbigdata.ms.domain.occurrences.gateway;

import com.carbigdata.ms.domain.occurrences.entities.Occurrences;
import java.util.List;


public interface OccurrencesGateway {
    
    public Occurrences save(Occurrences data);
    public Occurrences update(Occurrences data);
    public void delete(Integer id);
    
    
    public Occurrences findById(Integer id);
    public List<?> findAll(String fullName, String cpf, String city, Boolean orderByCity, Boolean orderByDate, String direction);

}
