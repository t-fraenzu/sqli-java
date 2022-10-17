package ch.mse.itsec.jakarta_jdbc.security;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/secured")
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"admin_role", "special_forces"}
        )
)
public class SecureServlet extends HttpServlet {
}
