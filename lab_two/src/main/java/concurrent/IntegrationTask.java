package concurrent;

import functions.TabulatedFunction;

import java.util.concurrent.Callable;

public class IntegrationTask implements Callable<Double> {
    double x1, x2, y1, y2;

    public IntegrationTask(TabulatedFunction tabulatedFunction, int floorIndex) {
        this.x1 = tabulatedFunction.getX(floorIndex);
        this.x2 = tabulatedFunction.getX(floorIndex + 1);
        this.y1 = tabulatedFunction.getY(floorIndex);
        this.y2 = tabulatedFunction.getY(floorIndex + 1);
    }

    @Override
    public Double call()  {

          if ((y1 >= 0) != (y2 >= 0)) {
            double x0 = -(y1 / (y2 - y1) * (x2 - x1)) + x1;
            return Math.abs((y1 / 2) * (x0 - x1)) + Math.abs((y2 / 2) * (x2 - x0));
        }

        return Math.abs(((y1 + y2) / 2) * (x2 - x1));
    }
}
