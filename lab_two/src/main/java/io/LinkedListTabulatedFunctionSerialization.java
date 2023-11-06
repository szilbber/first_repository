package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args){
        try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))){

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
