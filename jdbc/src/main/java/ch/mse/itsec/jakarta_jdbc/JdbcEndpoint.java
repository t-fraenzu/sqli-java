package ch.mse.itsec.jakarta_jdbc;


import ch.mse.itsec.jakarta_jdbc.entities.Employee;
import ch.mse.itsec.jakarta_jdbc.inbound.SearchRequest;
import ch.mse.itsec.jakarta_jdbc.outbound.JdbcResult;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/jdbc")
public class JdbcEndpoint {

    @POST
    @Produces("application/json")
    public JdbcResult getEmployers(SearchRequest sr) throws SQLException, NamingException {
        if(sr.queryName != null && !sr.queryName.isBlank()){

        }

        JdbcResult result = new JdbcResult();
        result.prepStatementResult = getEmployeesPrepStatement(sr);
        result.statementResult = getEmployeesDirty(sr);
        return result;
    }

    private List<Employee> getEmployeesDirty(SearchRequest sr) throws NamingException, SQLException {
        Context ctx = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySqli");

            con = ds.getConnection();


            stmt = con.createStatement();
            rs = stmt.executeQuery("select eid, ename from Employee " +
                                    "where ename like '%" + sr.queryName + "%' or eid = " + sr.queryId);

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int empid = rs.getInt("eid");
                String name = rs.getString("ename");
                employeeList.add(new Employee(empid, name, 0, "test"));
            }
            return employeeList;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw e;
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
                e.printStackTrace();
                throw e;
            } catch (NamingException e) {
                System.out.println("Exception in closing Context");
                e.printStackTrace();
                throw e;
            }
        }
    }

    private List<Employee> getEmployeesPrepStatement(SearchRequest sr) throws NamingException, SQLException {
        Context ctx = null;
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySqli");

            con = ds.getConnection();

            prepStmt = con.prepareStatement("select eid, ename from Employee where ename like ? or eid = ?");
            prepStmt.setString(1, sr.queryName);
            prepStmt.setInt(2, sr.queryId);
            rs = prepStmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int empid = rs.getInt("eid");
                String name = rs.getString("ename");
                employeeList.add(new Employee(empid, name, 0, "test"));
            }
            return employeeList;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                prepStmt.close();
                con.close();
                ctx.close();
            } catch (SQLException e) {
                System.out.println("Exception in closing DB resources");
                e.printStackTrace();
                throw e;
            } catch (NamingException e) {
                System.out.println("Exception in closing Context");
                e.printStackTrace();
                throw e;
            }
        }
    }
}
