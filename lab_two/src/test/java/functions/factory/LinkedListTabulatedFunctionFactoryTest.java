package functions.factory;

import functions.LinkedListTabulatedFunction;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListTabulatedFunctionFactoryTest {
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal, yVal);

    @Test
    public void Test() {
        LinkedListTabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        Assertions.assertEquals(list.getClass(), listFactory.create(xVal, yVal).getClass());
    }
}