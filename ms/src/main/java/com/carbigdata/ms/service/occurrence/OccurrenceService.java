package com.carbigdata.ms.service.occurrence;


import java.util.List;

import com.carbigdata.ms.core.domain.occurrences.Occurrences;
import com.carbigdata.ms.core.domain.occurrences.OccurrencesStatus;

public interface OccurrenceService {
    public Occurrences get(Integer id);

    public List<?> findAll(String fullName, String cpf, String city, Boolean orderByCity, Boolean orderByDate, String direction);
    
    public Occurrences update(Integer id, OccurrencesStatus status);
    public Occurrences create(Integer addressId, Integer clientId);

    public void delete(int id);

    public void finished(int id);

}
