package com.test1.student.service;

import com.test1.student.entity.Student;
import com.test1.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
    }

    public void updateStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            throw new RuntimeException("Không thể cập nhật, không tìm thấy sinh viên với ID: " + student.getId());
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Không thể xóa, không tìm thấy sinh viên với ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
