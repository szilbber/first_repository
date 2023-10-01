package functions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;

public class LinkedListTabulatedFunctionTest {
    double[] xVal = {1,2,3,4};
    double[] yVal = {5,6,7,8};
    LinkedListTabulatedFunction List = new LinkedListTabulatedFunction(xVal,yVal);
    @Test
    public void floorIndexOfX(){
        double x = List.floorIndexOfX(3);
        Assertions.assertEquals(1, x);
        double x1 = List.floorIndexOfX(5);
        Assertions.assertEquals(4, x1);
        double x2 = List.floorIndexOfX(-1);
        Assertions.assertEquals(0, x2);
        double x3 = List.floorIndexOfX(3.5);
        Assertions.assertEquals(2, x3);
        double x4 = List.floorIndexOfX(4);
        Assertions.assertEquals(2, x4);
    }
//    @Test
//    public void extrapolateLeft(){
//    }
//    @Test
//    public void extrapolateRight(){
//    }
//    @Test
//    public void interpolate(){
//    }
//
//    @Test
//    public void getCount(){
//
//    }
//    @Test
//    public double getX(int index) {
//    }
//
//    @Test
//    public double getY(int index) {
//
//    }
//
//    @Test
//    public void SetY(int index, double value) {
//    }
    @Test
    public void indexOfX(){
        int index = List.indexOfX(4);
        Assertions.assertEquals(3, index);
    }
    @Test
    public void indexOfY(){
        int index = List.indexOfY(7);
        Assertions.assertEquals(2, index);
    }
    @Test
    public void leftBound() {
        double x = List.leftBound();
        Assertions.assertEquals(1, x);
    }
    @Test
    //Возвращает самый правый х
    public void rightBound() {
        double x = List.rightBound();
        Assertions.assertEquals(4, x);
    }
}