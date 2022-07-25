package com.surecloud.javatechnicalinterview.controller;

import static com.surecloud.javatechnicalinterview.utils.Constants.ID_API;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.service.ExamResultService;
import com.surecloud.javatechnicalinterview.utils.Constants;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RESULTS_API)
public class ExamResultController {

    private final ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @PostMapping
    public ResponseEntity<ExamResultResponse> addExamResult(
        @RequestBody @Valid ExamResultRequest examResultRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(examResultService.addExamResult(examResultRequest));
    }

    @GetMapping
    public ResponseEntity<List<ExamResultResponse>> findAllResults() {
        return ResponseEntity.status(HttpStatus.OK).body(examResultService.findAllExamResults());
    }

    @GetMapping(ID_API)
    public ResponseEntity<ExamResultResponse> findResultById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(examResultService.findExamResultById(id));
    }
}
