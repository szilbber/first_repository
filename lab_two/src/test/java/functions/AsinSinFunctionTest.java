package functions;

import junit.framework.Assert;
import org.junit.Test;

public class AsinSinFunctionTest {
    MathFunction test2 = new AsinSinFunction();

    @Test
    public void testEquals() {
        Assert.assertEquals(0.0, test2.apply(0));
    }
}