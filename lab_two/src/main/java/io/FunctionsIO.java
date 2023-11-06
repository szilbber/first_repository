package io;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String[] parts = reader.readLine().split(" ");
            try {
                xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                yValues[i] = numberFormat.parse(parts[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException(e);
            }
        }

        return factory.create(xValues, yValues);
    }
}
