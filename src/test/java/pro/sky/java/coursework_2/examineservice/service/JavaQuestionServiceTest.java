package pro.sky.java.coursework_2.examineservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.coursework_2.examineservice.service.constants.ConstantsQuestionService;
import pro.sky.java.coursework_2.examineservice.domain.Question;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JavaQuestionServiceTest {

    QuestionService questionService;

    @BeforeEach
    public void initQuestionService() {
        questionService = new JavaQuestionService();
        ConstantsQuestionService.QUESTIONS_ALL.forEach((q) -> questionService.add(q));
    }

    @Test
    public void whenAddQuestionsThenEqualsSet() {
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(ConstantsQuestionService.QUESTIONS_ALL);
    }

    @Test
    public void whenRemoveOneQuestionThenEqualsSet() {
        questionService.remove(ConstantsQuestionService.QUESTION_OBJ1);
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(ConstantsQuestionService.QUESTIONS_NOT_FIRST);
    }

    @Test
    public void whenGetRandomQuestionThenContainInSet() {
        Question actual = questionService.getRandomQuestion();
        assertTrue(ConstantsQuestionService.QUESTIONS_ALL.contains(actual));
    }
}