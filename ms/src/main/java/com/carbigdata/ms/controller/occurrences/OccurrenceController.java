package com.carbigdata.ms.controller.occurrences;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carbigdata.ms.controller.occurrences.dto.CreateOccurrenceRequestDTO;
import com.carbigdata.ms.controller.occurrences.dto.UpdateOccurrenceRequestDTO;
import com.carbigdata.ms.domain.client.entities.Client;
import com.carbigdata.ms.domain.occurrences.entities.Occurrences;
import com.carbigdata.ms.repositories.address.AddressJpaGateway;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.repositories.client.ClientJpaGateway;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.repositories.occurrences.OccurrencesJpaGateway;
import com.carbigdata.ms.repositories.occurrences.jpa.OccurrencesJpaRepository;
import com.carbigdata.ms.repositories.occurrences_image.OccurrencesImageJpaGateway;
import com.carbigdata.ms.repositories.occurrences_image.jpa.OccurrencesImageJpaRepository;
import com.carbigdata.ms.service.address.AddressService;
import com.carbigdata.ms.service.address.impl.AddressServiceImpl;
import com.carbigdata.ms.service.client.ClientService;
import com.carbigdata.ms.service.client.impl.ClientServiceImpl;
import com.carbigdata.ms.service.occurrence.OccurrenceService;
import com.carbigdata.ms.service.occurrence.impl.OccurrenceServiceImpl;
import com.carbigdata.ms.service.occurrence_images.OccurrenceImagesService;
import com.carbigdata.ms.service.occurrence_images.impl.OccurrenceImagesServiceImpl;
import com.carbigdata.ms.service.upload_image.UploadImageService;
import com.carbigdata.ms.service.upload_image.dto.UploadImageResponseDTO;

import io.micrometer.common.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/occurrence")
public class OccurrenceController {

    private final OccurrenceService occurrenceService;
    private final OccurrenceImagesService occurrenceImagesService;

    private final AddressService addressService;

    private final ClientService clientService;

    private final UploadImageService uploadImageService;

    public OccurrenceController(
            OccurrencesJpaRepository repository,
            OccurrencesImageJpaRepository occurrencesImageJpaRepository,
            UploadImageService uploadImageService,
            AddressJpaRepository addressJpaRepository,
            ClientJpaRepository clientJpaRepository) {

        OccurrencesJpaGateway occurrencesJpaGateway = new OccurrencesJpaGateway(repository);
        this.occurrenceService = OccurrenceServiceImpl.build(occurrencesJpaGateway);

        OccurrencesImageJpaGateway occurrencesImageJpaGateway = new OccurrencesImageJpaGateway(occurrencesImageJpaRepository);
        this.occurrenceImagesService = OccurrenceImagesServiceImpl.build(occurrencesImageJpaGateway);

        AddressJpaGateway addressJpaGateway = new AddressJpaGateway(addressJpaRepository);
        this.addressService = AddressServiceImpl.build(addressJpaGateway);

        ClientJpaGateway clientJpaGateway = new ClientJpaGateway(clientJpaRepository);
        this.clientService = ClientServiceImpl.with(clientJpaGateway);
        
        this.uploadImageService = uploadImageService;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(
            @RequestPart("body") CreateOccurrenceRequestDTO dto,
            @RequestParam("files") MultipartFile[] files) {

        Client client = this.clientService.findByCpf(dto.cpf());

        // // upload image
        ArrayList<UploadImageResponseDTO> responses = new ArrayList<UploadImageResponseDTO>();

        for (MultipartFile file : files) {
            var image = this.uploadImageService.upload(file);
            responses.add(image);
        }

        // save address
        var address = this.addressService.create(dto.state(), dto.city(), dto.zipCode(), dto.district());

        // create occurrence
        var occurrence = this.occurrenceService.create(address.getId(), client.getId());

        // save image on database
        for (UploadImageResponseDTO data : responses) {
            this.occurrenceImagesService.create(
                data.hash(),
                data.path(),
                occurrence.getId()
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(occurrence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Occurrences> update(@PathVariable("id") int id, @RequestBody UpdateOccurrenceRequestDTO dto) {
        Occurrences register = this.occurrenceService.update(id, dto.status());
        return ResponseEntity.ok().body(register);
    }

    @PostMapping("/finish/{id}")
    public ResponseEntity<Void> finish(@PathVariable("id") int id) {
        this.occurrenceService.finished(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        Occurrences register = this.occurrenceService.get(id);
        return ResponseEntity.ok().body(register);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        this.occurrenceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(
            @Nullable @RequestParam(name = "cpf") String cpf,
            @Nullable @RequestParam(name = "full_name") String fullName,
            @Nullable @RequestParam(name = "city") String city,
            @Nullable @RequestParam(name = "date") LocalDateTime date,
            @Nullable @RequestParam(name = "order_by_city") Boolean orderByCity,
            @Nullable @RequestParam(name = "order_by_date") Boolean orderByDate,
            @Nullable @RequestParam(name = "direction") String direction,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize

    ) {
        var response = this.occurrenceService.findAll(fullName, cpf, city, orderByCity, orderByDate, direction);
        return ResponseEntity.ok().body(response);
    }

}
