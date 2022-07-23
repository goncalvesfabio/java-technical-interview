package com.surecloud.javatechnicalinterview.service.impl;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.service.ExamResult;
import java.util.UUID;
import org.springframework.data.domain.Page;

public class ExamResultImpl implements ExamResult {

    @Override
    public ExamResultResponse addExamResult(ExamResultRequest examResultRequest) {
        return null;
    }

    @Override
    public Page<ExamResultResponse> findAllExamResults() {
        return null;
    }

    @Override
    public ExamResultResponse findExamResultById(UUID id) {
        return null;
    }
}
