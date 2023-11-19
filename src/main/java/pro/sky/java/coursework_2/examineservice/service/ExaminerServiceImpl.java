package pro.sky.java.coursework_2.examineservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.coursework_2.examineservice.domain.Question;
import pro.sky.java.coursework_2.examineservice.exceptions.AmountMoreSizeQuestionsException;
import pro.sky.java.coursework_2.examineservice.repository.JavaQuestionRepository;
import pro.sky.java.coursework_2.examineservice.repository.QuestionRepository;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final static String TEMPLATE_EXCEPTION = "Количестов вопросов %d не корректно задано.";
    private final QuestionService javaService;
    private final QuestionService mathService;

    @Autowired
    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaService,
                               @Qualifier("mathQuestionService") QuestionService mathService) {
        this.javaService = javaService;
        this.mathService = mathService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        List<QuestionService> services = List.of(javaService, mathService);
        int minElements = services.stream().mapToInt(i -> i.getAll().size()).min().orElse(0);
        int size = services.size();
        if (amount < 0 || amount > minElements / size) {
            throw new AmountMoreSizeQuestionsException(String.format(TEMPLATE_EXCEPTION, amount));
        }
        Set<Question> result = new HashSet<>();
        IntStream.range(0, amount).forEach(i -> {
            Question question = services.get(i % size).getRandomQuestion();
            while (result.contains(question)) {
                question = services.get(i % size).getRandomQuestion();
            }
            result.add(question);
        });
        return result;
    }
}
