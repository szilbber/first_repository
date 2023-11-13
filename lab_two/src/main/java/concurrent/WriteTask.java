package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private final double value;

    WriteTask(TabulatedFunction function, double value) {
        this.tabulatedFunction = function;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i != tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, value);
                String str = String.format("Writing for index %d complete", i);
                System.out.println(str);
            }
        }
    }
}
