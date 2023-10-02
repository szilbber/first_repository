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

    MathFunction sqrSinFunc = sqrFunc.andThen(sinFunc);
    MathFunction sqrZeroFunc = sqrFunc.andThen(zeroFunc);
    @Test
    public void TestEquals(){
        Assert.assertEquals(0.0, test3.apply(0));
        Assert.assertEquals(1.0, test4.apply(0));
    }
    @Test
    public void TestAndThen(){
        Assert.assertEquals(0., sqrSinFunc.apply(0));
        Assert.assertEquals(0.0, sqrZeroFunc.apply(1000));
    }

}