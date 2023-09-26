package functions;
import org.junit.Test;
import junit.framework.Assert;

public class SinFunctionTest {
    MathFunction test2 = new SinFunction();
    @Test
    public void testEquals(){
        Assert.assertEquals(0, test2.apply(0));
    }
}