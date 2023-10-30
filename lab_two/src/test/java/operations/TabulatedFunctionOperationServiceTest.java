package operations;

import functions.ArrayTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
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
}