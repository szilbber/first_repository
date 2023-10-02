package functions;

import org.junit.Test;
import junit.framework.Assert;

public class CompositeFunctionTest {

    MathFunction sinFunc = new SinFunction();
    MathFunction sqrFunc = new SqrFunctions();
    MathFunction unitFunc = new UnitFunctions();
    MathFunction zeroFunc = new ZeroFunction();
    MathFunction test3 = new CompositeFunction(sqrFunc, sinFunc);
    MathFunction test4 = new CompositeFunction(sinFunc, unitFunc);
    MathFunction tableMultiplyContFunc = new ArrayTabulatedFunction(
            new double[]{1, 1.5, 2, 2.5, 3, 3.5, 4},
            new double[]{2, 3, 4, 5, 6, 7, 8});
    MathFunction tabulateSqrFunction = new ArrayTabulatedFunction(sqrFunc, 0, 10, 100);

    MathFunction sqrSinFunc = sqrFunc.andThen(sinFunc);
    MathFunction sqrZeroFunc = sqrFunc.andThen(zeroFunc);
    MathFunction mulSqrFunc = tableMultiplyContFunc.andThen(tabulateSqrFunction);
    MathFunction expLogSqrFunc = new ExponentialLogFunction(2, 2).andThen(tabulateSqrFunction);

    @Test
    public void TestEquals() {
        Assert.assertEquals(0.0, test3.apply(0));
        Assert.assertEquals(1.0, test4.apply(0));
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