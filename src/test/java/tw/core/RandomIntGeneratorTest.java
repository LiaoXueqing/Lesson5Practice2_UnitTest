package tw.core;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_return_a_generate_nums() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        String numOfGenerate = randomIntGenerator.generateNums(10, 4);

        List<String> numOfGenerateList =
                Arrays.stream(numOfGenerate.split(" "))
                        .collect(Collectors.toList());
        long validatedNum = numOfGenerateList
                .stream()
                .map(num->parseInt(num))
                .distinct()
                .filter(num->num<10)
                .count();
        assertEquals(validatedNum,4);
    }
    @Test
    public void should_return_an_IllegalArgumentException(){
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Can't ask for more numbers than are available");
        randomIntGenerator.generateNums(10,11);
    }
}