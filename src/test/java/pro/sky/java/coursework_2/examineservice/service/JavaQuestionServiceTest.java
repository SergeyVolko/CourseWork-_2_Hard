package pro.sky.java.coursework_2.examineservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework_2.examineservice.repository.JavaQuestionRepository;
import pro.sky.java.coursework_2.examineservice.service.constants.ConstantsQuestionService;
import pro.sky.java.coursework_2.examineservice.domain.Question;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static pro.sky.java.coursework_2.examineservice.service.constants.ConstantsQuestionService.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    JavaQuestionRepository repository;
    @InjectMocks
    QuestionService questionService;

    @Test
    public void whenAddOneQuestionThenEqualsSet() {
        when(repository.add(QUESTION_OBJ1)).thenReturn(QUESTION_OBJ1);
        assertEquals(QUESTION_OBJ1, questionService.add(QUESTION_OBJ1));
    }

    @Test
    public void whenGetAllQuestionsThenEqualsSet() {
        when(repository.getAll()).thenReturn(QUESTIONS_ALL);
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(QUESTIONS_ALL);
    }

    @Test
    public void whenRemoveOneQuestionThenEqualsSet() {
        when(repository.remove(QUESTION_OBJ1)).thenReturn(QUESTION_OBJ1);
        assertEquals(QUESTION_OBJ1, questionService.remove(QUESTION_OBJ1));
    }

    @Test
    public void whenGetRandomQuestionThenContainInSet() {
        when(repository.getAll()).thenReturn(QUESTIONS_ALL);
        Question actual = questionService.getRandomQuestion();
        assertTrue(QUESTIONS_ALL.contains(actual));
    }
}