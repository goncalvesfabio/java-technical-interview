package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import java.util.List;
import java.util.UUID;

public interface ExamResultService {

    ExamResultResponse addExamResult(ExamResultRequest examResultRequest);

    List<ExamResultResponse> findAllExamResults();

    ExamResultResponse findExamResultById(UUID id);
}
