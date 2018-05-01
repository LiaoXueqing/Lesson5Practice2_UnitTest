package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private Answer answer;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_return_create_Answer(){
        answer = Answer.createAnswer("3 4 5 6");
        assertEquals(answer.toString(),"3 4 5 6");
    }

    @Test
    public void should_return_an_OutOfRangeAnswerException() throws OutOfRangeAnswerException {
        answer = Answer.createAnswer("11 5 6 5");
        expectedEx.expect(OutOfRangeAnswerException.class);
        expectedEx.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void should_return_Record(){
        Answer guess_answer = Answer.createAnswer("1 3 6 7");
        answer = Answer.createAnswer("1 2 3 4");
        int numOfA = answer.check(guess_answer).getValue()[0];
        int numOfB = answer.check(guess_answer).getValue()[1];
        assertEquals(numOfA,1);
        assertEquals(numOfB,1);
    }

    @Test
    public void should_return_indexOfNum(){
        answer = Answer.createAnswer("1 2 3 4");
        int indexOfNum = answer.getIndexOfNum("3");
        assertEquals(indexOfNum,2);
    }
}