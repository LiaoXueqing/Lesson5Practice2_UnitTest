package tw.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private Game game;
    private Game gameSpy;
    private GameController gameController;
    private InputCommand command;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() throws Exception {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);

        System.setOut(new PrintStream(outContent));

        String expectAnswer = "1 2 3 4";
        when(randomIntGenerator.generateNums(10, 4)).thenReturn(expectAnswer);
        answerGenerator.generate();
        game = new Game(answerGenerator);
        gameSpy = spy(game);
        command = mock(InputCommand.class);
    }

    @Test
    public void should_print_game_success_information() throws IOException {
        when(gameSpy.checkCoutinue()).thenReturn(false);
        when(gameSpy.checkStatus()).thenReturn(SUCCESS);
        when(command.input()).thenReturn(Answer.createAnswer("1 2 3 4"));

        gameController = new GameController(gameSpy,new GameView());
        gameController.play(command);

        String result = "Game Status: success\n";
        assertEquals(systemOut(),result);
    }

    @Test
    public void should_print_game_fail_information() throws IOException {
        when(gameSpy.checkCoutinue()).thenReturn(false);
        when(gameSpy.checkStatus()).thenReturn(FAIL);
        when(command.input()).thenReturn(Answer.createAnswer("1 6 7 9"));

        gameController = new GameController(gameSpy,new GameView());
        gameController.play(command);

        String result = "Game Status: fail\n";
        assertEquals(systemOut(),result);
    }

    @Test
    public void should_print_game_history_information() throws IOException {
        when(gameSpy.checkCoutinue()).thenReturn(true).thenReturn(false);
        when(command.input()).thenReturn(Answer.createAnswer("2 4 7 8"));

        gameController = new GameController(gameSpy,new GameView());
        gameController.play(command);
        String result ="Guess Result: 0A2B\n"+"Guess History:\n"+
                "[Guess Numbers: 2 4 7 8, Guess Result: 0A2B]\n"+
                "Game Status: continue\n" ;
        assertEquals(systemOut(),result);
    }
    private String systemOut() {
        return outContent.toString();
    }
}