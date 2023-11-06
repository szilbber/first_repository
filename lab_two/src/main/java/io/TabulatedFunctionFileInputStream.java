package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args){
        try{
            try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))){
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
                System.out.println(function);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}