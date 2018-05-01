package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.core.GameStatus.*;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;
    private Answer inputAnswer = new Answer();
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Before
    public void setup() throws OutOfRangeAnswerException{
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
        when(randomIntGenerator.generateNums(10, 4)).thenReturn("2 4 3 6");
        game = new Game(answerGenerator);
    }

    @Test
    public void should_return_0A1B(){
        inputAnswer = Answer.createAnswer("1 5 6 7");
        String result = game.guess(inputAnswer).getResult();
        assertEquals("0A1B",result);
    }

    @Test
    public void should_return_guess_result_all(){
        inputAnswer = Answer.createAnswer("1 5 6 7");
        String result = game.guess(inputAnswer).getResult();

        String[] resultCollection = {"0A0B", "1A0B", "2A0B", "3A0B", "4A0B", "0A1B", "1A1B", "2A1B", "0A2B", "1A2B", "2A2B", "0A3B", "1A3B", "0A4B"};
        boolean isFormatCorrect = false;
        for (int i = 0; i < resultCollection.length; i++) {
            if (resultCollection[i].equals(result)) {
                isFormatCorrect = true;
            }
        }
        assertTrue(isFormatCorrect);
    }

    @Test
    public void should_return_status_continue(){
        Answer inputAnswer1 = Answer.createAnswer("1 5 6 7");
        Answer inputAnswer2 = Answer.createAnswer("1 2 3 4");
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        String status = game.checkStatus();
        assertEquals(status,CONTINUE);
    }
    @Test
    public void should_return_status_success(){
        Answer inputAnswer1 = Answer.createAnswer("2 4 3 6");
        game.guess(inputAnswer1);
        String status = game.checkStatus();
        assertEquals(status,SUCCESS);
    }
    @Test
    public void should_return_status_fail(){
        game.guess(Answer.createAnswer("1 2 3 4"));
        game.guess(Answer.createAnswer("2 3 4 5"));
        game.guess(Answer.createAnswer("1 7 6 9"));
        game.guess(Answer.createAnswer("2 4 1 7"));
        game.guess(Answer.createAnswer("3 5 9 2"));
        game.guess(Answer.createAnswer("6 2 8 3"));
        String status = game.checkStatus();
        assertEquals(status,FAIL);
    }

}
