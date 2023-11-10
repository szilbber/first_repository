package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {

        try (BufferedOutputStream arrayFileOutputStream = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
             BufferedOutputStream LinkedListFileOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))) {
            double[] xValue = {1, 2, 3, 4};
            double[] yValue = {5, 6, 7, 8};

            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValue, yValue);
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValue, yValue);

            FunctionsIO.writeTabulatedFunction(arrayFileOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(LinkedListFileOutputStream, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}