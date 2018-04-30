package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static tw.core.GameStatus.CONTINUE;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;
    private Answer inputAnswer = new Answer();

    @Before
    public void setup() throws OutOfRangeAnswerException{
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        game = new Game(answerGenerator);
    }

    @Test
    public void should_return_guess_result(){
        List<String> inputAnswerList = Arrays.asList("1","5","6","7");
        inputAnswer.setNumList(inputAnswerList);
        String result = game.guess(inputAnswer).getResult();
        String regEx = "^\\dA\\dB$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(result);
        boolean isFormatCorrect = matcher.matches();
        assertEquals(isFormatCorrect,true);
    }

    @Test
    public void should_return_guess_result_all(){
        List<String> inputAnswerList = Arrays.asList("1","5","6","7");
        inputAnswer.setNumList(inputAnswerList);
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
    public void should_return_status(){
        Answer inputAnswer1 = new Answer();
        List<String> inputAnswerList1 = Arrays.asList("1","5","6","7");
        inputAnswer1.setNumList(inputAnswerList1);
        Answer inputAnswer2 = new Answer();
        List<String> inputAnswerList2 = Arrays.asList("1","2","3","4");
        inputAnswer2.setNumList(inputAnswerList2);
        game.guess(inputAnswer1);
        game.guess(inputAnswer2);
        String status = game.checkStatus();
        assertEquals(status,CONTINUE);
    }

}
