package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
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

    @Before
    public void setup() throws OutOfRangeAnswerException {
        System.setOut(new PrintStream(outContent));
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game = new Game(answerGenerator);
        gameSpy = spy(game);
        command = mock(InputCommand.class);
    }

    @Test
    public void should_print_game_success_or_fail_information() throws IOException {
        when(gameSpy.checkCoutinue()).thenReturn(false);
        when(gameSpy.checkStatus()).thenReturn(SUCCESS);
        when(command.input()).thenReturn(Answer.createAnswer("1 2 3 4"));
        gameController = new GameController(gameSpy,new GameView());
        gameController.play(command);
        String result = "Game Status: success\r\n";
        assertEquals(systemOut(),result);
    }
    @Test
    public void should_print_game_information() throws IOException {
        when(gameSpy.checkCoutinue()).thenReturn(true).thenReturn(false);
        when(command.input()).thenReturn(Answer.createAnswer("2 4 7 8"));

        gameController = new GameController(gameSpy,new GameView());
        gameController.play(command);
        String result ="Guess Result: 0A2B\r\n"+"Guess History:\r\n"+
                "[Guess Numbers: 2 4 7 8, Guess Result: 0A2B]\r\n"+
                "Game Status: continue\r\n" ;
        assertEquals(systemOut(),result);
    }
    private String systemOut() {
        return outContent.toString();
    }
}