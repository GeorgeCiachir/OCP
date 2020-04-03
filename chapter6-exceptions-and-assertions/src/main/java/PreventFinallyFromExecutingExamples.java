public class PreventFinallyFromExecutingExamples {

    public static void main(String[] args) {



        try {
            System.out.println("Second");
            System.exit(1);
        } finally {
            System.out.println("should print something here");
        }
    }
}
