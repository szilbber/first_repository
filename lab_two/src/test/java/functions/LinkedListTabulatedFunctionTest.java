package functions;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListTabulatedFunctionTest {
    double[] xVal = {1,2,3,4};
    double[] yVal = {5,6,7,8};
    LinkedListTabulatedFunction List = new LinkedListTabulatedFunction(xVal,yVal);
    double[] x_one = {3};
    double[] y_one = {4};
    LinkedListTabulatedFunction List_one = new LinkedListTabulatedFunction(x_one,y_one);
    @Test
    public void floorIndexOfX(){
        double x = List.floorIndexOfX(3);
        Assertions.assertEquals(2, x);
        double x1 = List.floorIndexOfX(5);
        Assertions.assertEquals(4, x1);
        double x2 = List.floorIndexOfX(-1);
        Assertions.assertEquals(0, x2);
        double x3 = List.floorIndexOfX(3.5);
        Assertions.assertEquals(2, x3);
        double x4 = List.floorIndexOfX(4);
        Assertions.assertEquals(3, x4);
    }
    @Test
    public void extrapolateLeft(){
        double test = List.extrapolateLeft(-1);
        Assertions.assertEquals(3, test);
        double test1 = List_one.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
    }
    @Test
    public void extrapolateRight(){
        double test = List.extrapolateLeft(5);
        Assertions.assertEquals(9, test);
        double test1 = List_one.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
    }
    @Test
    public void interpolate(){
        double test = List.interpolate(2, 0);
        Assertions.assertEquals(6, test);
        double test1 = List_one.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
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
    @Test
    public void floorNodeOfX(){
        Node test = List.floorNodeOfX(3);
        Assertions.assertEquals(3, test.x);

        Node x1 = List.floorNodeOfX(5);
        Assertions.assertEquals(4, x1.x);

        Node x2 = List.floorNodeOfX(-1);
        Assertions.assertEquals(1, x2.x);

        Node x3 = List.floorNodeOfX(3.5);
        Assertions.assertEquals(3, x3.x);

        Node x4 = List.floorNodeOfX(4);
        Assertions.assertEquals(4, x4.x);
    }
    @Test
    public void apply(){
        double test = List.apply(-1);
        Assertions.assertEquals(List.extrapolateLeft(-1), test);
        double test1 = List.apply(10);
        Assertions.assertEquals(List.extrapolateRight(10), test1);
        double test2 = List.apply(3.5);
        Assertions.assertEquals(List.interpolate(3.5, 2), test2);
    }

}