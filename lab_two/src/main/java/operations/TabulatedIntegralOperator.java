package operations;

import concurrent.IntegrationTask;
import functions.TabulatedFunction;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TabulatedIntegralOperator implements IntegralOperator {
    @Override
    public double integrate(TabulatedFunction function) throws InterruptedException, ExecutionException {
        int countSector = function.getCount() - 1;
        int availableProcessors = Math.min(countSector, Runtime.getRuntime().availableProcessors());
        System.out.println(availableProcessors);

        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
        List<Future<Double>> result = new ArrayList<>();

        for (int i = 0; i < countSector; i++)
            result.add(executorService.submit(new IntegrationTask(function, i)));

        executorService.shutdown();

        double integralSum = 0;
        for (Future<Double> future : result)
            integralSum += future.get();

        return integralSum;
    }
}
