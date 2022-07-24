package com.surecloud.javatechnicalinterview.respository;

import com.surecloud.javatechnicalinterview.domain.entity.ExamResultEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, UUID> {

}
