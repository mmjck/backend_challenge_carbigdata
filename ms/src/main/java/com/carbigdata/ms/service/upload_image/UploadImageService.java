package com.carbigdata.ms.service.upload_image;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carbigdata.ms.domain.occurrences_image.exceptions.OccurrencesImagesUploadException;
import com.carbigdata.ms.service.upload_image.dto.UploadImageResponseDTO;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class UploadImageService {
    @Value("${minio.bucketName}")
    private String bucketName;

    private final MinioClient minioClient;

    public UploadImageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public UploadImageResponseDTO upload(MultipartFile file) {
        try{
            InputStream inputStream = file.getInputStream();

            PutObjectArgs object = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(file.getOriginalFilename())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();

            ObjectWriteResponse response = minioClient.putObject(object);

            String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .build());
            
            return new UploadImageResponseDTO(response.etag(), url);
        } catch (Exception e) {
            System.out.println(e);
            throw new OccurrencesImagesUploadException();
        }
    }
}
