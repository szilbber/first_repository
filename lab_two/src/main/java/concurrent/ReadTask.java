package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

public class ReadTask implements Runnable{
    private final TabulatedFunction tabulatedFunction;
    ReadTask(TabulatedFunction function){
        this.tabulatedFunction = function;
    }
    @Override
    public void run() {
        for(int i = 0; i!= tabulatedFunction.getCount(); i++){
           String str = String.format("After read: i = %d, x = %f, y = %f", i, tabulatedFunction.getX(i), tabulatedFunction.getY(i));
           System.out.println(str);
        }

    }
}
