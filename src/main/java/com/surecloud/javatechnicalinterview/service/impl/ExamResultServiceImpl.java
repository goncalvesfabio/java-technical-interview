package com.surecloud.javatechnicalinterview.service.impl;

import com.surecloud.javatechnicalinterview.domain.entity.ExamResultEntity;
import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.exception.ResourceNotFoundException;
import com.surecloud.javatechnicalinterview.respository.ExamResultRepository;
import com.surecloud.javatechnicalinterview.service.ExamResultService;
import com.surecloud.javatechnicalinterview.service.mapper.ExamResultMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;

    public ExamResultServiceImpl(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    @Override
    public ExamResultResponse addExamResult(ExamResultRequest examResultRequest) {
        ExamResultEntity examResultEntity = examResultRepository
            .save(ExamResultMapper.mapExamResultRequestToEntity(examResultRequest));

        return ExamResultMapper.mapExamResultEntityToResponse(examResultEntity);
    }

    @Override
    public List<ExamResultResponse> findAllExamResults() {
        return examResultRepository.findAll().stream()
            .map(ExamResultMapper::mapExamResultEntityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public ExamResultResponse findExamResultById(UUID id) {
        return ExamResultMapper.mapExamResultEntityToResponse(examResultRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Result with id %s not found", id))));
    }
}
