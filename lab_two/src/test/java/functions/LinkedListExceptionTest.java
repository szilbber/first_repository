package functions;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListExceptionTest {
    double[] xVal = {1,2,3,4};
    double[] yVal = {5,6,7,8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xVal,yVal);

    @Test(expected = IllegalArgumentException.class)
    public void createLinkedListOne(){
        double[] x_one = {3};
        double[] y_one = {4};
        LinkedListTabulatedFunction listOne = new LinkedListTabulatedFunction(x_one,y_one);
    }
    @Test(expected = IllegalArgumentException.class)
    public void createLinkedList(){
        MathFunction sqrFunctions = new SqrFunctions();
        LinkedListTabulatedFunction listTwo = new LinkedListTabulatedFunction(sqrFunctions,0,100,1);
    }
    @Test
    public void getXExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.getX(10));
    }
    @Test
    public void getYExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.getY(10));
    }
    @Test
    public void setYExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.setY(10, 20));
    }
    @Test
    public void FloorIndexOfXExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.floorIndexOfX(-5));
    }
    @Test
    public void FloorNodeOfXExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.floorNodeOfX(-5));
    }
    @Test
    public void removeExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> list.remove(-5));
    }

}
