package com.surecloud.javatechnicalinterview.service.mapper;

import com.surecloud.javatechnicalinterview.domain.entity.ExamResultEntity;
import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;

public class ExamResultMapper {

    public static ExamResultEntity mapExamResultRequestToEntity(ExamResultRequest examResultRequest) {
        return ExamResultEntity.builder()
            .name(examResultRequest.getName())
            .score(examResultRequest.getScore())
            .dateTaken(examResultRequest.getDateTaken())
            .build();
    }

    public static ExamResultResponse mapExamResultEntityToResponse(ExamResultEntity examResultEntity) {
        return ExamResultResponse.builder()
            .id(examResultEntity.getId())
            .name(examResultEntity.getName())
            .score(examResultEntity.getScore())
            .dateTaken(examResultEntity.getDateTaken())
            .build();
    }
}
