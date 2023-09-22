import myfirstpackage.*;
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