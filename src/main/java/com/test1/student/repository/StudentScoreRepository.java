package com.test1.student.repository;

import com.test1.student.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
    List<StudentScore> findByStudentId(Long studentId);
    List<StudentScore> findBySubjectId(Long subjectId);
}
