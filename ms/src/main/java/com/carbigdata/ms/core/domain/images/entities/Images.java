package com.carbigdata.ms.core.domain.images.entities;

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
public class Images {
    private int id;

    private int occurrenceId;

    private String hash;
    private String path;
    private LocalDateTime createdAt;
}
