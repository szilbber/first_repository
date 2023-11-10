package functions;

import junit.framework.Assert;
import org.junit.Test;


public class ZeroFunctionTest {
    MathFunction testFunc = new ZeroFunction();

    @Test
    public void testApply() {
        Assert.assertEquals(0.0, testFunc.apply(0));
        Assert.assertEquals(0.0, testFunc.apply(2));
        Assert.assertEquals(0.0, testFunc.apply(3));
    }
}