package operations;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    TabulatedFunctionFactory factory;

    TabulatedDifferentialOperator(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    TabulatedDifferentialOperator(){
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    @Override
    public TabulatedFunction derive(TabulatedFunction function) {

        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int size = points.length;
        double[] xValues = new double[size];
        double[] yValues = new double[size];
        int index = 0;
        while(index != size-1){
            xValues[index] = points[index].x;
            yValues[index] = (points[index+1].y - points[index].y)/(points[index+1].x - points[index].x);
            index++;
        }
        xValues[size-1] = points[size-2].x;
        yValues[size-1] = yValues[size-2];
        return factory.create(xValues, yValues);
    }
}
