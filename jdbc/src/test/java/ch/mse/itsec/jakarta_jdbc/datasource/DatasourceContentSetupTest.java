package ch.mse.itsec.jakarta_jdbc.datasource;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatasourceContentSetupTest {

    @Test
    void setupDatabase() {
        DataSource ds = new DatasourceFactory().getMySQLDataSource();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();

            dropTableIfExists(stmt);
            createNewTable(stmt);
            insertData(stmt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void insertData(Statement stmt) throws SQLException {
        var result = stmt.executeUpdate("""
                -- insert some sample data
                INSERT INTO EmployeeUT (empId, name)
                VALUES
                    (1, '1Pankaj'),
                    (2, '2David'),
                    (3, '3Otto');
                """);
    }

    private static void createNewTable(Statement stmt) throws SQLException {
        var result5 = stmt.executeUpdate("""
                    CREATE TABLE EmployeeUT (
                      empId int(10) unsigned NOT NULL,
                      name varchar(10) DEFAULT NULL,
                      PRIMARY KEY (empId)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;                
                """);
    }

    private static void dropTableIfExists(Statement stmt) throws SQLException {
        var result3 = stmt.executeUpdate("""
                DROP TABLE IF EXISTS EmployeeUT""");
    }
}
