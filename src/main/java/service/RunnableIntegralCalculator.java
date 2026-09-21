package service;

import calc.SingleThreadIntegralCalc;

import java.util.function.DoubleUnaryOperator;

public class RunnableIntegralCalculator {
    public record CalculationResult(double result, long duration) {}

    private double totalSum;
    private long finished;

    public CalculationResult calculate(double a, double b, long n, DoubleUnaryOperator f, long threads) throws InterruptedException {
        synchronized (this) {
            totalSum = 0;
            finished = 0;
        }
        long startTime = System.currentTimeMillis();
        double delta = (b - a) / threads;
        for (int i = 0; i < threads; i++) {
            double ai = a + i * delta;
            double bi = ai + delta;
            long ni = n / threads;
            Runnable calc = new SingleThreadIntegralCalc(ai, bi, ni, f, this::sendResult);
            Thread.startVirtualThread(calc);
        }
        synchronized (this) {
            while (finished < threads) {
                this.wait();
            }
        }
        long endTime = System.currentTimeMillis();
        return new CalculationResult(totalSum, endTime - startTime);
    }

    private synchronized void sendResult(double v) {
        totalSum += v;
        finished++;
        notify();
    }
}
