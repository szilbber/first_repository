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
    private Node getNode(int index){
        //int index_temp = 0;
        Node i = head;
        for(int index_temp = 0; index_temp!=index; i = i.next, index_temp++){}
        return i;
    }
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
        int index_maxX = 0;
        int index_temp = 0;
        double maxX = head.x;
        boolean allXbigger = true;
        boolean allXsmaller = true;
        for(Node i = head; i!=head.prev; i = i.next, index_temp++) {
            if((i.x < x) && (i.x >= maxX)) {
                maxX = i.x;
                index_maxX = index_temp;
                allXbigger = false;
                allXsmaller = false;
            }
            if(i.x>x){
                allXsmaller = false;
            }
            if(i.x>x){
                allXbigger = false;
            }
        }
        if(allXsmaller) index_maxX = count;
        if(allXbigger) index_maxX = 0;
        return index_maxX;
    }

    protected double extrapolateLeft(double x) {
        return 0;
    }


    protected double extrapolateRight(double x) {
        return 0;
    }


    protected double interpolate(double x, int floorIndex) {
        return 0;
    }

    //Получение количества табулированных значений
    public int getCount() {
        return count;
    }


    public double getX(int index) {
        return getNode(index).x;
    }


    public double getY(int index) {
        return getNode(index).y;
    }


    public void SetY(int index, double value) {
        getNode(index).y = value;
    }


    public int indexOfX(double x) {
        int index = 0;
        boolean flag = true;
        for(Node i = head; i!=head.prev; i = i.next){
            if(i.x == x) {flag = false; break;}
            else index++;
        }
        if(flag) index = -1;
        return index;
//        int index = -1;
//        for(Node i = head; i!=head.prev; i = i.next) {
//            if (i.x == x) {
//                if (index == -1){
//                    index = 0;
//                    break;
//                }
//                break;
//            } else index++;
//        }
        //return index;
    }

    public int indexOfY(double y) {
        int index = 0;
        boolean flag = true;
        for(Node i = head; i!=head.prev; i = i.next){
            if(i.y == y) {flag = false; break;}
            else index++;
        }
        if(flag) index = -1;
        return index;
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
