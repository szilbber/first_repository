package functions;

import junit.framework.TestCase;

public class ArrayTabulatedFunctionTest extends TestCase {
    double[] xValue = {1, 2, 3, 4, 5};
    double[] yValue = {2, 4, 6, 8, 10};

    MathFunction sqrFunctions = new SqrFunctions();

    ArrayTabulatedFunction objTest = new ArrayTabulatedFunction(xValue, yValue);
    ArrayTabulatedFunction objSqrFunctionTest =  new ArrayTabulatedFunction(sqrFunctions, 0, 100, 10);

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
        assertEquals(0,objTest.indexOfX(1));
        assertEquals(-1,objTest.indexOfX(10));
    }

    public void testIndexOfY() {
        assertEquals(0,objTest.indexOfY(2.));
        assertEquals(-1,objTest.indexOfY(2.1));
    }

    public void testLeftBound() {
        assertEquals(1., objTest.leftBound());
        assertEquals(0., objSqrFunctionTest.leftBound());
    }

    public void testRightBound() {
        assertEquals(5., objTest.rightBound());
        assertEquals(100., objSqrFunctionTest.rightBound(), 0.1);
    }
}