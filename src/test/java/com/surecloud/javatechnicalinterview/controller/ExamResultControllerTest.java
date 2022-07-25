package com.surecloud.javatechnicalinterview.controller;

import static com.surecloud.javatechnicalinterview.utils.ErrorConstants.RESULT_NOT_FOUND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.exception.ResourceNotFoundException;
import com.surecloud.javatechnicalinterview.service.ExamResultService;
import com.surecloud.javatechnicalinterview.util.ExamResultCreator;
import com.surecloud.javatechnicalinterview.util.TestConstants;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ExamResultControllerTest {

    @InjectMocks
    private ExamResultController examResultController;

    @Mock
    private ExamResultService examResultService;

    @Test
    @DisplayName("Add exam result when successful")
    void addExamResultWhenSuccessfull() {
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse expectedExamResultResponse = ExamResultCreator
            .createExamResultResponse(examResultRequest, UUID.randomUUID());

        when(examResultService.addExamResult(any()))
            .thenReturn(expectedExamResultResponse);

        ResponseEntity<ExamResultResponse> result = examResultController
            .addExamResult(examResultRequest);

        assertThat(result)
            .isNotNull();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(result.getBody())
            .isEqualTo(expectedExamResultResponse);
    }

    @Test
    @DisplayName("Find all exam result when successful")
    void findAllResultsWhenSuccessfull() {
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse examResultResponse1 = ExamResultCreator
            .createExamResultResponse(examResultRequest, UUID.randomUUID());

        ExamResultResponse examResultResponse2 = ExamResultCreator
            .createExamResultResponse(examResultRequest, UUID.randomUUID());

        List<ExamResultResponse> expectedExamResultResponse = Arrays.asList(examResultResponse1, examResultResponse2);

        when(examResultService.findAllExamResults())
            .thenReturn(expectedExamResultResponse);

        ResponseEntity<List<ExamResultResponse>> result = examResultController
            .findAllResults();

        assertThat(result)
            .isNotNull();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
            .isNotEmpty()
            .hasSize(2)
            .isEqualTo(expectedExamResultResponse);
    }

    @Test
    @DisplayName("Find exam result by id when successful")
    void findResultByIdWhenSuccessfull() {
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse expectedExamResultResponse = ExamResultCreator
            .createExamResultResponse(examResultRequest, UUID.randomUUID());

        when(examResultService.findExamResultById(any()))
            .thenReturn(expectedExamResultResponse);

        ResponseEntity<ExamResultResponse> result = examResultController
            .findResultById(expectedExamResultResponse.getId());

        assertThat(result)
            .isNotNull();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
            .isEqualTo(expectedExamResultResponse);
    }

    @Test
    @DisplayName("Find exam result by id when not found")
    void findResultByIdWhenNotFound() {
        UUID id = UUID.randomUUID();

        when(examResultService.findExamResultById(any()))
            .thenThrow(new ResourceNotFoundException(String.format(RESULT_NOT_FOUND_MESSAGE, id)));

        assertThatExceptionOfType(ResourceNotFoundException.class)
            .isThrownBy(() -> examResultController.findResultById(id))
            .withMessage(String.format(RESULT_NOT_FOUND_MESSAGE, id));
    }
}