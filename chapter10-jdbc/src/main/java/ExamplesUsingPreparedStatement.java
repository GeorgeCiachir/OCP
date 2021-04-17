import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import static dbconfig.SetupDerbyDatabase.DB_URL;

public class ExamplesUsingPreparedStatement {

    public static void main(String[] args) throws Exception {
        ExamplesUsingPreparedStatement client = new ExamplesUsingPreparedStatement();
        client.runExamples();
    }

    private void runExamples() throws Exception {
//        simpleOps();
//        updatingMultipleTimes();
//        batchUpdate();
//        traversingTheResultSet();
        extractingFromTheResultSet();
    }

    private void extractingFromTheResultSet() throws Exception {
        var selectSQL = "SELECT * FROM names";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ResultSet rs = ps.executeQuery();
            System.out.println(
                    rs.getString(1) + " ; " +
                            rs.getString(2) + " ; " +
                            rs.getString(3));
        }
    }

    private void traversingTheResultSet() throws Exception {
        var selectSQL = "SELECT * FROM names";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ResultSet rs = ps.executeQuery();
            System.out.println(
                    rs.getString(1) + " ; " +
                            rs.getString(2) + " ; " +
                            rs.getString(3));
        }
    }

    static class Name {

        public Name(int type, String name) {
            this.type = type;
            this.name = name;
        }

        int type;
        String name;
    }

    private void batchUpdate() throws Exception {
        int type = 2;
        List<Name> names = List.of(new Name(type, "Elsa"),
                new Name(type, "Gigi"),
                new Name(type, "Michael"));

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println(Arrays.toString(batchJob(conn, names, type)));
        }
    }

    private int[] batchJob(Connection conn, List<Name> names, int type) throws Exception {
        var preparedInsertSQL = "INSERT INTO names values(?,?,?)";
        var selectSQL = "SELECT * FROM names";
        try (PreparedStatement ps = conn.prepareStatement(preparedInsertSQL);
             PreparedStatement selectPs = conn.prepareStatement(selectSQL)) {
            int index = 22; //suppose we now the last index in the db is index - 1

            ps.setInt(2, type);
            for (Name name : names) {
                ps.setInt(1, index);
                ps.setString(3, name.name);
                ps.addBatch();
                index++;
            }

            return ps.executeBatch();
        }
    }

    private void updatingMultipleTimes() throws Exception {
        var preparedInsertSQL = "INSERT INTO names values(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(preparedInsertSQL)) {

            ps.setInt(1, 20);
            ps.setInt(2, 1);
            ps.setString(3, "Ester");
            ps.executeUpdate();

            ps.setInt(1, 21);
            ps.setString(3, "Elias");
            ps.executeUpdate();

            executeQueryForNames(conn);
        }
    }

    private void simpleOps() throws Exception {

        var insertSQL = "INSERT INTO exhibits values(10, 'Deer',3)";
        var updateSQL = "UPDATE exhibits SET name = '' WHERE name = 'None'";
        var deleteSQL = "DELETE FROM exhibits WHERE id = 10";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            var selectSQL = "SELECT * FROM exhibits";
            execute(conn, selectSQL);
            execute(conn, insertSQL);
            execute(conn, selectSQL);
            execute(conn, updateSQL);
            execute(conn, selectSQL);
            execute(conn, deleteSQL);
            execute(conn, selectSQL);

            executeQueryForExhibits(conn);
            executeUpdate(conn, insertSQL);
            executeQueryForExhibits(conn);
            executeUpdate(conn, updateSQL);
            executeQueryForExhibits(conn);
            executeUpdate(conn, deleteSQL);
            executeQueryForExhibits(conn);
        }
    }

    private void executeQueryForNames(Connection conn) throws Exception {
        var sql = "SELECT * FROM names";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " ; " +
                                rs.getInt(2) + " ; " +
                                rs.getString(3));
            }
        }
    }

    private void executeQueryForExhibits(Connection conn) throws Exception {
        var sql = "SELECT * FROM exhibits";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " ; " +
                                rs.getString(2) + " ; " +
                                rs.getDouble("NUM_ACRES"));
            }
        }
    }

    private void executeUpdate(Connection conn, String sql) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println(ps.executeUpdate());
        }
    }

    private void execute(Connection conn, String sql) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            boolean isRs = ps.execute();
            if (isRs) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {

                    System.out.println(
                            rs.getInt("id") + " ; " +
                                    rs.getString(2) + " ; " +
                                    rs.getDouble("NUM_ACRES"));
                }
            } else {
                System.out.println(ps.getUpdateCount());
            }
        }
    }
}

