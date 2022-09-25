package ch.mse.itsec.jakarta_jdbc;


import ch.mse.itsec.jakarta_jdbc.entities.Employee;
import ch.mse.itsec.jakarta_jdbc.inbound.SearchRequest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/jdbc")
public class JdbcEndpoint {

    @POST
    @Produces("application/json")
    public List<Employee> getEmployers(SearchRequest sr) {
        if(sr.queryName != null && !sr.queryName.isBlank()){

        }

        Context ctx = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySqli");

            con = ds.getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery("select empid, name from Employee");
            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int empid = rs.getInt("empid");
                String name = rs.getString("name");
                employeeList.add(new Employee(empid, name, 0, "test"));
            }
            return employeeList;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                stmt.close();
                con.close();
                ctx.close();
            } catch (SQLException e) {
                System.out.println("Exception in closing DB resources");
            } catch (NamingException e) {
                System.out.println("Exception in closing Context");
            }
        }

        return Collections.emptyList();
    }
}
