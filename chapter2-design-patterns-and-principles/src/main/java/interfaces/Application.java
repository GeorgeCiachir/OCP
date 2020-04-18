package interfaces;

public class Application {


    public static void main(String[] args) {
        Application application = new Application();
        B.fooBar();

        System.out.println(B.VALUE);
        System.out.println(FirstClass.VALUE);
        application.new FirstClass().bar();
        application.new SecondClass().bar();
    }

    class FirstClass implements A {

        @Override
        public void foo() {

        }
    }

    class SecondClass implements A {

        @Override
        public void foo() {

        }

        @Override
        public void bar() {
            System.out.println("bar in SecondClass");
        }


        public void fooBar() {
            System.out.println("fooBar in SecondClass");
        }
    }

    interface A extends B, C {
    }

    interface B {

        int VALUE = 5;

        void foo();

        default void bar() {
            System.out.println("bar in interface B called from " + this.getClass());
        }

        static void fooBar() {
            System.out.println("fooBar in interface B");
        }

    }

    interface C {
        void foo();
    }

}
