package calc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FunctionTest {
    private final static double EPS = 1e-5;

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(Math.PI / 4, 1),
                arguments(Math.PI / 2 - EPS, 2.50000000026996e+9),
                arguments(EPS, 2.50000000033333e+9)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testCalculate(double x, double expected) {
        double actual = Function.calculate(x);
        if (expected > 10_000) {
            double relativeError = Math.abs((expected - actual) / expected);
            assertEquals(1e-9, relativeError, EPS);
        } else {
            assertEquals(expected, actual, EPS);
        }
    }
}
