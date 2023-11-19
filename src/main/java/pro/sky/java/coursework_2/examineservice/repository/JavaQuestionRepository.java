package pro.sky.java.coursework_2.examineservice.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.coursework_2.examineservice.domain.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static pro.sky.java.coursework_2.examineservice.repository.ConstantsQuestionRepository.*;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void init() {
        questions.addAll(QUESTIONS_ALL);
    }

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        questions.add(result);
        return result;
    }

    @Override
    public Question add(Question question) {
        return add(question.getQuestion(), question.getAnswer());
    }

    @Override
    public Question remove(Question question) {
        Question result = null;
        if (questions.remove(question)) {
            result = question;
        }
        return result;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

}
