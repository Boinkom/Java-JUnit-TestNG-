import org.example.Calculator;
import org.example.Values;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("smoke")
public class CalculatorTest {
    private Calculator calculator;
    private Values values;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void test1() {
        values = new Values(23, "big", "no", 3);
        int result = calculator.calculate(values);
        assertEquals(560, result);
    }

    @Test
    public void test2() {
        values = new Values(1, "small", "yes", 1);
        int result = calculator.calculate(values);
        assertEquals(450, result);
    }

    @Test
    @DisplayName("Throw exception for null values")
    public void test3() {
        values = new Values(1, null, "yes", 1);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Input parameters cannot be null", thrown.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "23, big, no, 3, 560",
            "1, small, yes, 1, 450",
            "5, small, no, 4, 400",
            "30, big, yes, 2, 840"
    })
    public void testCalculateWithParameters(int distance, String size, String fragility, int workload, int expected) {
        values = new Values(distance, size, fragility, workload);
        int result = calculator.calculate(values);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Throw exception for invalid size")
    public void testInvalidSize() {
        values = new Values(10, "invalidSize", "no", 1);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Invalid size specified", thrown.getMessage());
    }

    @Test
    @DisplayName("Throw exception for invalid workload")
    public void testInvalidWorkload() {
        values = new Values(10, "small", "no", 5);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Invalid workload value", thrown.getMessage());
    }
}
