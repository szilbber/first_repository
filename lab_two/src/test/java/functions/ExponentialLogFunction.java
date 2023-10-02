package functions;

public class ExponentialLogFunction implements MathFunction {
    private final double a, b;

    ExponentialLogFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }


    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public double apply(double x) {
        return Math.pow(a, Math.log(x) / Math.log(b));
    }
}
