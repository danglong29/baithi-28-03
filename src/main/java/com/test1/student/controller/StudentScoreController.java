package com.test1.student.controller;

import com.test1.student.entity.StudentScore;
import com.test1.student.service.StudentScoreService;
import com.test1.student.service.StudentService;
import com.test1.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/scores")
public class StudentScoreController {
    private final StudentScoreService studentScoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentScoreController(StudentScoreService studentScoreService, StudentService studentService, SubjectService subjectService) {
        this.studentScoreService = studentScoreService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String listScores(Model model) {
        List<StudentScore> scores = studentScoreService.getAllScores();
        model.addAttribute("scores", scores);
        return "scores/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("score", new StudentScore());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "scores/form";
    }

    @PostMapping("/save")
    public String saveScore(@ModelAttribute StudentScore score) {
        studentScoreService.addScore(score);
        return "redirect:/scores";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentScore score = studentScoreService.getScoreById(id);
        model.addAttribute("score", score);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "scores/form";
    }

    @PostMapping("/update")
    public String updateScore(@ModelAttribute StudentScore score) {
        studentScoreService.updateScore(score);
        return "redirect:/scores";
    }

    @GetMapping("/delete/{id}")
    public String deleteScore(@PathVariable Long id) {
        studentScoreService.deleteScore(id);
        return "redirect:/scores";
    }
}
