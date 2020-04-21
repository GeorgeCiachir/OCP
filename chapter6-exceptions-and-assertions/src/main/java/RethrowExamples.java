import java.io.IOException;
import java.sql.SQLException;

public class RethrowExamples {

    public static void main(String[] args) {

    }

    private static void catchAndRethrow() throws IOException, SQLException {
        try {
            mightThrow();
        } catch (Exception e) {
            e.printStackTrace();
            // Java knows that mightThrow() can throw only IOException or SQLException, so if we rethrow
            // the same e is like saying we rethrow IOException or SQLException

            // Even if we declare e as Exception it is in fact only IOException or SQLException

            Exception z = e;
            // throw z; // Can't rethrow z -> I have to declare Exception on the method signature
            throw e;
        }
    }

    private static void mightThrow() throws IOException, SQLException {
    }
}
