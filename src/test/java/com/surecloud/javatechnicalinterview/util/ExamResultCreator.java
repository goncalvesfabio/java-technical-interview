package com.surecloud.javatechnicalinterview.util;


import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import java.time.LocalDate;
import java.util.UUID;

public class ExamResultCreator {

    public static ExamResultRequest createExamResultRequest(String name, Integer score, LocalDate dateTaken) {
        return ExamResultRequest.builder()
            .name(name)
            .score(score)
            .dateTaken(dateTaken)
            .build();
    }

    public static ExamResultResponse createExamResultResponse(ExamResultRequest examResultRequest, UUID id) {
        return ExamResultResponse.builder()
            .id(id)
            .name(examResultRequest.getName())
            .score(examResultRequest.getScore())
            .dateTaken(examResultRequest.getDateTaken())
            .build();
    }
}
