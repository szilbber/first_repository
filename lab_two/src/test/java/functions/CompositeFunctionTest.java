package functions;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CompositeFunctionTest {

    MathFunction sinFunc = new AsinSinFunction();
    MathFunction sqrFunc = new SqrFunctions();
    MathFunction unitFunc = new UnitFunctions();
    MathFunction zeroFunc = new ZeroFunction();
    MathFunction test3 = new CompositeFunction(sqrFunc, sinFunc);
    MathFunction test4 = new CompositeFunction(sinFunc, unitFunc);
    MathFunction tableMultiplyContFunc = new ArrayTabulatedFunction(
            new double[]{1, 1.5, 2, 2.5, 3, 3.5, 4},
            new double[]{2, 3, 4, 5, 6, 7, 8});
    MathFunction tabulateSqrFunction = new ArrayTabulatedFunction(sqrFunc, 0, 10, 100);
    MathFunction mulSqrFunc = tableMultiplyContFunc.andThen(tabulateSqrFunction);
    MathFunction expLogSqrFunc = new ExponentialLogFunction(2, 2).andThen(tabulateSqrFunction);
    MathFunction sqrSinFunc = sqrFunc.andThen(sinFunc);
    MathFunction sqrZeroFunc = sqrFunc.andThen(zeroFunc);
    //Тесты для X
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    MathFunction myTestFunction = new LinkedListTabulatedFunction(xVal, yVal);

    MathFunction unitFunctions = new UnitFunctions();
    MathFunction asinSinFunction = new AsinSinFunction();

    MathFunction unitMy = unitFunctions.andThen(myTestFunction);
    MathFunction asinSinMy = asinSinFunction.andThen(myTestFunction);

    @Test
    public void AndThanList() {
        Assertions.assertEquals(1, unitMy.apply(1));
        Assertions.assertEquals(0.716815, asinSinMy.apply(3), 0.000_001);
    }

    //Тесты для Y
    @Test
    public void TestEquals() {
        Assert.assertEquals(0.0, test3.apply(0));
        Assert.assertEquals(1.0, test4.apply(0));
        CompositeFunction func = new CompositeFunction(sinFunc, unitFunc);
    }

    @Test
    public void TestAndThen() {
        Assert.assertEquals(0.0, sqrSinFunc.apply(0));
        Assert.assertEquals(0.0, sqrZeroFunc.apply(1000));
        Assert.assertEquals(8.0, mulSqrFunc.apply(2), 0.1);
        Assert.assertEquals(0.0, expLogSqrFunc.apply(0));
        Assert.assertEquals(100.0, expLogSqrFunc.apply(10), 0.1);
    }

}