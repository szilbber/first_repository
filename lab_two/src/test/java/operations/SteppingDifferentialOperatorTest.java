package operations;

import functions.MathFunction;
import functions.SqrFunctions;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SteppingDifferentialOperatorTest {
    @Test
    public void deriveTest() {
        double STEP = 2;
        SteppingDifferentialOperator leftDifferentialOperator = new LeftSteppingDifferentialOperator(STEP);
        SteppingDifferentialOperator rightDifferentialOperator = new RightSteppingDifferentialOperator(STEP);
        SteppingDifferentialOperator middleDifferentialOperator = new MiddleSteppingDifferentialOperator(STEP);

        MathFunction sqrLeftDerive = leftDifferentialOperator.derive(new SqrFunctions());
        MathFunction sqrRightDerive = rightDifferentialOperator.derive(new SqrFunctions());
        MathFunction sqrMiddleDerive = middleDifferentialOperator.derive(new SqrFunctions());

        assertEquals(198, sqrLeftDerive.apply(100), 0.001);
        assertEquals(202, sqrRightDerive.apply(100), 0.001);
        assertEquals(200, sqrMiddleDerive.apply(100), 0.001);
        assertEquals(20, sqrMiddleDerive.apply(10), 0.001);
    }
}