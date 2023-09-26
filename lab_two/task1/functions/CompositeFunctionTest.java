package functions;
import org.junit.Test;
import junit.framework.Assert;
public class CompositeFunctionTest {

    MathFunction sinFunc = new SinFunction();
    MathFunction sqrFunc = new SqrFunctions();
    MathFunction unitFunc = new UnitFunctions();
    MathFunction test3 = new CompositeFunction(sqrFunc, sinFunc);
    MathFunction test4 = new CompositeFunction(sinFunc, unitFunc);
    @Test
    public void TestEquals(){
        Assert.assertEquals(0, test3.apply(0));
        Assert.assertEquals(1, test4.apply(0));
    }

}