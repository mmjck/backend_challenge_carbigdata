package com.carbigdata.ms.domain.occurrences_image.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrencesImage {
    private int id;

    private int occurrenceId;

    private String hash;
    private String path;
    private LocalDateTime createdAt;
}
