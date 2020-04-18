package nestedclasses.staticnested;

public class Application {

    public static void main(String[] args) {
        foo();
    }

    public static void foo() {

        int value;
        value = new Outer.Inner().nonStaticValue;
        value = new Outer.Inner().someStaticValue;
        value = Outer.Inner.someStaticValue;

        new Outer.Inner().addToValue(2);
    }
}
