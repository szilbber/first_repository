package myfirstpackage;

public class MySecondClass {
    private int a;
    private int b;

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public void SetA(int var1) {
        this.a = var1;
    }

    public void SetB(int var1) {
        this.b = var1;
    }

    public MySecondClass() {
        this.a = 0;
        this.b = 0;
    }

    public MySecondClass(int var1, int var2) {
        this.a = var1;
        this.b = var2;
    }

    public int Max() {
        return this.a > this.b ? this.a : this.b;
    }
}
