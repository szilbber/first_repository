package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            double[] xValue = {1, 2, 3, 4};
            double[] yValue = {5, 6, 7, 8};

            TabulatedFunction function = new LinkedListTabulatedFunction(xValue, yValue);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction derivativeFirst = operator.derive(function);
            TabulatedFunction derivativeSecond = operator.derive(derivativeFirst);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, derivativeFirst);
            FunctionsIO.serialize(out, derivativeSecond);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("input/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
