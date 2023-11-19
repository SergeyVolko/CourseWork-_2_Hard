package pro.sky.java.coursework_2.examineservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework_2.examineservice.service.constants.ConstantsQuestionService;
import pro.sky.java.coursework_2.examineservice.exceptions.AmountMoreSizeQuestionsException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pro.sky.java.coursework_2.examineservice.service.constants.ConstantsQuestionService.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock(name = "javaService")
    QuestionService javaService;
    @Mock(name = "mathService")
    QuestionService mathService;
    ExaminerServiceImpl examinerService;

    @BeforeEach
    public void initMock() {
        examinerService = new ExaminerServiceImpl(javaService, mathService);

    }

    @Test
    public void whenCountsThenUniq() {
        when(javaService.getAll()).thenReturn(QUESTIONS_ALL);
        when(mathService.getAll()).thenReturn(MATH_QUESTIONS_ALL);
        when(javaService.getRandomQuestion()).thenReturn(QUESTION_OBJ1, QUESTION_OBJ2, QUESTION_OBJ3);
        when(mathService.getRandomQuestion()).thenReturn(MATH_QUESTION_OBJ1, MATH_QUESTION_OBJ2);
        assertThat(examinerService.getQuestions(AMOUNT)).containsExactlyInAnyOrderElementsOf(MATH_JAVA_QUESTIONS);
    }

    @Test
    public void whenCountsThenUniqException() {
        assertThrows(AmountMoreSizeQuestionsException.class,
                () -> examinerService.getQuestions(ConstantsQuestionService.WRONG_AMOUNT));
    }
}