package ch.mse.itsec.jakarta_jdbc.demostuff;

import java.io.*;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
@ServletSecurity(@HttpConstraint(rolesAllowed = "known_user"))
public class HelloServlet extends HttpServlet {
    private String message;

    @Inject
    private SecurityContext securityContext;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        test_method();
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void test_method()
    {
        if(true){
            throw new RuntimeException("since no login occured");
        }
    }

    public void destroy() {
    }
}