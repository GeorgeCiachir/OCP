package threadbasics;

public class BasicExamples implements Runnable {

    String msg;

    public BasicExamples(String s) {
        msg = s;
    }

    public void run() {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        new Thread(new BasicExamples("String1")).run();
        new Thread(new BasicExamples("String2")).run();
        System.out.println("end");

        new Thread(new BasicExamples("String1")).start();
        new Thread(new BasicExamples("String2")).start();
        System.out.println("end");
    }

}
