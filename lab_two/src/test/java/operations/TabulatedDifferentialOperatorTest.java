package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.Test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;

public class TabulatedDifferentialOperatorTest {
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal, yVal);
    double[] xValue = {1, 2, 3, 4, 5};
    double[] yValue = {2, 4, 6, 8, 10};
    ArrayTabulatedFunction arr = new ArrayTabulatedFunction(xValue, yValue);
    @Test
    public void setFactoryTest() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator();
        operation.setFactory(fact);
        Assertions.assertEquals(operation.getFactory().getClass(), fact.getClass());
    }
    @Test
    public void deriveTest1(){
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        TabulatedFunction function = operation.derive(list);
        Assertions.assertEquals(1, function.getY(0));
        Assertions.assertEquals(1, function.getY(1));
        Assertions.assertEquals(1, function.getY(2));
        Assertions.assertEquals(1, function.getY(3));
    }
    @Test
    public void deriveTest2(){
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        TabulatedFunction function = operation.derive(arr);
        Assertions.assertEquals(2, function.getY(0));
        Assertions.assertEquals(2, function.getY(1));
        Assertions.assertEquals(2, function.getY(2));
        Assertions.assertEquals(2, function.getY(3));
        Assertions.assertEquals(2, function.getY(4));
    }


}