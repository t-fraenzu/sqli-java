package ch.mse.itsec.jakarta_jdbc.datasource;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatasourceFactoryTest {

    @Test
    void testDataSource() {
        new DatasourceContentSetupTest().setupDatabase();
        DataSource ds = new DatasourceFactory().getMySQLDataSource();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select empid, name from EmployeeUT");
            while(rs.next()){
                System.out.println("Employee ID="+rs.getInt("empid")+", Name="+rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("could not select data from db");
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}