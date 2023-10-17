package functions;


import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" +
                    + x + ", "
                    + y +
                    ')';
        }

        @Override
        public boolean equals(Object o) {
            if ((this == o)) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Double.compare(x, node.x) == 0 && Double.compare(y, node.y) == 0 && Objects.equals(next, node.next) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Node cloneNode = new Node(x,y);
            cloneNode.prev = this.prev;
            cloneNode.next = this.next;
            return cloneNode;
        }
    }

    private Node head;

    private Node getNode(int index) {
        Node i = head;
        try {
            if((index > count) || (index < 0)) throw new Exception("IllegalArgumentException");
            for (int index_temp = 0; index_temp != index; i = i.next, index_temp++) ;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
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

    LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if (xFrom > xTo) {
            double temp = xTo;
            xTo = xFrom;
            xFrom = temp;
        }
        double step = (xTo - xFrom) / (count - 1);
        double xCordinate = xFrom;
        if (xFrom == xTo) {
            for (int i = 1; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        } else {
            for (int i = 1; i < count; i++) {
                addNode(xCordinate, source.apply(xCordinate));
                xCordinate += step;
            }
        }
        head.x = xFrom;
        head.prev.x = xTo;
    }

    protected int floorIndexOfX(double x) {
        int index_floor = 0;
        try {
            if(x < head.x) throw new Exception("IllegalArgumentException");
            boolean allXsmaller = true;
            Node temp = head;
            for (int index_temp = 0; index_temp != count; ++index_temp) {
                if (temp.x < x) {
                    temp = temp.next;
                } else if (temp.x > x) {
                    allXsmaller = false;
                    index_floor = index_temp - 1;
                    if (index_temp == 0) {
                        index_floor = 0;
                    }
                    break;
                } else {
                    allXsmaller = false;
                    index_floor = index_temp - 1;
                    break;
                }
            }
            if (allXsmaller) index_floor = count;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return index_floor;
    }

    protected double extrapolateLeft(double x) {
        if ((head.next == head) && (head.prev == head)) {
            return x;
        } else {
            double leftY = getY(0);
            double leftX = getX(0);
            double rightY = getY(1);
            double rightX = getX(1);
            return interpolate(x, leftX, rightX, leftY, rightY);
        }
    }


    protected double extrapolateRight(double x) {
        if ((head.next == head) && (head.prev == head)) {
            return x;
        } else {
            double leftY = head.prev.prev.y;
            double leftX = head.prev.prev.x;
            double rightY = head.prev.y;
            double rightX = rightBound();
            return interpolate(x, leftX, rightX, leftY, rightY);
        }
    }


    protected double interpolate(double x, int floorIndex) {
        if ((head.next == head) && (head.prev == head)) {
            return x;
        } else {
            double leftY = getY(floorIndex);
            double leftX = getX(floorIndex);
            double rightY = getY(floorIndex + 1);
            double rightX = getX(floorIndex + 1);
            return interpolate(x, leftX, rightX, leftY, rightY);
        }
    }

    //Получение количества табулированных значений
    public int getCount() {
        return count;
    }


    public double getX(int index){
        try {
            if((index > count) || (index < 0)) throw new Exception("IllegalArgumentException");
        }catch(Exception e){
            e.getMessage();
        }
        return getNode(index).x;
    }


    public double getY(int index) {
        try {
            if((index > count) || (index < 0)) throw new Exception("IllegalArgumentException");
        }catch(Exception e){
            e.getMessage();
        }
        return getNode(index).y;
    }


    public void setY(int index, double value) {
        try {
            if((index > count) || (index < 0)) throw new Exception("IllegalArgumentException");
        }catch(Exception e){
            e.getMessage();
        }
        getNode(index).y = value;
    }


    public int indexOfX(double x) {
        int index = 0;
        boolean flag = true;
        for (Node i = head; i != head.prev; i = i.next) {
            if (i.x == x) {
                flag = false;
                break;
            } else index++;
        }
        if (flag && (x == head.prev.x)) {
            flag = false;
        }
        if (flag) index = -1;
        return index;
    }

    public int indexOfY(double y) {
        int index = 0;
        boolean flag = true;
        for (Node i = head; i != head.prev; i = i.next) {
            if (i.y == y) {
                flag = false;
                break;
            } else index++;
        }
        if (flag && (y == head.prev.y)) {
            flag = false;
        }
        if (flag) index = -1;
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

    protected Node floorNodeOfX(double x) {
        Node floorNode = head;
        boolean allXsmaller = true;
        Node temp = head;
        for (int index_temp = 0; index_temp != count; index_temp++) {
            if (temp.x < x) {
                temp = temp.next;
            } else if (temp.x > x) {
                allXsmaller = false;
                floorNode = temp.prev;
                if (index_temp == 0) {
                    floorNode = head;
                }
                break;
            } else if (temp.x == x) {
                allXsmaller = false;
                floorNode = temp;
                break;
            }
        }
        if (allXsmaller) floorNode = head.prev;
        return floorNode;
    }

    public double apply(double x){
        if (x < leftBound())
            return extrapolateLeft(x);
        else if (x > rightBound())
            return extrapolateRight(x);
        else {
            int searchIndexOfX = indexOfX(x);

            if (searchIndexOfX != -1)
                return getY(searchIndexOfX);
            else {
                Node froorNode = floorNodeOfX(x);
                return interpolate(x, froorNode.x, froorNode.next.x, froorNode.y, froorNode.next.y);
            }
        }
    }

    public void insert(double x, double y) {
        if (x < leftBound()) {
            insertNodeFront(head.prev, new Node(x, y));
            head = head.prev;
        } else {
            Node floorNode = floorNodeOfX(x);
            if (floorNode.next.x == x)
                floorNode.next.y = y;
            else if (floorNode.x == x)
                floorNode.y = y;
            else
                insertNodeFront(floorNode, new Node(x, y));
        }
    }

    public void insertNodeFront(Node currentNode, Node nextNode) {
        nextNode.next = currentNode.next;
        nextNode.prev = currentNode;
        currentNode.next.prev = nextNode;
        currentNode.next = nextNode;
        count++;
    }

    public void remove(int index) {
        Node currentNode = head;
        try {
            if((index>count)||(index < 0)) throw new Exception("IllegalArgumentException");
            while (index-- != 0)
                currentNode = currentNode.next;

            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            --count;

            if (currentNode == head) {
                if (count == 0)
                    head = null;
                else
                    head = currentNode.next;
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }


    }

    @Override
    public String toString() {

        String stringArray = "";
        for(int i = 0; i!=count; i++ ){
            stringArray += ("(" + getX(i) + ";" + getY(i) + ") ");
        }
        return stringArray;
    }

    @Override
    public boolean equals(Object o) {

        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction) o;
        boolean flag = true;
        for(int i = 0; i!=count; i++ ){
            if((this.getNode(i).x != that.getNode(i).x) || (this.getNode(i).y != that.getNode(i).y)){
                flag = false;
                break;
            }
        }
        return ((getClass() == that.getClass()) && (flag==true));
    }

    @Override
    public int hashCode() {
        int result = 31;
        for(int i = 0; i!=count; i++){
            result = result * 31 + this.getNode(i).hashCode();
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        double xCloneValues [] = new double[count];
        double yCloneValues [] = new double[count];
        for(int i = 0; i!=count; i++){
            xCloneValues[i] = this.getX(i);
            yCloneValues[i] = this.getY(i);
        }
        LinkedListTabulatedFunction cloneList = new LinkedListTabulatedFunction(xCloneValues,yCloneValues);
        return cloneList;
    }
}
