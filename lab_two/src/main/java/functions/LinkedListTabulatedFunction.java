package functions;


import exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    @Serial
    private static final long serialVersionUID = 324484052879874044L;
    @Override
    public Iterator<Point> iterator() throws UnsupportedOperationException {
        //throw new UnsupportedOperationException();
        return new Iterator<Point>() {
            Node node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                Point p;
                if (hasNext()) {
                    p = new Point(node.x, node.y);
                    if (node.next == head) node = null;
                    else node = node.next;
                } else throw new NoSuchElementException();
                return p;
            }
        };
    }
    static class Node implements Serializable {
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
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        Node i = head;
        for (int index_temp = 0; index_temp != index; i = i.next, index_temp++) ;
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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        if (xValues.length < 2)
            throw new IllegalArgumentException("Длина меньше минимальной");

        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Длина меньше минимальной");
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
        if (x < head.x) throw new IllegalArgumentException("х меньше левой границы");
        int index_floor = 0;
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
                index_floor = index_temp-1;
                break;
            }
        }
        if (allXsmaller) index_floor = count;
        return index_floor;
    }

    protected double extrapolateLeft(double x)throws IllegalArgumentException {

            double leftY = getY(0);
            double leftX = getX(0);
            double rightY = getY(1);
            double rightX = getX(1);
            return interpolate(x, leftX, rightX, leftY, rightY);

    }


    protected double extrapolateRight(double x) {

            double leftY = head.prev.prev.y;
            double leftX = head.prev.prev.x;
            double rightY = head.prev.y;
            double rightX = rightBound();
            return interpolate(x, leftX, rightX, leftY, rightY);

    }


    protected double interpolate(double x, int floorIndex) {
        if (getX(floorIndex) >= x)
            throw new InterpolationException("Range error for interpolation");

        double leftY = getY(floorIndex);
            double leftX = getX(floorIndex);
            double rightY = getY(floorIndex + 1);
            double rightX = getX(floorIndex + 1);
            return interpolate(x, leftX, rightX, leftY, rightY);

    }

    //Получение количества табулированных значений
    public int getCount() {
        return count;
    }


    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        return getNode(index).x;
    }


    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        return getNode(index).y;
    }


    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
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
        if (x < head.x) throw new IllegalArgumentException("х меньше левой границы");

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

    public double apply(double x) {
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

    public void remove(int index) throws IllegalArgumentException {
        Node nodeToRemove = getNode(index);

        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;

        if (nodeToRemove == head)
            head = nodeToRemove.next;

        if (count == 1)
            head = null;

        count--;
    }

//    @Override
//    public String toString() {
//
//        String stringArray = "";
//        for(int i = 0; i!=count; i++ ){
//            stringArray += ("(" + getX(i) + ";" + getY(i) + ") ");
//        }
//        return stringArray;
//    }

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
        double[] xCloneValues = new double[count];
        double[] yCloneValues = new double[count];
        for (int i = 0; i != count; i++) {
            xCloneValues[i] = this.getX(i);
            yCloneValues[i] = this.getY(i);
        }
        return new LinkedListTabulatedFunction(xCloneValues, yCloneValues);
    }
}
