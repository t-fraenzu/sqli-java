package ch.mse.itsec.jakarta_jdbc.entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Employee")
@XmlRootElement
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int eid;
    protected String ename;
    protected double salary;
    protected String workfunction;

    public Employee(int eid, String ename, double salary, String workfunction) {
        this.eid = eid;
        this.ename = ename;
        this.salary = salary;
        this.workfunction = workfunction;
    }

    public Employee( ) {
        super();
    }

    public int getEid( ) {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname( ) {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary( ) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorkfunction( ) {
        return workfunction;
    }

    public void setWorkfunction(String deg) {
        this.workfunction = deg;
    }

    @Override
    public String toString() {
        return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + workfunction + "]";
    }
}