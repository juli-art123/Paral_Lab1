package calc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegralCalculatorTest {
    private static final double EPS = 1e-5;
    IntegralCalculator calculator;

    @ParameterizedTest
    @CsvSource({"100", "10_000", "1_000_000", "10_000_000", "100_000_000"})
    void calculateTest(int n) {
        calculator = new IntegralCalculator(EPS, Math.PI / 2 - EPS, n, Function::calculate);
        double expected = 50000.0;
        double actual = calculator.calculate();
        double relativeError = Math.abs((expected - actual) / expected);
        assertEquals(0, relativeError, EPS);
    }
}
