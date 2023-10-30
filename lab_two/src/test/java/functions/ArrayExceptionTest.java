package functions;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayExceptionTest {
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {1, 2, 3, 4};

    ArrayTabulatedFunction array = new ArrayTabulatedFunction(xVal, yVal);
    @Test
    public void constructorExceptionTest() {
        double[] xVal1 = {1, 2, 3, 4};
        double[] yVal1 = {1, 2, 3, 4};

        double[] xValIncorrect = {1, 2, 5, 3};
        double[] yValIncorrect = {5, 6, 7, 8, 9};

        Assertions.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValIncorrect, yVal1));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xVal1, yValIncorrect));
    }
    @Test(expected = IllegalArgumentException.class)
    public void createArrayOne() {
        double[] x_one = {3};
        double[] y_one = {4};
        ArrayTabulatedFunction arrayOne = new ArrayTabulatedFunction(x_one, y_one);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createArray() {
        MathFunction sqrFunctions = new SqrFunctions();
        ArrayTabulatedFunction arrTwo = new ArrayTabulatedFunction(sqrFunctions, 0, 100, 1);
    }
    @Test
    public void getXExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> array.getX(10));
    }

    @Test
    public void getYExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> array.getY(10));
    }

    @Test
    public void setYExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> array.setY(10, 20));
    }

    @Test
    public void FloorIndexOfXExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> array.floorIndexOfX(-5));
    }
}
