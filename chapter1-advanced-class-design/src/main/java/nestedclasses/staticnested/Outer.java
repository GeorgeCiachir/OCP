package nestedclasses.staticnested;

public  class Outer {

    public static class Inner {

        public static int someStaticValue = 2;
        public int nonStaticValue = 3;

        public Inner() {
            System.out.println("created Inner");
        }

        public int addToValue(int received){
            return someStaticValue + received;

        }
    }

    public Outer() {
        System.out.println("created Outer");
    }
}
