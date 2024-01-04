package com.practice.qna01.Answer;

import com.practice.qna01.Question.Question;
import com.practice.qna01.Question.QuestionService;
import com.practice.qna01.user.SiteUser;
import com.practice.qna01.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated")
    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
                         BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.detail(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            return "/question/detail";
        }
        this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}
