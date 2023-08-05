package uz.pdp.app.lc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class LcApplicationTests {
    private final Calculator underTest = new Calculator();

    @Test
    void contextLoads() {
        int a = 10;
        int b = 15;
        int expected = 25;
        int result = underTest.add(a, b);
        assertThat(result).isEqualTo(expected);
    }

    class Calculator {
        int add(int a, int b){return a + b;}
    }





}
