import java.io.IOException;
import java.sql.SQLException;

public class RethrowExamples {

    public static void main(String[] args) {



    }




    private static void catchAndRethrow() throws IOException, SQLException {
        try{
            mightThrow();
        } catch (Exception e) {
            e.printStackTrace();
            // Java knows that we only catch IOException or SQLException, so if we rethrow the same e is like saying
            // we rethrow IOException or SQLException
            Exception z = e; // does not work to rethrow z
            throw e;
        }
    }

    private static void mightThrow() throws IOException, SQLException {
    }
}
