package com.test1.student.service;

import com.test1.student.entity.StudentScore;
import com.test1.student.repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoreService {
    private final StudentScoreRepository studentScoreRepository;

    @Autowired
    public StudentScoreService(StudentScoreRepository studentScoreRepository) {
        this.studentScoreRepository = studentScoreRepository;
    }

    public List<StudentScore> getAllScores() {
        return studentScoreRepository.findAll();
    }

    public void addScore(StudentScore score) {
        studentScoreRepository.save(score);
    }

    public StudentScore getScoreById(Long id) {
        return studentScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy điểm với ID: " + id));
    }

    public void updateScore(StudentScore score) {
        if (!studentScoreRepository.existsById(score.getId())) {
            throw new RuntimeException("Không thể cập nhật, không tìm thấy điểm với ID: " + score.getId());
        }
        studentScoreRepository.save(score);
    }

    public void deleteScore(Long id) {
        if (!studentScoreRepository.existsById(id)) {
            throw new RuntimeException("Không thể xóa, không tìm thấy điểm với ID: " + id);
        }
        studentScoreRepository.deleteById(id);
    }
}
