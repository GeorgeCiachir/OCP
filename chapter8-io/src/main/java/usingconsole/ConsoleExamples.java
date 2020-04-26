package usingconsole;

import java.io.Console;

/**
 * java.io.Console doesn't work from/with IntelliJ
 * System.console() will return null in IntelliJ
 *
 * For this to work
 * 1. compile it -> javac ConsoleExamples.java
 * 2. run it with java -> java usingconsole.ConsoleExamples
 */
public class ConsoleExamples {

    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.out.println("Console is null");
            return;
        }

        console.flush();
        String name = console.readLine("Please input your name: ");
        console.printf("Name is %s", name);
        console.writer().println();

        console.flush();
        System.out.print("Input the username: ");
        String username = console.readLine();
        console.writer().println("The selected username is: " + username);


        // A more correct approach would be to verify the password using the char array
        // and after this, to add "garbage" data into the array, so that in case of a
        // memory dump, the data wouldn't be available for anyone else

        // basically, a good practice would be TO NOT STORE THE INPUT PASSWORD AS STRING
        // because strings are stored in the string pool and they would be available long
        // after the initial user interaction (basically the password would become available for anyone
        // that can do a memory dump)
        console.flush();
        console.writer().println("Input the password");
        char[] password = console.readPassword("Please input the password: ");
        console.writer().println("The password is: ");
        for (char c : password) {
            System.out.print(c);
        }
    }
}
