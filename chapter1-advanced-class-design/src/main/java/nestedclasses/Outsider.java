package nestedclasses;

class NonStaticInsider {
}

class StaticInsider {
}

public class Outsider {

    class NonStaticInsider {

    }

    static class StaticInsider {

    }

    public static void staticContext() {
        Outsider.NonStaticInsider in = new Outsider().new NonStaticInsider();
        NonStaticInsider in2 = new Outsider().new NonStaticInsider();
//        Outsider.NonStaticInsider in3 = new Outsider.NonStaticInsider();

        Outsider.StaticInsider staticInsider = new Outsider.StaticInsider();
        StaticInsider staticInsider2 = new StaticInsider();
//        Outsider.StaticInsider staticInsider3 = new Outsider().new StaticInsider();
    }

    public void nonStaticContext() {
        Outsider.NonStaticInsider in = new Outsider().new NonStaticInsider();
        NonStaticInsider in2 = new NonStaticInsider();
        Outsider.NonStaticInsider in3 = new Outsider.NonStaticInsider();

        Outsider.StaticInsider staticInsider = new Outsider.StaticInsider();
        StaticInsider staticInsider2 = new StaticInsider();
//        Outsider.StaticInsider staticInsider3 = new Outsider().new StaticInsider();
    }
}
