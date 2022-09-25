package ch.mse.itsec.jakarta_jdbc.outbound;

import ch.mse.itsec.jakarta_jdbc.entities.Employee;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class JpaResult {

    public List<Employee> criteriaQuery;

    public List<Employee> untypedQuery;
}
