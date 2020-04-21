import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.MissingResourceException;

public class MulticatchExamples {

    public static void main(String[] args) {

        try {
            mightThrow();
//        } catch (SQLException | ArrayIndexOutOfBoundsException e) { // does not compile because the checked SQLException is not declared on the method's signature

        } catch (FileNotFoundException | IllegalArgumentException e) {

        } catch (IOException e) {

        } catch (InputMismatchException | MissingResourceException e) {
            // does not compile
            // because Java doesn't know what's the type of the exception (it could be InputMismatchException or MissingResourceException)
            // it makes it final, so that it cannot be reassigned
            // MissingResourceException e = new new InputMismatchException() // wouldn't compile
            // InputMismatchException e = new new MissingResourceException() // also wouldn't compile

            // e = new InputMismatchException();

            // it resembles the way a List<? extends Number> aList = new ArrayList<Integer>() becomes effectively final
            // see chapter3-generics-and-collections -> package UpperBounded -> List<? extends Bird> logicallyImmutableBirds = new ArrayList<>();

        } catch (Exception e) {

        }


    }

    private static void mightThrow() throws DateTimeParseException, IOException {

    }
}
