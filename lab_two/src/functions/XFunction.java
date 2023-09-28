package functions;
    class XFunction implements MathFunction{
        public double apply(double x){
            return Math.asin(Math.sin(x));
        }
    }