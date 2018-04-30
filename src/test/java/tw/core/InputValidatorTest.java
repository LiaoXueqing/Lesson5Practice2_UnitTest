package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertEquals;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    InputValidator inputValidator = new InputValidator();
    @Test
    public void should_return_true(){
        assertEquals(inputValidator.validate("3 4 5 6"),true);
    }

    @Test
    public void should_return_false(){
        assertEquals(inputValidator.validate("3 4 5 6 7"),false);
        assertEquals(inputValidator.validate("3 3 5 6 "),false);
        assertEquals(inputValidator.validate("3 4 5 10"),false);
    }

}
