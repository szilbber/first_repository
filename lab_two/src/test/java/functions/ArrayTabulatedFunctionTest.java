package functions;

import junit.framework.TestCase;

import java.util.Iterator;

import static org.junit.Assert.assertNotEquals;

public class ArrayTabulatedFunctionTest extends TestCase {
    double[] xValue = {1, 2, 3, 4, 5};
    double[] yValue = {2, 4, 6, 8, 10};

    MathFunction sqrFunctions = new SqrFunctions();

    ArrayTabulatedFunction objTest = new ArrayTabulatedFunction(xValue, yValue);
    ArrayTabulatedFunction objSqrFunctionTest = new ArrayTabulatedFunction(sqrFunctions, 0, 100, 10);

    public void testFloorIndexOfX() {
        assertEquals(0, objTest.floorIndexOfX(-2));
        assertEquals(0, objTest.floorIndexOfX(2));
        assertEquals(1, objTest.floorIndexOfX(2.2));
        assertEquals(2, objTest.floorIndexOfX(3.4));
        assertEquals(3, objTest.floorIndexOfX(4.9));
        assertEquals(3, objTest.floorIndexOfX(5));
        assertEquals(5, objTest.floorIndexOfX(6));
    }

    public void testExtrapolateLeft() {
        assertEquals(0.0, objTest.extrapolateLeft(0));
        assertEquals(-2.0, objTest.extrapolateLeft(-1));
        assertEquals(-22.2, objSqrFunctionTest.extrapolateLeft(-2), 0.1);
    }

    public void testExtrapolateRight() {
        assertEquals(13., objTest.extrapolateRight(6.5));
        assertEquals(14., objTest.extrapolateRight(7));
        assertEquals(10377.7, objSqrFunctionTest.extrapolateRight(102), 0.1);
    }

    public void testInterpolate() {
        assertEquals(3.5, objTest.interpolate(1.75, objTest.floorIndexOfX(1.75)));
        assertEquals(4.5, objTest.interpolate(2.25, objTest.floorIndexOfX(2.25)));
        assertEquals(4., objTest.interpolate(2., objSqrFunctionTest.floorIndexOfX(2.)));
    }

    public void testGetCount() {
        assertEquals(5, objTest.getCount());
        assertEquals(10, objSqrFunctionTest.getCount());
    }

    public void testGetX() {
        assertEquals(1., objTest.getX(0));
        assertEquals(3., objTest.getX(2));
        assertEquals(4., objTest.getX(3));
        assertEquals(0., objSqrFunctionTest.getX(0));
        assertEquals(22.2, objSqrFunctionTest.getX(2), 0.1);
        assertEquals(33.3, objSqrFunctionTest.getX(3), 0.1);
    }

    public void testGetY() {
        assertEquals(2., objTest.getY(0));
        assertEquals(6., objTest.getY(2));
        assertEquals(8., objTest.getY(3));
    }

    public void testSetY() {
        objTest.setY(0, 15);
        assertEquals(15., objTest.getY(0));
    }

    public void testIndexOfX() {
        assertEquals(0, objTest.indexOfX(1));
        assertEquals(-1, objTest.indexOfX(10));
    }

    public void testIndexOfY() {
        assertEquals(0, objTest.indexOfY(2.));
        assertEquals(-1, objTest.indexOfY(2.1));
    }

    public void testLeftBound() {
        assertEquals(1., objTest.leftBound());
        assertEquals(0., objSqrFunctionTest.leftBound());
    }

    public void testRightBound() {
        assertEquals(5., objTest.rightBound());
        assertEquals(100., objSqrFunctionTest.rightBound(), 0.1);
    }

    public void testToString() {
        String expectedString = "ArrayTabulatedFunction{xValues=[1.0, 2.0, 3.0, 4.0, 5.0], yValues=[2.0, 4.0, 6.0, 8.0, 10.0]}";
        assertEquals(expectedString, objTest.toString());
    }


    public void testHashCode() {
        ArrayTabulatedFunction objTestCopy = new ArrayTabulatedFunction(xValue, yValue);

        // Тест на равенство hashCode двух объектов с одинаковыми значениями
        assertEquals(objTest.hashCode(), objTestCopy.hashCode());
    }

    public void testEquals() {
        assertEquals(objTest, objTest);

        ArrayTabulatedFunction objTestCopy = new ArrayTabulatedFunction(xValue, yValue);

        assertEquals(objTest, objTestCopy);

        double[] differentXValues = {1, 2, 3, 4, 6};
        double[] differentYValues = {2, 4, 6, 8, 12};
        ArrayTabulatedFunction objTestDifferent = new ArrayTabulatedFunction(differentXValues, differentYValues);

        assertNotEquals(objTest, objTestDifferent);
    }

    public void testClone() {
        ArrayTabulatedFunction clonedFunction = objTest.clone();

        assertNotSame(objTest, clonedFunction);
        assertEquals(objTest, clonedFunction);
        assertNotEquals(objTest, sqrFunctions);
    }

    public void testIteratorWithForEachLoop() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        ArrayTabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);

        int i = 0;
        for (Point point : tabulatedFunction) {
            assertEquals(xValues[i], point.x, 0.0001);
            assertEquals(yValues[i], point.y, 0.0001);
            i++;
        }
    }

    public void testIteratorWithWhileLoop() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        ArrayTabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);

        Iterator<Point> iterator = tabulatedFunction.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValues[i], point.x, 0.0001);
            assertEquals(yValues[i], point.y, 0.0001);
            i++;
        }
    }
}