package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.LeftSteppingDifferentialOperator;
import operations.TabulatedDifferentialOperator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))){
                double[] xValues = {1.0, 2.0, 3.0, 4.0};
                double[] yValues = {0.0, 10.0, 40.0, 90.0};

                TabulatedDifferentialOperator differentOperator= new TabulatedDifferentialOperator();

                TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
                TabulatedFunction firstDifferentArrayFunction = differentOperator.derive(arrayFunction);
                TabulatedFunction secondDifferentArrayFunction = differentOperator.derive(firstDifferentArrayFunction);

                FunctionsIO.serialize(bufferedOutputStream, arrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, firstDifferentArrayFunction);
                FunctionsIO.serialize(bufferedOutputStream, secondDifferentArrayFunction);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
