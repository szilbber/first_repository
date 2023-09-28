package functions;
import org.junit.Test;
import junit.framework.Assert;

public class XFunctionTest {
    MathFunction test2 = new XFunction();
    @Test
    public void testEquals(){
        Assert.assertEquals(0, test2.apply(0));
    }
}