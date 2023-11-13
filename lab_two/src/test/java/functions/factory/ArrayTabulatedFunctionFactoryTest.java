package functions.factory;

import functions.ArrayTabulatedFunction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayTabulatedFunctionFactoryTest {
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    ArrayTabulatedFunction arr = new ArrayTabulatedFunction(xVal, yVal);

    @Test
    public void Test() {
        ArrayTabulatedFunctionFactory arrFactory = new ArrayTabulatedFunctionFactory();
        Assertions.assertEquals(arr.getClass(), arrFactory.create(xVal, yVal).getClass());
    }
}