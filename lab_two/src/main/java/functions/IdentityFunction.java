package functions;

class IdentityFunction implements MathFunction {
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction{}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}