package functions;

import com.sun.jdi.InternalException;

class IdentityFunction implements MathFunction {
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction(x) ";
    }

    @Override
    public int hashCode() {
        return 41;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public Object clone() {
        return new IdentityFunction();
    }
}