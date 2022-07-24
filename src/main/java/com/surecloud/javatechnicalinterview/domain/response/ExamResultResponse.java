package com.surecloud.javatechnicalinterview.domain.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultResponse {

    private UUID id;
    private String name;
    private Integer score;
    private LocalDate dateTaken;
}
