import dbconfig.SetupDerbyDatabase;

import java.sql.*;

import static dbconfig.SetupDerbyDatabase.DB_URL;

public class Test {

    public static void main(String[] args) throws SQLException {

//        usingTheDefaultForwardResultSet();
        usingScrollableResultSet();

    }

    private static void usingScrollableResultSet() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = statement.executeQuery("select id, name from animal order by id")) {

            rs.afterLast();
            System.out.println(rs.isAfterLast());

            System.out.println(rs.previous());
            System.out.println(rs.getInt("id"));
            System.out.println(rs.first());
            System.out.println(rs.getInt("id"));
            System.out.println(rs.last());
            System.out.println(rs.getInt("id"));

            rs.beforeFirst();
            System.out.println(rs.isBeforeFirst());

            System.out.println(rs.next());
            System.out.println(rs.getInt("id"));


            System.out.println();
            System.out.println("Using absolute()");
            System.out.println();

            // this moves the cursor at position 0 (before the nxt row), so next() has to be called in order to get the first rs
            // otherwise, a SQLException: Invalid cursor state - no current row will be thrown when accessing the rs
            System.out.println(rs.absolute(0));
            rs.next();
            System.out.println(rs.getString("name"));
            System.out.println(rs.absolute(1));
            System.out.println(rs.getString("name"));

            // This is the last position
            System.out.println(rs.absolute(-1));
            System.out.println(rs.getString("name"));
            // This is the first position
            System.out.println(rs.absolute(-5));
            System.out.println(rs.getString("name"));


            System.out.println();
            System.out.println("Using relative()");
            System.out.println();

            System.out.println(rs.first());
            System.out.println(rs.getInt("id"));
            System.out.println(rs.relative(2));
            System.out.println(rs.getInt("id"));
            System.out.println(rs.relative(-1));
            System.out.println(rs.getInt("id"));

        }
    }

    private static void usingTheDefaultForwardResultSet() throws SQLException {
        try (Connection connection = DriverManager.getConnection(SetupDerbyDatabase.DB_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select name from animal")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }


        try (Connection connection = DriverManager.getConnection(SetupDerbyDatabase.DB_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select date_born, species_id from animal where name = 'Elsa'")) {


            if (rs.next()) {
                Date sqlDate = rs.getDate(1);
                System.out.println(sqlDate.toLocalDate());

                Time sqlTime = rs.getTime(1);
                System.out.println(sqlTime.toLocalTime());

                Timestamp sqlTimestamp = rs.getTimestamp("date_born");
                System.out.println(sqlTimestamp.toInstant());
                System.out.println(sqlTimestamp.toLocalDateTime());

                System.out.println("Species id: " + rs.getObject("species_id"));
            }
        }
    }
}
