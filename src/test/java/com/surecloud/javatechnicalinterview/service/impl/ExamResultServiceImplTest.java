package com.surecloud.javatechnicalinterview.service.impl;

import static com.surecloud.javatechnicalinterview.utils.ErrorConstants.RESULT_NOT_FOUND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.surecloud.javatechnicalinterview.domain.entity.ExamResultEntity;
import com.surecloud.javatechnicalinterview.domain.request.ExamResultRequest;
import com.surecloud.javatechnicalinterview.domain.response.ExamResultResponse;
import com.surecloud.javatechnicalinterview.exception.ResourceNotFoundException;
import com.surecloud.javatechnicalinterview.respository.ExamResultRepository;
import com.surecloud.javatechnicalinterview.service.ExamResultService;
import com.surecloud.javatechnicalinterview.util.ExamResultCreator;
import com.surecloud.javatechnicalinterview.util.TestConstants;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ExamResultServiceImplTest {

    private ExamResultService examResultService;

    @Mock
    private ExamResultRepository examResultRepositoryMock;

    @BeforeEach
    void setup() {
        openMocks(this);
        examResultService = new ExamResultServiceImpl(examResultRepositoryMock);
    }

    @Test
    @DisplayName("Add exam result when successful")
    void testAddExamResultWhenSuccessfull() {

        UUID id = UUID.randomUUID();
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse expectedExamResultResponse = ExamResultCreator
            .createExamResultResponse(examResultRequest, id);

        ExamResultEntity expectedExamResultEntity = ExamResultCreator
            .createExamResultEntity(id, TestConstants.NAME_TEST, TestConstants.SCORE_TEST,
                LocalDate.of(2022, 10, 03));

        when(examResultRepositoryMock.save(any()))
            .thenReturn(expectedExamResultEntity);

        ExamResultResponse result = examResultService
            .addExamResult(examResultRequest);

        assertThat(result)
            .isNotNull();

        assertThat(result.getId())
            .isNotNull()
            .isEqualTo(expectedExamResultResponse.getId());

    }

    @Test
    @DisplayName("Find all exam result when successful")
    void testFindAllResultsWhenSuccessfull() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse examResultResponse1 = ExamResultCreator
            .createExamResultResponse(examResultRequest, id1);

        ExamResultResponse examResultResponse2 = ExamResultCreator
            .createExamResultResponse(examResultRequest, id2);

        List<ExamResultResponse> expectedExamResultResponse = Arrays.asList(examResultResponse1, examResultResponse2);

        List<ExamResultEntity> expectedExamResultEntities = Arrays.asList(
            ExamResultCreator.createExamResultEntity(id1, TestConstants.NAME_TEST, TestConstants.SCORE_TEST,
                LocalDate.of(2022, 10, 03)),
            ExamResultCreator.createExamResultEntity(id2, TestConstants.NAME_TEST, TestConstants.SCORE_TEST,
                LocalDate.of(2022, 10, 03)));

        when(examResultRepositoryMock.findAll())
            .thenReturn(expectedExamResultEntities);

        List<ExamResultResponse> result = examResultService
            .findAllExamResults();

        assertThat(result)
            .isNotNull()
            .isNotEmpty()
            .hasSize(2);
    }

    @Test
    @DisplayName("Find exam result by id when successful")
    void testFindResultByIdWhenSuccessfull() {
        UUID id = UUID.randomUUID();
        ExamResultRequest examResultRequest = ExamResultCreator
            .createExamResultRequest(TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        ExamResultResponse expectedExamResultResponse = ExamResultCreator
            .createExamResultResponse(examResultRequest, id);

        ExamResultEntity expectedExamResultEntity = ExamResultCreator
            .createExamResultEntity(id, TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate.of(2022, 10, 03));

        when(examResultRepositoryMock.findById(any()))
            .thenReturn(Optional.of(expectedExamResultEntity));

        ExamResultResponse result = examResultService
            .findExamResultById(expectedExamResultResponse.getId());

        assertThat(result)
            .isNotNull();

        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Find exam result by id when not found")
    void testFindResultByIdWhenNotFound() {
        UUID id = UUID.randomUUID();

        when(examResultRepositoryMock.findById(any()))
            .thenThrow(new ResourceNotFoundException(String.format(RESULT_NOT_FOUND_MESSAGE, id)));

        assertThatExceptionOfType(ResourceNotFoundException.class)
            .isThrownBy(() -> examResultService.findExamResultById(id))
            .withMessage(String.format(RESULT_NOT_FOUND_MESSAGE, id));
    }
}