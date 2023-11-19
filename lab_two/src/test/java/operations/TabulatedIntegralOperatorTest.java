package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.SqrFunctions;
import functions.TabulatedFunction;
import functions.UnitFunctions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TabulatedIntegralOperatorTest {

    @Test
    void integrate() {
        TabulatedFunction sqrtTabulatedFunction = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(new SqrFunctions(), 0, 10, 100_000_000));
        TabulatedFunction arrayTabulatedFunction = new SynchronizedTabulatedFunction (new ArrayTabulatedFunction(new double[]{-3, 3, 6, 9, 12}, new double[]{-1, 1, 1, 2, 2}));
        IntegralOperator integralOperator = new TabulatedIntegralOperator();

        try {
            assertEquals(333.333, integralOperator.integrate(sqrtTabulatedFunction), 0.001);
            assertEquals(16.5, integralOperator.integrate(arrayTabulatedFunction), 0.001);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}