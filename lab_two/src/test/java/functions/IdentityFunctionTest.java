package functions;

import junit.framework.Assert;
import org.junit.Test;

public class IdentityFunctionTest {
    IdentityFunction objOne = new IdentityFunction();
    IdentityFunction objTwo = (IdentityFunction) objOne.clone();

    @Test
    public void testApply() {
        double x = 15;
        Assert.assertEquals(x, objOne.apply(15));
        Assert.assertEquals(x, objTwo.apply(15));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("IdentityFunction(x) ", objOne.toString());
    }

    @Test
    public void testEquals() {
        MathFunction identFunctionToMathInterface = new IdentityFunction();
        MathFunction constFunc = new ConstantFunction(15);
        Assert.assertTrue(objOne.equals(objTwo));
        Assert.assertTrue(objOne.equals(identFunctionToMathInterface));
        Assert.assertFalse(objOne.equals(constFunc));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(41, objOne.hashCode());
        Assert.assertEquals(41, objTwo.hashCode());
    }
}
