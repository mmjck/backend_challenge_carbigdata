package com.carbigdata.ms.core.domain.occurrences;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Occurrences {
    private Integer id;
    private OccurrencesStatus status;
    private LocalDateTime createdAt;


    private Integer addressId;
    private Integer clientId;
}