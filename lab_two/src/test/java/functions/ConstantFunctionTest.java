package functions;

import junit.framework.TestCase;

public class ConstantFunctionTest extends TestCase {

    ConstantFunction PI = new ConstantFunction(3.14);
    ConstantFunction Zero =  new ZeroFunction();
    ConstantFunction Unit = new UnitFunctions();
    public void testGetConstNumber() {
        assertEquals(3.14, PI.getConstant());
        assertEquals(0., Zero.getConstant());
        assertEquals(1., Unit.getConstant());
    }

    public void testApply() {
        assertEquals(3.14, PI.apply(20));
        assertEquals(0., Zero.apply(20));
        assertEquals(1., Unit.apply(20));
    }
}