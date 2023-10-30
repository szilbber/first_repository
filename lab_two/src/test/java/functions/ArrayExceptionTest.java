package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayExceptionTest {
    @Test
    public void constructorExceptionTest() {
        double[] xVal = {1, 2, 3, 4};
        double[] yVal = {1, 2, 3, 4};

        double[] xValIncorrect = {1, 2, 5, 3};
        double[] yValIncorrect = {5, 6, 7, 8, 9};

        Assertions.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValIncorrect, yVal));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xVal, yValIncorrect));
    }
}
