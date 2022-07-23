package com.surecloud.javatechnicalinterview.service;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ExamResult {

    ExamResultResponse addExamResult(ExamResultRequest examResultRequest);

    Page<ExamResultResponse> findAllExamResults();

    ExamResultResponse findExamResultById(UUID id);
}
