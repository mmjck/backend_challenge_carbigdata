package com.carbigdata.ms.presentation.controller.images;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.presentation.controller.images.dto.ImagesResponseDTO;
import com.carbigdata.ms.presentation.controller.pagination.PaginationResponse;
import com.carbigdata.ms.repositories.images.jpa.ImagesJpaRepository;
import com.carbigdata.ms.service.images.ImagesService;
import com.carbigdata.ms.service.images.impl.ImagesServiceImpl;
import com.carbigdata.ms.service.upload_image.UploadImageService;
import com.carbigdata.ms.service.upload_image.dto.UploadImageResponseDTO;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/occurrence-images")
public class ImagesController {

    private final ImagesService occurrenceImagesService;
    private final UploadImageService uploadImageService;

    public ImagesController(ImagesService occurrenceImagesService, UploadImageService uploadImageService) {

        this.occurrenceImagesService = occurrenceImagesService;
        this.uploadImageService = uploadImageService;

    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestPart("occurrence_id") int occurrenceId,
            @RequestParam("files") MultipartFile[] files) {

        ArrayList<UploadImageResponseDTO> responses = new ArrayList<UploadImageResponseDTO>();

        for (MultipartFile f : files) {
            UploadImageResponseDTO image;
            image = this.uploadImageService.upload(f);
            responses.add(image);
        }

        for (UploadImageResponseDTO data : responses) {
            this.occurrenceImagesService.create(
                    data.hash(),
                    data.path(),
                    occurrenceId);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") int id,
            @RequestParam("files") MultipartFile[] files) {
        if (files != null) {
            ArrayList<UploadImageResponseDTO> responses = new ArrayList<UploadImageResponseDTO>();

            for (MultipartFile f : files) {
                UploadImageResponseDTO image;
                image = this.uploadImageService.upload(f);
                responses.add(image);
            }

            for (UploadImageResponseDTO data : responses) {
                this.occurrenceImagesService.update(
                        id,
                        data.hash(),
                        data.path()

                );
            }

        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<PaginationResponse<Images>> getAll(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PaginationResponse<Images> data = this.occurrenceImagesService.findAll(page, pageSize);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagesResponseDTO> getById(@PathVariable("id") int id) {
        Images register = this.occurrenceImagesService.get(id);
        return ResponseEntity.ok().body(new ImagesResponseDTO(register.getHash(), register.getPath(), register.getOccurrenceId(), register.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        this.occurrenceImagesService.delete(id);
        return ResponseEntity.ok().build();
    }

}
