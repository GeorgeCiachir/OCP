package nestedclasses.innerclasses;

public class A {

    private int nestingLevel = 0;

    private class B {

        private int nestingLevel = 1;

        private class C {

            private int nestingLevel = 2;

            private class D {

                private int nestingLevel = 3;

                private D() {
                    System.out.println("created D");
                }

                private void printStuff() {
                    System.out.println(D.this.nestingLevel);
                    System.out.println(C.this.nestingLevel);
                    System.out.println(B.this.nestingLevel);
                    System.out.println(A.this.nestingLevel);
                }
            }

            private C() {
                System.out.println("created C");
            }
        }

        private B() {
            System.out.println("created B");
        }
    }

    public A() {
        System.out.println("created A");
    }

    public static void main(String[] args) {
        B b = new A().new B();
        B.C c = new A().new B().new C();
        B.C.D d = new A().new B().new C().new D();

        d.printStuff();

    }

}
