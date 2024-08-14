import org.example.Calculator;
import org.example.Values;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("smoke")
public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Should calculate correct value for big size")
    public void shouldCalculateCorrectValueForBigSize() {
        Values values = createValues(23, "big", "no", 3);
        int result = calculator.calculate(values);
        assertEquals(560, result);
    }

    @Test
    @DisplayName("Should calculate correct value for small size")
    public void shouldCalculateCorrectValueForSmallSize() {
        Values values = createValues(1, "small", "yes", 1);
        int result = calculator.calculate(values);
        assertEquals(450, result);
    }

    @Test
    @DisplayName("Should throw exception for null values")
    public void shouldThrowExceptionForNullValues() {
        Values values = createValues(1, null, "yes", 1);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Input parameters cannot be null", thrown.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Should calculate correct values for various parameters")
    @CsvSource({
            "23, big, no, 3, 560",
            "1, small, yes, 1, 450",
            "5, small, no, 4, 400",
            "30, big, yes, 2, 840"
    })
    public void shouldCalculateCorrectValuesForVariousParameters(int distance, String size, String fragility, int workload, int expected) {
        Values values = createValues(distance, size, fragility, workload);
        int result = calculator.calculate(values);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw exception for invalid size")
    public void shouldThrowExceptionForInvalidSize() {
        Values values = createValues(10, "invalidSize", "no", 1);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Invalid size specified", thrown.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for invalid workload")
    public void shouldThrowExceptionForInvalidWorkload() {
        Values values = createValues(10, "small", "no", 5);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            calculator.calculate(values);
        });
        assertEquals("Invalid workload value", thrown.getMessage());
    }

    private Values createValues(int distance, String size, String fragility, int workload) {
        return new Values(distance, size, fragility, workload);
    }
}
