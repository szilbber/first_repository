package functions;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public ConstantFunction(double x) {
        constant = x;
    }

    public double getConstant() {
        return constant;
    }

    public double apply(double x) {
        return constant;
    }


}
