package functions;
import org.junit.Test;
import junit.framework.Assert;

public class IdentityFunctionTest {
    MathFunction test1 = new IdentityFunction();
    @Test
    public void testEquals(){
        double x = 15;
        Assert.assertEquals(x, test1.apply(15));
    }
}
