package operations;

import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

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

    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal, yVal);
    double[] xVal1 = {1, 2, 3, 4};
    double[] yVal1 = {13, 14, 15, 16};
    LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xVal1, yVal1);
    TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService();
    @Test
    public void addTest() {
       TabulatedFunction fuck = operation.add(list, list1);
       for(int i=0; i!=fuck.getCount(); i++){
           Assertions.assertEquals(yVal[i]+yVal1[i], fuck.getY(i));
       }

    }
    @Test
    public void  subtractTest() {
        TabulatedFunction fuck = operation.subtract(list, list1);
        for(int i=0; i!=fuck.getCount(); i++){
            Assertions.assertEquals(yVal[i]-yVal1[i], fuck.getY(i));
        }
    }
    @Test
    public void  multiplicationTest() {
        TabulatedFunction fuck = operation.multiplication(list, list1);
        for(int i=0; i!=fuck.getCount(); i++){
            Assertions.assertEquals(yVal[i]*yVal1[i], fuck.getY(i));
        }
    }
    @Test
    public void  divisionTest() {
        TabulatedFunction fuck = operation.division(list, list1);
        for(int i=0; i!=fuck.getCount(); i++){
            Assertions.assertEquals(yVal[i]/yVal1[i], fuck.getY(i));
        }
    }
}