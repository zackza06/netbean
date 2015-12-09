/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findByEmpnum", query = "SELECT e FROM Employees e WHERE e.employeesPK.empnum = :empnum"),
    @NamedQuery(name = "Employees.findByEmpname", query = "SELECT e FROM Employees e WHERE e.empname = :empname"),
    @NamedQuery(name = "Employees.findByHiredate", query = "SELECT e FROM Employees e WHERE e.hiredate = :hiredate"),
    @NamedQuery(name = "Employees.findBySalary", query = "SELECT e FROM Employees e WHERE e.salary = :salary"),
    @NamedQuery(name = "Employees.findByPosition", query = "SELECT e FROM Employees e WHERE e.position = :position"),
    @NamedQuery(name = "Employees.findByMgrno", query = "SELECT e FROM Employees e WHERE e.mgrno = :mgrno"),
    @NamedQuery(name = "Employees.findByProjno", query = "SELECT e FROM Employees e WHERE e.employeesPK.projno = :projno"),
    @NamedQuery(name = "Employees.findByName", query = "SELECT e FROM Employees e WHERE e.employeesPK.name = :name"),
    @NamedQuery(name = "Employees.findByLastName", query = "SELECT e FROM Employees e WHERE e.employeesPK.lastName = :lastName")})
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeesPK employeesPK;
    @Size(max = 15)
    @Column(name = "empname")
    private String empname;
    @Column(name = "hiredate")
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salary")
    private BigDecimal salary;
    @Size(max = 10)
    @Column(name = "position")
    private String position;
    @Size(max = 4)
    @Column(name = "mgrno")
    private String mgrno;
    @JoinColumn(name = "depno", referencedColumnName = "depno")
    @ManyToOne(optional = false)
    private Dep depno;
    @JoinColumns({
        @JoinColumn(name = "NAME", referencedColumnName = "NAME", insertable = false, updatable = false),
        @JoinColumn(name = "LAST NAME", referencedColumnName = "LAST NAME", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Details details;
    @JoinColumns({
        @JoinColumn(name = "projno", referencedColumnName = "projno", insertable = false, updatable = false),
        @JoinColumn(name = "empnum", referencedColumnName = "empnum", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Projwork projwork;

    public Employees() {
    }

    public Employees(EmployeesPK employeesPK) {
        this.employeesPK = employeesPK;
    }

    public Employees(String empnum, String projno, String name, String lastName) {
        this.employeesPK = new EmployeesPK(empnum, projno, name, lastName);
    }

    public EmployeesPK getEmployeesPK() {
        return employeesPK;
    }

    public void setEmployeesPK(EmployeesPK employeesPK) {
        this.employeesPK = employeesPK;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMgrno() {
        return mgrno;
    }

    public void setMgrno(String mgrno) {
        this.mgrno = mgrno;
    }

    public Dep getDepno() {
        return depno;
    }

    public void setDepno(Dep depno) {
        this.depno = depno;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Projwork getProjwork() {
        return projwork;
    }

    public void setProjwork(Projwork projwork) {
        this.projwork = projwork;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeesPK != null ? employeesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.employeesPK == null && other.employeesPK != null) || (this.employeesPK != null && !this.employeesPK.equals(other.employeesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Employees[ employeesPK=" + employeesPK + " ]";
    }
    
}
