package com.carbigdata.ms.repositories.occurrences;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.carbigdata.ms.domain.client.exceptions.ClientNotFoundException;
import com.carbigdata.ms.domain.occurrences.entities.Occurrences;
import com.carbigdata.ms.domain.occurrences.gateway.OccurrencesGateway;
import com.carbigdata.ms.repositories.occurrences.jpa.OccurrencesJpaRepository;
import com.carbigdata.ms.repositories.occurrences.jpa.mapper.Occurrences2OccurrencesJpaModel;
import com.carbigdata.ms.repositories.occurrences.jpa.mapper.OccurrencesJpaModel2Occurrences;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;

@Component
public class OccurrencesJpaGateway implements OccurrencesGateway {

    private final OccurrencesJpaRepository repository;

    public OccurrencesJpaGateway(OccurrencesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Occurrences save(Occurrences data) {

        var entity = Occurrences2OccurrencesJpaModel.mapper(data);
        var client = this.repository.save(entity);

        return OccurrencesJpaModel2Occurrences.mapper(client);
    }

    @Override
    public Occurrences update(Occurrences data) {
        var entity = Occurrences2OccurrencesJpaModel.mapper(data);
        var client = this.repository.save(entity);

        return OccurrencesJpaModel2Occurrences.mapper(client);
    }

    @Override
    public void delete(Integer id) {
        this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException());
        this.repository.deleteById(id);
    }

    @Override
    public Occurrences findById(Integer id) {
        Optional<OccurrencesJpaModel> model = this.repository.findById(id);

        if (model.isEmpty()) {
            return null;
        }

        return OccurrencesJpaModel2Occurrences.mapper(model.get());
    }

    @Override
    public List<?> findAll(String fullName, String cpf, String city, Boolean orderByCity,
            Boolean orderByDate, String direction) {
        return this.repository.findWithDetails(fullName, cpf, city);
    }
}
