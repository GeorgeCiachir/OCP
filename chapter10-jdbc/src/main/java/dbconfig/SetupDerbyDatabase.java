package dbconfig;

import java.sql.*;

public class SetupDerbyDatabase {

    public static final String DB_URL = "jdbc:derby:zoodb";

    public static void main(String[] args) throws Exception {
        String createOption = ";create=true";
        String url = DB_URL + createOption;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // This is the old configuration, for the OCP 8 exam
            //stmt.executeUpdate("DROP TABLE animal");
            //stmt.executeUpdate("DROP TABLE species");

            stmt.executeUpdate("CREATE TABLE species ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL(4,1))");

            stmt.executeUpdate("CREATE TABLE animal ("
                    + "id INTEGER PRIMARY KEY, "
                    + "species_id integer REFERENCES species (id), "
                    + "name VARCHAR(255), "
                    + "date_born TIMESTAMP)");

            stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
            stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

            stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");

            ResultSet rs = stmt.executeQuery("select count(*) from animal");
            rs.next();
            System.out.println("Inserted a total of " + rs.getInt(1) + " animals");

            ////////////////////////////////////////////////////////////////////////////////////////
            // This is the new configuration, for the OCP 11 exam
            // is the same thing, but with different table names
            // run(conn,"DROP TABLE names");
            // run(conn,"DROP TABLE exhibits");

            System.out.println("The new setup");

            run(conn, "CREATE TABLE exhibits ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL(4,1))");

            run(conn, "CREATE TABLE names ("
                    + "id INTEGER PRIMARY KEY, "
                    + "species_id integer REFERENCES exhibits (id), "
                    + "name VARCHAR(255))");

            run(conn, "INSERT INTO exhibits VALUES (1, 'African Elephant', 7.5)");
            run(conn, "INSERT INTO exhibits VALUES (2, 'Zebra', 1.2)");

            run(conn, "INSERT INTO names VALUES (1, 1, 'Elsa')");
            run(conn, "INSERT INTO names VALUES (2, 2, 'Zelda')");
            run(conn, "INSERT INTO names VALUES (3, 1, 'Ester')");
            run(conn, "INSERT INTO names VALUES (4, 1, 'Eddie')");
            run(conn, "INSERT INTO names VALUES (5, 2, 'Zoe')");

            printCount(conn, "SELECT count(*) FROM names");
        }
    }

    private static void run(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    private static void printCount(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Inserted a total of " + rs.getInt(1) + " animals");
        }
    }
}
