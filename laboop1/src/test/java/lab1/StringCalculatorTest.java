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
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("w,2,3"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add(",3,"));
    }
    @Test
    void test3() {
        assertEquals(172, strcalc.add("45,6\n87\n1,33"));
        //assertThrows(IllegalArgumentException.class, () -> strcalc.add("1\n,"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("\n1\n4"));
        assertEquals(6, strcalc.add("1\n3,2"));
    }
    @Test
    void test4() {
        assertEquals(16, strcalc.add("//;\n1;2;5;6;2"));
        assertEquals(16, strcalc.add("//;\n1;2;5,6\n2"));
        assertEquals(16, strcalc.add("//.\n1.2\n5,6\n2"));
    }
    @Test
    void test5() {
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("//;\n1;-2;5;-6;2"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("1,-2\n7,-4"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("//;\n-1;-2;-5,-6\n-2"));
    }
    @Test
    void test6() {
        assertEquals(1789, strcalc.add("1000,1111\n789,7171"));
        assertEquals(0, strcalc.add("1101,1001\n7777,4012"));
        assertEquals(2118, strcalc.add("//;\n100;999;109,1099\n910"));
    }
    @Test
    void test7() {
        assertEquals(8, strcalc.add("//[=]\n1=2===3==2"));
        assertEquals(114, strcalc.add("//[*]\n100*******2,3\n2*7"));
        assertEquals(2118, strcalc.add("//[;]\n100;999;109,1099\n910"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("//[;\n100;999;109,1099\n910"));
    }
    @Test
    void test8() {
        assertEquals(10, strcalc.add("//[*][-][%]\n1*2-3%4"));
        assertEquals(115, strcalc.add("//[*][+][^]\n100^2,3\n2*7+1"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("//;][/]\n100;999;109/1099/910"));
        assertThrows(IllegalArgumentException.class, () -> strcalc.add("//[*][;\n100;999*109,1099\n910"));
    }
}