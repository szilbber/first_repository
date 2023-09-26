package functions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import junit.framework.Assert;

public class IdentityFunctionTest {
    MathFunction test1 = new IdentityFunction();
    @Test
    public void testEquals(){
        Assert.assertEquals(15, test1.apply(15));
    }
}
