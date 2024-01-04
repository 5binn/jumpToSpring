package com.practice.qna01.Question;

import com.practice.qna01.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> list() {
        return this.questionRepository.findAll();
    }
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question detail(Integer id) {
        Optional<Question> questionOptional = this.questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            return questionOptional.get();
        } else
            return null;
    }

    public void create(String subject, String content, SiteUser author) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(author);
        this.questionRepository.save(question);
    }
}
