package functions;
import org.junit.Test;
import junit.framework.Assert;
public class CompositeFunctionTest {

    MathFunction sinFunc = new XFunction();
    MathFunction sqrFunc = new SqrFunctions();
    MathFunction unitFunc = new UnitFunctions();
    MathFunction test3 = new CompositeFunction(sqrFunc, sinFunc);
    MathFunction test4 = new CompositeFunction(sinFunc, unitFunc);
    @Test
    public void TestEquals(){
        Assert.assertEquals(0.0, test3.apply(0));
        Assert.assertEquals(1.0, test4.apply(0));
    }

}