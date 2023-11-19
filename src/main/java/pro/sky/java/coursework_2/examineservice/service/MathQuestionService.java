package pro.sky.java.coursework_2.examineservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.coursework_2.examineservice.domain.Question;
import pro.sky.java.coursework_2.examineservice.repository.QuestionRepository;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {
    private static final Random RANDOM = new Random();
    private final QuestionRepository repository;

    private  List<Question> shuffleQuestion = new LinkedList<>();
    private boolean isChanged = false;

    @Autowired
    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        isChanged = true;
        return repository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        isChanged = true;
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        isChanged = true;
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (isChanged || shuffleQuestion.isEmpty()) {
            shuffleQuestion = shuffle(repository.getAll());
            isChanged = false;
        }
        return shuffleQuestion.remove(0);
    }

    private List<Question> shuffle(Collection<Question> questions) {
        Question[] result = (Question[]) questions.toArray();
        for (int i = 0; i < result.length; i++) {
            int index = i + RANDOM.nextInt(result.length - i);
            Question tmp = result[i];
            result[i] = result[index];
            result[index] = tmp;
        }
        return Arrays.asList(result);
    }
}
