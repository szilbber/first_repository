package functions;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LinkedListTabulatedFunctionTest {
    double[] xVal = {1, 2, 3, 4};
    double[] yVal = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal, yVal);

    MathFunction sqrFunctions = new SqrFunctions();
    LinkedListTabulatedFunction listTwo = new LinkedListTabulatedFunction(sqrFunctions, 0, 100, 10);


    @Test
    public void floorIndexOfX() {
        double x = list.floorIndexOfX(3);
        Assertions.assertEquals(1, x);
        double x3 = list.floorIndexOfX(3.5);
        Assertions.assertEquals(2, x3);
        double x4 = list.floorIndexOfX(4);
        Assertions.assertEquals(2, x4);
        Assertions.assertEquals(0, listTwo.floorIndexOfX(10));
        Assertions.assertEquals(1, listTwo.floorIndexOfX(15));
    }

    @Test
    public void extrapolateLeft() {
        double test = list.extrapolateLeft(-1);
        Assertions.assertEquals(3, test);

    }

    @Test
    public void extrapolateRight() {
        double test = list.extrapolateLeft(5);
        Assertions.assertEquals(9, test);
    }

    @Test
    public void interpolate() {
        double test = list.interpolate(2, 0);
        Assertions.assertEquals(6, test);
    }

    @Test
    public void getCount() {
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
    public void indexOfX() {
        int index = list.indexOfX(4);
        Assertions.assertEquals(3, index);
        Assertions.assertEquals(-1, list.indexOfX(15));
    }

    @Test
    public void indexOfY() {
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
    public void floorNodeOfX() {
        Assertions.assertEquals(3, list.floorNodeOfX(3).x);

        Assertions.assertEquals(3, list.floorNodeOfX(3.5).x);

        Assertions.assertEquals(4, list.floorNodeOfX(4).x);
    }

    @Test
    public void apply() {
        double test = list.apply(-1);
        Assertions.assertEquals(list.extrapolateLeft(-1), test);
        double test1 = list.apply(10);
        Assertions.assertEquals(list.extrapolateRight(10), test1);
        double test2 = list.apply(3.5);
        Assertions.assertEquals(list.interpolate(3.5, 2), test2);
    }

    @Test
    public void insertTest() {
        list.insert(1, 10);
        Assertions.assertEquals(10., list.getY(0));
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
    public void removeTest() {
        list.remove(0);
        Assertions.assertEquals(6.0, list.getY(0));
        list.remove(1);
        Assertions.assertEquals(8.0, list.getY(1));
        list.remove(list.getCount() - 1);
        Assertions.assertEquals(6.0, list.getY(list.getCount() - 1));
    }

    @Test
    public void toStringNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(1, 5);
        Assertions.assertEquals("(1.0, 5.0)", node.toString());
    }

    @Test
    public void equalsNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(3, 4);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(3, 4);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(3, 7);
        Assertions.assertEquals(node2, node);
        assertNotEquals(true, node3.equals(node2));
    }

    @Test
    public void hashCodeNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(1, 5);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(1, 5);
        Assertions.assertEquals(node2.hashCode(), node.hashCode());
    }

    @Test
    public void cloneNodeTest() throws CloneNotSupportedException {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(3, 4);
        Assertions.assertEquals(node, node.clone());
    }

    @Test
    public void equalsTest() {
        double[] xVal = {1, 2, 3, 4};
        double[] yVal = {5, 6, 7, 8};
        LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xVal, yVal);

        double[] xVal2 = {1, 2, 3, 5};
        double[] yVal2 = {5, 6, 7, 8};
        LinkedListTabulatedFunction list2 = new LinkedListTabulatedFunction(xVal2, yVal2);

        Assertions.assertEquals(list, list1);
        assertNotEquals(list, list2);
    }

    @Test
    public void hashCodeTest() {
        double[] xVal = {1, 2, 3, 4};
        double[] yVal = {5, 6, 7, 8};
        LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xVal, yVal);

        double[] xVal2 = {1, 2, 3, 4};
        double[] yVal2 = {5, 6, 7, 8};
        LinkedListTabulatedFunction list2 = new LinkedListTabulatedFunction(xVal2, yVal2);

        Assertions.assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Assertions.assertEquals(list, list.clone());
    }

    @Test
    public void iteratorTest1() {
        Iterator<Point> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assertions.assertEquals(point.x, list.getX(index));
            Assertions.assertEquals(point.y, list.getY(index));
            index++;
        }
    }

    @Test
    public void iteratorTest2() {
        int index = 0;
        for (Point point : list) {
            Assertions.assertEquals(point.x, list.getX(index));
            Assertions.assertEquals(point.y, list.getY(index));
            index++;
        }
    }

    @Test
    public void toString2Test() {
        Assertions.assertEquals(list.toString(), "LinkedListTabulatedFunction size = 4\n[1.0; 5.0]\n[2.0; 6.0]\n[3.0; 7.0]\n[4.0; 8.0]\n");
    }
}