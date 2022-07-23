package com.surecloud.javatechnicalinterview.domain.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultRequest {

    private String name;
    private Integer score;
    private LocalDate dateTaken;

}
