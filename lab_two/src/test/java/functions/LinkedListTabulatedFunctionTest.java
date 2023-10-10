package functions;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListTabulatedFunctionTest {
    double[] xVal = {1,2,3,4};
    double[] yVal = {5,6,7,8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal,yVal);
    double[] x_one = {3};
    double[] y_one = {4};
    LinkedListTabulatedFunction listOne = new LinkedListTabulatedFunction(x_one,y_one);

    @Test
    public void floorIndexOfX(){
        double x = list.floorIndexOfX(3);
        Assertions.assertEquals(2, x);
        double x1 = list.floorIndexOfX(5);
        Assertions.assertEquals(4, x1);
        double x2 = list.floorIndexOfX(-1);
        Assertions.assertEquals(0, x2);
        double x3 = list.floorIndexOfX(3.5);
        Assertions.assertEquals(2, x3);
        double x4 = list.floorIndexOfX(4);
        Assertions.assertEquals(3, x4);
    }
    @Test
    public void extrapolateLeft(){
        double test = list.extrapolateLeft(-1);
        Assertions.assertEquals(3, test);
        double test1 = listOne.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
    }
    @Test
    public void extrapolateRight(){
        double test = list.extrapolateLeft(5);
        Assertions.assertEquals(9, test);
        double test1 = listOne.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
    }
    @Test
    public void interpolate(){
        double test = list.interpolate(2, 0);
        Assertions.assertEquals(6, test);
        double test1 = listOne.interpolate(3, 0);
        Assertions.assertEquals(3, test1);
    }

    @Test
    public void getCount(){
        int x = list.getCount();
        Assertions.assertEquals(4, x);
    }
    @Test
    public void getX() {
        double x = list.getX(1);
        Assertions.assertEquals(2, x);
    }

    @Test
    public void getY() {
        double y = list.getY(1);
        Assertions.assertEquals(6, y);
    }
    @Test
    public void SetY() {
        list.setY(3, 9);
        Assertions.assertEquals(9, list.getY(3));
    }
    @Test
    public void indexOfX(){
        int index = list.indexOfX(4);
        Assertions.assertEquals(3, index);
    }
    @Test
    public void indexOfY(){
        int index = list.indexOfY(7);
        Assertions.assertEquals(2, index);
    }
    @Test
    public void leftBound() {
        double x = list.leftBound();
        Assertions.assertEquals(1, x);
    }
    @Test
    //Возвращает самый правый х
    public void rightBound() {
        double x = list.rightBound();
        Assertions.assertEquals(4, x);
    }
    @Test
    public void floorNodeOfX(){
        Assertions.assertEquals(3, list.floorNodeOfX(3).x);

        Assertions.assertEquals(4, list.floorNodeOfX(5).x);

        Assertions.assertEquals(1, list.floorNodeOfX(-1).x);

        Assertions.assertEquals(3, list.floorNodeOfX(3.5).x);

        Assertions.assertEquals(4, list.floorNodeOfX(4).x);
    }
    @Test
    public void apply(){
        double test = list.apply(-1);
        Assertions.assertEquals(list.extrapolateLeft(-1), test);
        double test1 = list.apply(10);
        Assertions.assertEquals(list.extrapolateRight(10), test1);
        double test2 = list.apply(3.5);
        Assertions.assertEquals(list.interpolate(3.5, 2), test2);
    }

    @Test
    public void insertTest(){
        list.insert(1, 10);
        Assertions.assertEquals(10.,list.getY(0));
        list.insert(2, 10);
        Assertions.assertEquals(10, list.getY(1));
        list.insert(1.5, 6);
        Assertions.assertEquals(6., list.getY(1));
        list.insert(-10, -50);
        Assertions.assertEquals(-50., list.getY(0));
        Assertions.assertEquals(10, list.getY(1));
        list.insert(100, 100);
        Assertions.assertEquals(100., list.getY(list.getCount() - 1));
    }

    @Test
    public void removeTest(){
        list.remove(0);
        Assertions.assertEquals(6.0, list.getY(0));
        list.remove(1);
        Assertions.assertEquals(8.0, list.getY(1));
        list.remove(list.getCount() - 1);
        Assertions.assertEquals(6.0, list.getY(list.getCount()- 1));
    }
}