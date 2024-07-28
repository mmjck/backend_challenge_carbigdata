package com.carbigdata.ms.service.occurrence.impl;



import org.springframework.stereotype.Service;
import com.carbigdata.ms.domain.occurrences.entities.Occurrences;
import com.carbigdata.ms.domain.occurrences.entities.OccurrencesStatus;
import com.carbigdata.ms.domain.occurrences.exceptions.OccurrenceNotBeModifiedException;
import com.carbigdata.ms.domain.occurrences.exceptions.OccurrenceNotFoundException;
import com.carbigdata.ms.domain.occurrences.gateway.OccurrencesGateway;
import com.carbigdata.ms.service.occurrence.OccurrenceService;

import java.util.List;

@Service
public class OccurrenceServiceImpl implements OccurrenceService {
    
    private final OccurrencesGateway gateway;
        private OccurrenceServiceImpl(OccurrencesGateway gateway) {
        this.gateway = gateway;
    }

    public static OccurrenceServiceImpl build(OccurrencesGateway gateway){
        return new OccurrenceServiceImpl(gateway);
    }

    @Override
    public Occurrences get(Integer id) {
        Occurrences occurrence = this.gateway.findById(id);
        
        if(occurrence == null){
            throw new OccurrenceNotFoundException();
        }
        
        return occurrence;
    }

    @Override
    public Occurrences create(Integer addressId, Integer clientId) {
        var occ = Occurrences
            .builder()
            .addressId(addressId)
            .clientId(clientId)
            .status(OccurrencesStatus.ACTIVE)
            .build();
            
        return this.gateway.save(occ);
    }

    @Override
    public void delete(int id) {
        this.gateway.delete(id);
    }


    @Override
    public void finished(int id) {
        Occurrences occurrence = this.gateway.findById(id);

        if(occurrence == null){
            throw new OccurrenceNotFoundException();
        }
        
        if(occurrence.getStatus() == OccurrencesStatus.FINISHED){
            throw new OccurrenceNotBeModifiedException();
        }

        var occ = occurrence;
        occ.setStatus(OccurrencesStatus.FINISHED);
        
        
        this.gateway.save(occ);
    }

    @Override
    public Occurrences update(Integer id, OccurrencesStatus status) {
        Occurrences occurrence = this.gateway.findById(id);

        if(occurrence == null){
            throw new OccurrenceNotFoundException();
        }


        if(occurrence.getStatus() == OccurrencesStatus.FINISHED){
            throw new OccurrenceNotBeModifiedException();
        }
        return this.gateway.save(occurrence);

    }

    @Override
    public List<?> findAll(String fullName, String cpf, String city, Boolean orderByCity, Boolean orderByDate, String direction) {
        String cpfConcat = "%" + fullName + "%";
        String cityConcat = "%" + city + "%";

        return this.gateway.findAll( cpfConcat,  cpf,  cityConcat, orderByCity, orderByDate, direction);
    }

}