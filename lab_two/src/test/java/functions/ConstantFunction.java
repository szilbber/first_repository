package functions;

public class ConstantFunction implements MathFunction {
    private final double ConstNumber;

    public ConstantFunction(double x) {
        ConstNumber = x;
    }

    public double getConstNumber() {
        return ConstNumber;
    }

    public double apply(double x) {
        return ConstNumber;
    }


}
