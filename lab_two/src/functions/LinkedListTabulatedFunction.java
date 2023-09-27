package functions;

class Node {
    public Node next;
    public Node prev;
    public double x;
    public double y;

    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

}

public class LinkedListTabulatedFunction implements AbstractTabulatedFunction{
    private Node head;
    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;

        }
        count++;
    }
    LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        for(int i = 0; i<xValues.length; i++){
            for(int j = 0; j<yValues.length; j++){
                addNode(xValues[i],yValues[j]);
            }
        }
    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if(xFrom > xTo){
            double temp = xTo;
            xTo = xFrom;
            xFrom = temp;
        }

        head.x = xFrom;
        head.prev.x = xTo;
        double step = (xFrom + xTo)/(count - 1);
        double xCordinate = xFrom;

        if(xFrom == xTo){
            for(int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }else{
            for(int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
                xCordinate += step;
            }
        }
    }


    protected int floorIndexOfX(double x) {
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return 0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return 0;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return 0;
    }

    //Получение количества табулированных значений
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return 0;
    }

    @Override
    public double getY(int index) {
        return 0;
    }

    @Override
    public void SetY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        return 0;
    }

    @Override
    public int indexOfY(double y) {
        return 0;
    }

    //Возвращает самый левый х
    public double leftBound() {
        return head.x;
    }

    //Возвращает самый правый х
    public double rightBound() {
        return head.prev.x;
    }
}
