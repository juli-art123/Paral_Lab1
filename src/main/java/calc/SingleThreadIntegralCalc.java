package calc;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;

public class SingleThreadIntegralCalc implements Runnable {
    private final IntegralCalculator calculator;
    private final DoubleConsumer consumer;

    public SingleThreadIntegralCalc(double a, double b, long n, DoubleUnaryOperator f, DoubleConsumer consumer) {
        this.calculator = new IntegralCalculator(a, b, n, f);
        this.consumer = consumer;
    }

    @Override
    public void run() {
        double v = calculator.calculate();
        consumer.accept(v);
    }
}
