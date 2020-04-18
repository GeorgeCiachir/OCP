public class TestClass {

    public static void main(String[] args) {
        Office off = new HomeOffice();
//        System.out.println(off.getAddress()); // nok -> does not compile -> Static method may be invoked on containing interface class only
        System.out.println(Office.getAddress()); // ok
        System.out.println(new HomeOffice().getAddress());
    }
}

interface House {

    default String getAddress() {
        return "101 Main Str";
    }
}

interface Office {

    static String getAddress() {
        return "101 Smart Str";
    }
}

interface WFH extends House, Office {
}

class HomeOffice implements House, Office {
    public String getAddress() {
        return "R No 1, Home";
    }
}
