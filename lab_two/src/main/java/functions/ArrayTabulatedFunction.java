package functions;

import exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private final double[] xValues;
    private final double[] yValues;

    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        if (xValues.length < 2)
            throw new IllegalArgumentException("Длина меньше минимальной");

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Длина меньше минимальной");
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        xValues = new double[count];
        yValues = new double[count];
        this.count = count;

        double step = (xFrom + xTo) / (count - 1);
        double xCoordinate = xFrom;

        for (int i = 0; i < count; i++) {
            xValues[i] = xCoordinate;
            yValues[i] = source.apply(xCoordinate);
            xCoordinate += step;
        }
    }

    protected int floorIndexOfX(double x) {
        if (x < xValues[0])
            throw new IllegalArgumentException("х меньше левой границы");

        int index = 0;
        while (index < count && xValues[index] < x) ++index;
        return (index == count || index == 0) ? index : --index;
    }

    protected double extrapolateLeft(double x) {
        return interpolate(x, getX(0), getX(1), getY(0), getY(1));
    }

    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    protected double interpolate(double x, int floorIndex) {
        if (getX(floorIndex) >= x)
            throw new InterpolationException("Range error for interpolation");

        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("Некорректный индекс: " + index);

        return xValues[index];
    }

    public double getY(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("Некорректный индекс: " + index);

        return yValues[index];
    }

    public void setY(int index, double value) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("Некорректный индекс: " + index);

        yValues[index] = value;
    }

    public int indexOfX(double x) {
        return binarySearch(xValues, x);
    }

    public int indexOfY(double y) {
        return binarySearch(yValues, y);
    }

    private int binarySearch(double[] arr, double target) {
        int left = 0;
        int right = count - 1;
        double epsilon = 0.000_000_001;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Math.abs(arr[mid] - target) < epsilon)
                return mid;
            else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public double leftBound() {
        return xValues[0];
    }

    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;
        return Arrays.equals(xValues, that.xValues) && Arrays.equals(yValues, that.yValues);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        return result;
    }

//    @Override
//    public String toString() {
//        return "ArrayTabulatedFunction{" +
//                "xValues=" + Arrays.toString(xValues) +
//                ", yValues=" + Arrays.toString(yValues) +
//                '}';
//    }

    @Override
    public ArrayTabulatedFunction clone() {
        double[] clonedXValues = Arrays.copyOf(xValues, xValues.length);
        double[] clonedYValues = Arrays.copyOf(yValues, yValues.length);

        return new ArrayTabulatedFunction(clonedXValues, clonedYValues);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < xValues.length;
            }

            @Override
            public Point next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more elements to iterate");

                Point point = new Point(xValues[currentIndex], yValues[currentIndex]);
                currentIndex++;
                return point;
            }
        };
    }
}