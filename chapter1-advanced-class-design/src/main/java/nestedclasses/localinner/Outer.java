package nestedclasses.localinner;

public class Outer {

    private int outerClassValue = 2;


    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.printSomething(22);
    }

    private void printSomething(int received) {

        int v = 5;

        class LocalInner {

            private void useValues() {
                // v++; //doesn't compile. v needs to be final or effectively final
                System.out.println(v);

                // received++; //doesn't compile. v needs to be final or effectively final
                System.out.println(received);

                outerClassValue++; // not a problem when modifying the outer class member
                System.out.println(outerClassValue);

            }
        }


        LocalInner localInner = new LocalInner();
        localInner.useValues();
    }

}

