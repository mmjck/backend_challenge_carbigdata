package com.carbigdata.ms.controller.occurrences_images;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carbigdata.ms.controller.occurrences_images.dto.CreateOccurrenceImagesRequestDTO;
import com.carbigdata.ms.controller.occurrences_images.dto.UpdateOccurrenceImagesRequestDTO;
import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.domain.pagination.PaginationResponse;
import com.carbigdata.ms.repositories.occurrences_image.OccurrencesImageJpaGateway;
import com.carbigdata.ms.repositories.occurrences_image.jpa.OccurrencesImageJpaRepository;
import com.carbigdata.ms.service.occurrence_images.OccurrenceImagesService;
import com.carbigdata.ms.service.occurrence_images.impl.OccurrenceImagesServiceImpl;
import com.carbigdata.ms.service.upload_image.UploadImageService;
import com.carbigdata.ms.service.upload_image.dto.UploadImageResponseDTO;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/occurrence-images")
public class OccurrenceImagesController {

    private final OccurrenceImagesService service;
    private final UploadImageService uploadImageService;

    public OccurrenceImagesController(OccurrencesImageJpaRepository repository, UploadImageService uploadImageService) {

        OccurrencesImageJpaGateway gateway = new OccurrencesImageJpaGateway(repository);
        this.service = OccurrenceImagesServiceImpl.build(gateway);

        this.uploadImageService = uploadImageService;

    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestPart("body") CreateOccurrenceImagesRequestDTO dto,
            @RequestParam("files") MultipartFile[] files) {


                ArrayList<UploadImageResponseDTO> responses = new ArrayList<UploadImageResponseDTO>();

        for (MultipartFile file : files) {
            UploadImageResponseDTO image;
            image = this.uploadImageService.upload(file);
            responses.add(image);
        }


        for (UploadImageResponseDTO data: responses) {
            this.service.create(data.hash(), data.path(), dto.occurenceId());
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UpdateOccurrenceImagesRequestDTO dto) {
        this.service.update(id, dto.hash(), dto.path());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/")
    public ResponseEntity<?> getAll(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PaginationResponse<OccurrencesImage> data = this.service.findAll(page, pageSize);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        OccurrencesImage client = this.service.get(id);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}
