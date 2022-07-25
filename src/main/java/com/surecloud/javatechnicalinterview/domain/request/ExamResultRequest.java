package com.surecloud.javatechnicalinterview.domain.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultRequest {

    @NotNull(message = "The field name is mandatory")
    private String name;

    @NotNull(message = "The field score is mandatory")
    private Integer score;

    @NotNull(message = "The field dateTaken is mandatory")
    private LocalDate dateTaken;

}
