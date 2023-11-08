package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {

    protected int count;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" size = ");
        sb.append(getCount());
        sb.append("\n");
        for (Point point : this) {
            sb.append("[");
            sb.append(point.x);
            sb.append("; ");
            sb.append(point.y);
            sb.append("]\n");
        }
        return sb.toString();
    }

    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length)
            throw new DifferentLengthOfArraysException("The lengths of the xValues and yValues arrays are different");
    }

    ;

    static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; i++)
            if (xValues[i - 1] > xValues[i])
                throw new ArrayIsNotSortedException("The array is not sorted");
    }

    public double apply(double x) {
        if (x < leftBound())
            return extrapolateLeft(x);
        else if (x > rightBound())
            return extrapolateRight(x);
        else {
            int searchIndexOfX = indexOfX(x);

            if (searchIndexOfX != -1)
                return getY(searchIndexOfX);
            else
                return interpolate(x, floorIndexOfX(x));
        }
    }
}
