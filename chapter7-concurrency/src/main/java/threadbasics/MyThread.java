package threadbasics;

public class MyThread implements Runnable {

    String msg;

    public MyThread(String s) {
        msg = s;
    }

    public void run() {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        new Thread(new MyThread("String1")).run();
        new Thread(new MyThread("String2")).run();
        System.out.println("end");

        new Thread(new MyThread("String1")).start();
        new Thread(new MyThread("String2")).start();
        System.out.println("end");
    }

}
