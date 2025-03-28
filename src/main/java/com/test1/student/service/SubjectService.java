package com.test1.student.service;

import com.test1.student.entity.Subject;
import com.test1.student.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học với ID: " + id));
    }

    public void updateSubject(Subject subject) {
        if (!subjectRepository.existsById(subject.getId())) {
            throw new RuntimeException("Không thể cập nhật, không tìm thấy môn học với ID: " + subject.getId());
        }
        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Không thể xóa, không tìm thấy môn học với ID: " + id);
        }
        subjectRepository.deleteById(id);
    }
}
