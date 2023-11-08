package operations;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedDifferentialOperator(){
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
        int i = 0;
        while(i != size-1){
            xValues[i] = points[i].x;
            yValues[i] = (points[i+1].y - points[i].y)/(points[i+1].x - points[i].x);
            i++;
        }
        xValues[i] = points[i].x;
        yValues[i] = yValues[i-1];
        return factory.create(xValues, yValues);
    }
}
