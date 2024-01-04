package com.practice.qna01.Answer;

import com.practice.qna01.Question.Question;
import com.practice.qna01.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;
}
