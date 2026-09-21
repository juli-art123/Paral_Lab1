package calc;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.LongStream;

public class IntegralCalculator {
    private final double a;
    private final double b;
    private final long n;
    private final DoubleUnaryOperator f;

    public IntegralCalculator(double a, double b, long n, DoubleUnaryOperator f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    public double calculate() {
        double h = (b - a) / n;
        return LongStream.rangeClosed(0, n - 1).mapToDouble(i -> a + i * h).map(f).sum() * h;
    }
}
