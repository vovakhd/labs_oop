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
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("1,g"));
    }
    @Test
    void test2() {
        assertEquals(324, strcalc.add("45,6,87,1,33,147,5"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("2,,3,2"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("w,2,3"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add(",3,"));
    }
    @Test
    void test3() {
        assertEquals(172, strcalc.add("45,6\n87\n1,33"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("1,\n"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("\n1\n4"));
        assertEquals(6, strcalc.add("1\n3,2"));
    }
}