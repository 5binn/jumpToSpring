package com.practice.qna01.Answer;

import com.practice.qna01.Question.Question;
import com.practice.qna01.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}
