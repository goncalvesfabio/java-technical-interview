package com.surecloud.javatechnicalinterview.controller;

import static com.surecloud.javatechnicalinterview.utils.Constants.ID_API;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.utils.Constants;
import java.net.URI;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RESULTS_API)
public class ExamResultController {

    @PostMapping
    public ResponseEntity<ExamResultResponse> addExamResult(@RequestBody @Validated ExamResultRequest examResultRequest) {
        return ResponseEntity.created(URI.create(Constants.RESULTS_API)).body(null);
    }

    @GetMapping
    public ResponseEntity<Page<ExamResultResponse>> findAllResults() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(ID_API)
    public ResponseEntity<ExamResultResponse> findResultById(@PathVariable UUID id) {
        return ResponseEntity.ok(null);
    }
}
