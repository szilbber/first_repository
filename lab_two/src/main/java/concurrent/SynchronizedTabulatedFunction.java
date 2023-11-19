package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction delegate;

    public SynchronizedTabulatedFunction(TabulatedFunction delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getCount() {
        synchronized (delegate) {
            return delegate.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (delegate) {
            return delegate.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (delegate) {
            return delegate.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (delegate) {
            delegate.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (delegate) {
            return delegate.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (delegate) {
            return delegate.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (delegate) {
            return delegate.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (delegate) {
            return delegate.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (delegate) {
            Point[] copy = TabulatedFunctionOperationService.asPoints(delegate);
            return new Iterator<Point>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return (copy.length > i);
                }

                @Override
                public Point next() {
                    Point p;
                    if (hasNext()) {
                        p = copy[i++];
                    } else throw new NoSuchElementException();
                    return p;
                }
            };
        }

    }

    @Override
    public double apply(double x) {
        synchronized (delegate) {
            return delegate.apply(x);
        }
    }

    public <T> T doSynchronously(Operation<T> operation) {
        synchronized (delegate) {
            return operation.apply(this);
        }
    }

    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction);
    }
}
