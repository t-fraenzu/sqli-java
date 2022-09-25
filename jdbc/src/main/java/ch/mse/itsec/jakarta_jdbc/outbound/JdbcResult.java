package ch.mse.itsec.jakarta_jdbc.outbound;

import ch.mse.itsec.jakarta_jdbc.entities.Employee;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class JdbcResult {

    public List<Employee> prepStatementResult;

    public List<Employee> statementResult;
}
