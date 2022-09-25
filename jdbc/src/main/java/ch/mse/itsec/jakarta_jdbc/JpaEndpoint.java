package ch.mse.itsec.jakarta_jdbc;


import ch.mse.itsec.jakarta_jdbc.datasource.EntityManagerfactory;
import ch.mse.itsec.jakarta_jdbc.entities.Employee;
import ch.mse.itsec.jakarta_jdbc.inbound.SearchRequest;
import ch.mse.itsec.jakarta_jdbc.outbound.JdbcResult;
import ch.mse.itsec.jakarta_jdbc.outbound.JpaResult;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/jpa")
public class JpaEndpoint {

    @POST
    @Produces("application/json")
    public JpaResult getEmployers(SearchRequest sr) throws SQLException, NamingException {
        if(sr.queryName != null && !sr.queryName.isBlank()){

        }
        EntityManager entityManager = EntityManagerfactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.ename LIKE :ename")
                .setParameter("ename", "paramName").getResultList();
        JpaResult result = new JpaResult();
        result.criteriaQuery = getEmployeesPrepStatement(sr);
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
            rs = stmt.executeQuery("select empid, name from Employee " +
                                    "where name like '%" + sr.queryName + "%' or empid = " + sr.queryId);

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int empid = rs.getInt("empid");
                String name = rs.getString("name");
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

            prepStmt = con.prepareStatement("select empid, name from Employee where name like ? or empid = ?");
            prepStmt.setString(1, sr.queryName);
            prepStmt.setInt(2, sr.queryId);
            rs = prepStmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                int empid = rs.getInt("empid");
                String name = rs.getString("name");
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
