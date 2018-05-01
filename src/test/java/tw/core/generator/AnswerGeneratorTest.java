package tw.core.generator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Before
    public void setup(){
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void should_return_generate_answer_correct() throws OutOfRangeAnswerException {
        String expectAnswer = "1 2 3 4";
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);
        Answer answer = answerGenerator.generate();
        assertEquals(answer.toString(),expectAnswer);
    }
    @Test
    public void should_return_an_OutOfRangeAnswerException() throws OutOfRangeAnswerException {
        String expectAnswer = "1 2 3 10";
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);
        expectedEx.expect(OutOfRangeAnswerException.class);
        expectedEx.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }

}

