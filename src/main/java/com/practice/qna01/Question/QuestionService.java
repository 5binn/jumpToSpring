package com.practice.qna01.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> list() {
        return this.questionRepository.findAll();
    }

    public Question detail(Integer id) {
        Optional<Question> questionOptional = this.questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            return questionOptional.get();
        } else
            return null;
    }

    public void create(String subject, String content) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }
}
