package operations;

import exceptions.InconsistentFunctionsException;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TabulatedFunctionOperationServiceTest {

    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal, yVal);
    double[] xVal1 = {1, 2, 3, 4};
    double[] yVal1 = {13, 14, 15, 16};
    LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xVal1, yVal1);
    TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService();

    @Test
    void asPoint() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);

        Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);

        for (int i = 0; i < xValues.length; i++) {
            assertEquals(xValues[i], points[i].x, 0.0001);
            assertEquals(yValues[i], points[i].y, 0.0001);
        }
    }

    @Test
    public void addTest() {
        TabulatedFunction fuck = operation.add(list, list1);
        for (int i = 0; i != fuck.getCount(); i++) {
            Assertions.assertEquals(yVal[i] + yVal1[i], fuck.getY(i));
        }

    }

    @Test
    public void subtractTest() {
        TabulatedFunction fuck = operation.subtract(list, list1);
        for (int i = 0; i != fuck.getCount(); i++) {
            Assertions.assertEquals(yVal[i] - yVal1[i], fuck.getY(i));
        }
    }

    @Test
    public void multiplicationTest() {
        TabulatedFunction fuck = operation.multiplication(list, list1);
        for (int i = 0; i != fuck.getCount(); i++) {
            Assertions.assertEquals(yVal[i] * yVal1[i], fuck.getY(i));
        }
    }

    @Test
    public void divisionTest() {
        TabulatedFunction fuck = operation.division(list, list1);
        for (int i = 0; i != fuck.getCount(); i++) {
            Assertions.assertEquals(yVal[i] / yVal1[i], fuck.getY(i));
        }
    }

    @Test
    void getAndSetFactory() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};

        ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

        TabulatedFunctionOperationService tabulatedFunction = new TabulatedFunctionOperationService();
        assertEquals(arrayFactory.getClass(), tabulatedFunction.getFactory().getClass());

        tabulatedFunction.setFactory(new LinkedListTabulatedFunctionFactory());
        assertEquals(linkedListFactory.getClass(), tabulatedFunction.getFactory().getClass());
    }

    @Test
    void add() {
        double[] xValuesOne = {1.0, 2.0, 3.0};
        double[] yValuesOne = {10.0, 20.0, 30.0};
        double[] xValuesTwo = {1.0, 2.0, 3.0};
        double[] yValuesTwo = {1.0, 2.0, 3.0};

        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
        TabulatedFunction functionOne = new ArrayTabulatedFunction(xValuesOne, yValuesOne);
        TabulatedFunction functionTwo = new ArrayTabulatedFunction(xValuesTwo, yValuesTwo);

        TabulatedFunction resultFunction = operationService.add(functionOne, functionTwo);
        int i = 0;
        for (Point item : resultFunction) {
            assertEquals(functionOne.getY(i) + functionTwo.getY(i), item.y);
            assertEquals(functionOne.getX(i), item.x);
            i++;
        }
    }

    @Test
    void subtract() {
        double[] xValuesOne = {1.0, 2.0, 3.0};
        double[] yValuesOne = {10.0, 20.0, 30.0};
        double[] xValuesTwo = {1.0, 2.0, 3.0};
        double[] yValuesTwo = {1.0, 2.0, 3.0};

        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
        TabulatedFunction functionOne = new ArrayTabulatedFunction(xValuesOne, yValuesOne);
        TabulatedFunction functionTwo = new ArrayTabulatedFunction(xValuesTwo, yValuesTwo);

        TabulatedFunction resultFunction = operationService.subtract(functionOne, functionTwo);
        int i = 0;
        for (Point item : resultFunction) {
            assertEquals(functionOne.getY(i) - functionTwo.getY(i), item.y);
            assertEquals(functionOne.getX(i), item.x);
            i++;
        }
    }

    @Test
    void doOperationExceptionTest() {
        double[] xValuesOne = {1.0, 2.0, 3.0};
        double[] yValuesOne = {10.0, 20.0, 30.0};
        double[] xValuesTwoExceptSize = {1.0, 2.0, 3.0, 5.0};
        double[] yValuesTwoExceptSize = {1.0, 2.0, 3.0, 5.0};
        double[] xValuesTwoExceptValue = {1.0, 2.5, 3.0};

        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
        TabulatedFunction functionOne = new ArrayTabulatedFunction(xValuesOne, yValuesOne);
        TabulatedFunction functionSizeExcept = new ArrayTabulatedFunction(xValuesTwoExceptSize, yValuesTwoExceptSize);
        TabulatedFunction functionExceptValue = new ArrayTabulatedFunction(xValuesTwoExceptValue, yValuesOne);

        assertThrows(InconsistentFunctionsException.class, () -> operationService.add(functionOne, functionSizeExcept));
        assertThrows(InconsistentFunctionsException.class, () -> operationService.add(functionOne, functionExceptValue));
    }
}