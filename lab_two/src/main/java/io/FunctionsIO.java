package io;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("FunctionsIO class cannot be instantiated or extended.");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);

        printWriter.println(function.getCount());

        for (Point item : function)
            printWriter.printf("%f %f%n", item.x, item.y);

        printWriter.flush();
    }


}
