package com.surecloud.javatechnicalinterview.respository;

import static org.assertj.core.api.Assertions.assertThat;

import com.surecloud.javatechnicalinterview.domain.entity.ExamResultEntity;
import com.surecloud.javatechnicalinterview.util.ExamResultCreator;
import com.surecloud.javatechnicalinterview.util.TestConstants;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ExamResultRepositoryTest {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Test
    @DisplayName("Add exam result when successful")
    void testAddResultWhenSuccessful() {
        ExamResultEntity examResultEntity = ExamResultCreator
            .createExamResultEntity(null, TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate
                .of(2022, 10, 03));

        ExamResultEntity result = examResultRepository.save(examResultEntity);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(examResultEntity.getName());
        assertThat(result.getScore()).isEqualTo(examResultEntity.getScore());
        assertThat(result.getDateTaken()).isEqualTo(examResultEntity.getDateTaken());
    }

    @Test
    @DisplayName("Find all exam results when successful")
    void testFindAllResultWhenSuccessfull() {

        ExamResultEntity examResultEntity1 = ExamResultCreator
            .createExamResultEntity(null, TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate
                .of(2022, 10, 03));

        ExamResultEntity resultSaved1 = examResultRepository.save(examResultEntity1);

        ExamResultEntity examResultEntity2 = ExamResultCreator
            .createExamResultEntity(null, TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate
                .of(2022, 10, 03));

        ExamResultEntity resultSaved2 = examResultRepository.save(examResultEntity2);

        List<ExamResultEntity> result = examResultRepository
            .findAll();

        assertThat(result).isNotEmpty().hasSize(2);
        assertThat(result.get(0)).isNotNull();
        assertThat(result.get(0).getId()).isNotNull().isEqualTo(resultSaved1.getId());

        assertThat(result.get(1)).isNotNull();
        assertThat(result.get(1).getId()).isNotNull().isEqualTo(resultSaved2.getId());


    }

    @Test
    @DisplayName("Find exam result by id when successful")
    void testFindResultByIdWhenSuccessfull() {

        ExamResultEntity examResultEntity = ExamResultCreator
            .createExamResultEntity(null, TestConstants.NAME_TEST, TestConstants.SCORE_TEST, LocalDate
                .of(2022, 10, 03));

        ExamResultEntity resultSaved = examResultRepository.save(examResultEntity);

        Optional<ExamResultEntity> result = examResultRepository
            .findById(resultSaved.getId());

        assertThat(result).isPresent();

        assertThat(result.get().getId()).isEqualTo(resultSaved.getId());
        assertThat(result.get().getName()).isEqualTo(resultSaved.getName());
        assertThat(result.get().getScore()).isEqualTo(resultSaved.getScore());
        assertThat(result.get().getDateTaken()).isEqualTo(resultSaved.getDateTaken());
    }

}