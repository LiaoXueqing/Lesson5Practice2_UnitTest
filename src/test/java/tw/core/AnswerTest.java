package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void should_return_create_Answer(){
        Answer answer = Answer.createAnswer("3 4 5 6");
        assertEquals(answer.toString(),"3 4 5 6");
    }

    @Test
    public void should_return_create_Answer2(){
        List<String> resultList = Arrays.asList("1","2","3","4");
        List<String> actualList = Answer.createAnswer("1 2 3 4").getNumList();
        assertEquals(resultList,actualList);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void whether_throw_exception() throws OutOfRangeAnswerException {
        Answer inputAnswer = new Answer();
        List < String > inputAnswerList = Arrays.asList("11", "5", "6", "5");
        inputAnswer.setNumList(inputAnswerList);
        expectedEx.expect(OutOfRangeAnswerException.class);
        expectedEx.expectMessage("Answer format is incorrect");
        inputAnswer.validate();
    }

    @Test
    public void should_return_Record(){
        Answer inputAnswer = new Answer();
        List<String> inputAnswerList = Arrays.asList("1","3","6","7");
        inputAnswer.setNumList(inputAnswerList);

        Answer answer = new Answer();
        List<String> correctAnswerList = Arrays.asList("1","2","3","4");
        answer.setNumList(correctAnswerList);

        int numOfA = answer.check(inputAnswer).getValue()[0];
        int numOfB = answer.check(inputAnswer).getValue()[1];
        assertEquals(numOfA,1);
        assertEquals(numOfB,1);
    }

    @Test
    public void should_return_indexOfNum(){
        Answer answer = new Answer();
        List<String> correctAnswerList = Arrays.asList("1","2","3","4");
        answer.setNumList(correctAnswerList);
        int indexOfNum = answer.getIndexOfNum("3");
        assertEquals(indexOfNum,2);
    }

}