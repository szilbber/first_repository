package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction implements AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    protected int floorIndexOfX(double x) {
        return 0;
    }

    protected double extrapolateLeft(double x) {
        return 0;
    }

    protected double extrapolateRight(double x) {
        return 0;
    }

    protected double interpolate(double x, int floorIndex) {
        return 0;
    }

    public int getCount() {
        return 0;
    }

    public double getX(int index) {
        return 0;
    }

    public double getY(int index) {
        return 0;
    }

    public void SetY(int index, double value) {

    }

    public int indexOfX(double x) {
        return 0;
    }

    public int indexOfY(double y) {
        return 0;
    }

    public double leftBound() {
        return 0;
    }

    public double rightBound() {
        return 0;
    }
}
