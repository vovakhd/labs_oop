package lab1;

import static org.junit.jupiter.api.Assertions.*;
import lab1.StringCalculator;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest{

    StringCalculator strcalc = new StringCalculator();
    @Test
    void test1() {
        assertEquals(0, strcalc.add(""));
        assertEquals(1, strcalc.add("1"));
        assertEquals(3, strcalc.add("1,2"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("1,g,3"));
    }
}