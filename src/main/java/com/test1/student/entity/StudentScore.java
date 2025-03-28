package com.test1.student.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_scores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    private Double score1;
    private Double score2;
    public Long getId() {
        return id;
    }

    public Double getGrade() {
        return (0.3 * score1) + (0.7 * score2);
    }
}
