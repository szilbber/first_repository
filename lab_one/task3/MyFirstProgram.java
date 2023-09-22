class MyFirstClass {
    public static void main(String[] var0) {
        MySecondClass var1 = new MySecondClass();
        System.out.println(var1.Max());

        for (int var2 = 1; var2 <= 8; ++var2) {
            for (int var3 = 1; var3 <= 8; ++var3) {
                var1.SetA(var2);
                var1.SetB(var3);
                System.out.print(var1.Max());
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}

class MySecondClass {
    private int a;
    private int b;

    int getA() {
        return this.a;
    }

    int getB() {
        return this.b;
    }

    void SetA(int var1) {
        this.a = var1;
    }

    void SetB(int var1) {
        this.b = var1;
    }

    MySecondClass() {
        this.a = 0;
        this.b = 0;
    }

    MySecondClass(int var1, int var2) {
        this.a = var1;
        this.b = var2;
    }

    int Max() {
        return this.a > this.b ? this.a : this.b;
    }
}
