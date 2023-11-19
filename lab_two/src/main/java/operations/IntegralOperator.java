package operations;

import functions.TabulatedFunction;

import java.util.concurrent.ExecutionException;

public interface IntegralOperator {
    double integrate(TabulatedFunction function) throws ExecutionException, InterruptedException;
}
