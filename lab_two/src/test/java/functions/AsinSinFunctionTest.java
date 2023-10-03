package functions;
import org.junit.Test;
import junit.framework.Assert;

public class AsinSinFunctionTest {
    MathFunction test2 = new AsinSinFunction();
    @Test
    public void testEquals(){
        Assert.assertEquals(0.0, test2.apply(0));
    }
}