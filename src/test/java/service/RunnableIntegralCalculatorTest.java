package service;

import calc.Function;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunnableIntegralCalculatorTest {
    private static final double EPS = 1e-5;
    RunnableIntegralCalculator calculator;

    @ParameterizedTest
    @CsvSource({"100, 3", "100, 10", "100, 20",
                "10_000, 3", "10_000, 10", "10_000, 20",
                "1_000_000, 3", "1_000_000, 10", "1_000_000, 20",
                "100_000_000, 3", "100_000_000, 10", "100_000_000, 20"})
    void calculateTest(long n, long threads) throws InterruptedException {
        calculator = new RunnableIntegralCalculator();
        double expected = 50000.0;
        double actual = calculator.calculate(EPS, Math.PI / 2 - EPS, n, Function::calculate, threads).result();
        double relativeError = Math.abs((expected - actual) / expected);
        assertEquals(1e-9, relativeError, EPS);
    }
}
