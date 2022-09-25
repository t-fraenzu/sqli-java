package ch.mse.itsec.jakarta_jdbc;

import ch.mse.itsec.jakarta_jdbc.datasource.EntityManagerfactory;
import ch.mse.itsec.jakarta_jdbc.entities.Employee;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/JPADatasource")
public class JPADatasource extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EntityManager entityManager = EntityManagerfactory.createEntityManager();
            List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.name LIKE :ename")
                    .setParameter("ename", "paramName").getResultList();

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<html><body><h2>Employee Details</h2>");
            out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<th>Employee ID</th>");
            out.print("<th>Employee Name</th>");

            for (Employee employee : employees) {
                out.print("<tr>");
                out.print("<td>" + employee.getEid() + "</td>");
                out.print("<td>" + employee.getEname() + "</td>");
                out.print("</tr>");
            }

            out.print("</table></body><br/>");
            //lets print some DB information
            out.print("<h3>Database Details</h3>");

            Connection connection = entityManager.unwrap(Connection.class);
            DatabaseMetaData metaData = connection.getMetaData();

            out.print("Database Product: " + metaData.getDatabaseProductName() + "<br/>");
            out.print("Database Driver: " + metaData.getDriverName());
            out.print("</html>");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
