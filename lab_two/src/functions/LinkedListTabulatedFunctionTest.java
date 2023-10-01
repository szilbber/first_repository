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
    @Test
    public void extrapolateLeft(){
        double test = List.extrapolateLeft(-1);
        Assertions.assertEquals(3, test);
    }
    @Test
    public void extrapolateRight(){
        double test = List.extrapolateLeft(5);
        Assertions.assertEquals(9, test);
    }
    @Test
    public void interpolate(){
        double test = List.interpolate(2, 0);
        Assertions.assertEquals(6, test);
    }

    @Test
    public void getCount(){
        int x = List.getCount();
        Assertions.assertEquals(4, x);
    }
    @Test
    public void getX() {
        double x = List.getX(1);
        Assertions.assertEquals(2, x);
    }

    @Test
    public void getY() {
        double y = List.getY(1);
        Assertions.assertEquals(6, y);
    }
    @Test
    public void SetY() {
        List.SetY(3, 9);
        Assertions.assertEquals(9, List.getY(3));
    }
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