package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void should_return_whether_generate_answer_correct() throws OutOfRangeAnswerException {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        Answer answer = answerGenerator.generate();

        long validateNum = answer.getNumList().stream()
                .map(num -> parseInt(num))
                .distinct()
                .filter(num -> num<10)
                .count();
        assertEquals(validateNum,4);
    }

}

