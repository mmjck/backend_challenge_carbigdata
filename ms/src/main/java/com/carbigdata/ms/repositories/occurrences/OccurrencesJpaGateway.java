package com.carbigdata.ms.repositories.occurrences;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbigdata.ms.core.domain.occurrences.Occurrences;
import com.carbigdata.ms.core.domain.occurrences.OccurrencesList;
import com.carbigdata.ms.core.domain.occurrences.gateway.OccurrencesGateway;
import com.carbigdata.ms.core.exception.client.ClientNotFoundException;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;
import com.carbigdata.ms.repositories.images.jpa.ImagesJpaRepository;
import com.carbigdata.ms.repositories.images.jpa.mapper.ImagesJpaModel2ImagesShortMapper;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;
import com.carbigdata.ms.repositories.occurrences.jpa.OccurrencesJpaRepository;
import com.carbigdata.ms.repositories.occurrences.jpa.mapper.Occurrences2OccurrencesJpaModel;
import com.carbigdata.ms.repositories.occurrences.jpa.mapper.OccurrencesJpaModel2Occurrences;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;

@Repository
public class OccurrencesJpaGateway implements OccurrencesGateway {

    private final OccurrencesJpaRepository cccurrencesJpaRepository;
    private final ClientJpaRepository clientJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    private final ImagesJpaRepository occurrencesImageJpaRepository;
    public OccurrencesJpaGateway(
        OccurrencesJpaRepository cccurrencesJpaRepository, 
        ClientJpaRepository clientJpaRepository,
        AddressJpaRepository addressJpaRepository,
        ImagesJpaRepository occurrencesImageJpaRepository
    ) {
        this.cccurrencesJpaRepository = cccurrencesJpaRepository;
        this.clientJpaRepository = clientJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        this.occurrencesImageJpaRepository = occurrencesImageJpaRepository;
    }

    @Override
    public Occurrences save(Occurrences data) {

        var entity = Occurrences2OccurrencesJpaModel.mapper(data);
        var client = this.cccurrencesJpaRepository.save(entity);

        return OccurrencesJpaModel2Occurrences.mapper(client);
    }

    @Override
    public Occurrences update(Occurrences data) {
        var entity = Occurrences2OccurrencesJpaModel.mapper(data);
        var client = this.cccurrencesJpaRepository.save(entity);

        return OccurrencesJpaModel2Occurrences.mapper(client);
    }

    @Override
    public void delete(Integer id) {
        this.cccurrencesJpaRepository.findById(id).orElseThrow(() -> new ClientNotFoundException());
        this.cccurrencesJpaRepository.deleteById(id);
    }

    @Override
    public Occurrences findById(Integer id) {
        Optional<OccurrencesJpaModel> model = this.cccurrencesJpaRepository.findById(id);

        if (model.isEmpty()) {
            return null;
        }

        return OccurrencesJpaModel2Occurrences.mapper(model.get());
    }

    @Override
    public List<OccurrencesList> findAll(String fullName, String cpf, String city, Boolean orderByCity,
            Boolean orderByDate, String direction) {

        
        
        var occurrences = this.cccurrencesJpaRepository.findAll();
        List<OccurrencesList> response = new ArrayList<>();
        for(OccurrencesJpaModel model: occurrences){
            
            Optional<ClientJpaModel> client = this.clientJpaRepository.findById(model.getClientId());
            Optional<AddressJpaModel> address = this.addressJpaRepository.findById(model.getAddressId());

            List<ImagesJpaModel> images = this.occurrencesImageJpaRepository.findByOccurrenceId(model.getId());


            var parsed = new OccurrencesList(
                        model.getId(), 
                        model.getCreatedAt().toString(), 
                        model.getStatus().toString().toLowerCase(), 
                        client.get().getCpf(), 
                        client.get().getFullName(), 
                        client.get().getId(),
                        address.get().getState(),
                        address.get().getZipCode(), 
                        address.get().getDistrict(),
                        address.get().getCity(),
                        images.stream().map(ImagesJpaModel2ImagesShortMapper::mapper).toList()
                        );
            response.add(parsed);
        }

        return response;
    }
}
