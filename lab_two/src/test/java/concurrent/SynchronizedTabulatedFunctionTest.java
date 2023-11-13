package concurrent;

import functions.ArrayTabulatedFunction;
import functions.IdentityFunction;
import functions.Point;
import functions.TabulatedFunction;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Git test commit
public class SynchronizedTabulatedFunctionTest {
    final int COUNT = 201;
    final int LEFT_BOUND = -100;
    final int RIGHT_BOUND = 100;

    TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(new IdentityFunction(), LEFT_BOUND, RIGHT_BOUND, COUNT);
    SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(tabulatedFunction);

    @Test
    public void forEach() {
        int index = 0;
        for (Point element : synchronizedTabulatedFunction) {
            assertEquals(element.x, tabulatedFunction.getX(index));
            assertEquals(element.y, tabulatedFunction.getY(index));
            index++;
        }
    }

    @Test
    public void getCount() {
        assertEquals(COUNT, synchronizedTabulatedFunction.getCount());
    }

    @Test
    public void getX() {
        for (int i = 0; i < COUNT; i++)
            assertEquals(tabulatedFunction.getX(i), synchronizedTabulatedFunction.getX(i));
    }

    @Test
    public void getY() {
        for (int i = 0; i < COUNT; i++)
            assertEquals(tabulatedFunction.getY(i), synchronizedTabulatedFunction.getY(i));
    }

    @Test
    public void setY() {
    }

    @Test
    public void indexOfX() {
        for (int i = LEFT_BOUND; i < RIGHT_BOUND; i++)
            assertEquals(tabulatedFunction.indexOfX(i), synchronizedTabulatedFunction.indexOfX(i));
    }

    @Test
    public void indexOfY() {
        for (int i = LEFT_BOUND; i < RIGHT_BOUND; i++)
            assertEquals(tabulatedFunction.indexOfY(i * i), synchronizedTabulatedFunction.indexOfY(i * i));
    }

    @Test
    public void leftBound() {
        assertEquals(LEFT_BOUND, synchronizedTabulatedFunction.leftBound());
    }

    @Test
    public void rightBound() {
        assertEquals(RIGHT_BOUND, synchronizedTabulatedFunction.rightBound(), 0.000_001);
    }

    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = func -> {
            double sum = 0;
            for (Point el : synchronizedTabulatedFunction)
                sum += el.y;
            return sum;
        };
        double sumOfY = synchronizedTabulatedFunction.doSynchronously(operation);
        assertEquals(0, sumOfY);
    }
}