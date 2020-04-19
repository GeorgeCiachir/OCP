package wrappers;

public class WrapperClasses {

    public static void main(String[] args) {

        int primitiveInt = 2;
        Integer i = primitiveInt;

        long primitiveLong;
        primitiveLong = primitiveInt;
        primitiveLong = i;

        Long l;
        l = (long) primitiveInt;
        l = new Long(primitiveInt);
        l = Long.valueOf(primitiveInt);
        l = new Long(i);
        l = Long.valueOf(i);


        double primitiveDouble;
        primitiveDouble = primitiveInt;
        primitiveDouble = i;
        primitiveDouble = l;

        Double d;
        d = (double) primitiveInt;
        d = new Double(primitiveInt);
        d = Double.valueOf(primitiveInt);
        d = new Double(i);
        d = Double.valueOf(i);
        d = new Double(l);
        d = Double.valueOf(l);
    }
}
