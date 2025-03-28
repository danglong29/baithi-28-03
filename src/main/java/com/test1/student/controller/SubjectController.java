package com.test1.student.controller;

import com.test1.student.entity.Subject;
import com.test1.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String listSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/form";
    }

    @PostMapping("/save")
    public String saveSubject(@ModelAttribute Subject subject) {
        subjectService.addSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "subjects/form";
    }

    @PostMapping("/update")
    public String updateSubject(@ModelAttribute Subject subject) {
        subjectService.updateSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}
