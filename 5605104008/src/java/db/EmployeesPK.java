/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Embeddable
public class EmployeesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "empnum")
    private String empnum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "projno")
    private String projno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LAST NAME")
    private String lastName;

    public EmployeesPK() {
    }

    public EmployeesPK(String empnum, String projno, String name, String lastName) {
        this.empnum = empnum;
        this.projno = projno;
        this.name = name;
        this.lastName = lastName;
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum;
    }

    public String getProjno() {
        return projno;
    }

    public void setProjno(String projno) {
        this.projno = projno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empnum != null ? empnum.hashCode() : 0);
        hash += (projno != null ? projno.hashCode() : 0);
        hash += (name != null ? name.hashCode() : 0);
        hash += (lastName != null ? lastName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeesPK)) {
            return false;
        }
        EmployeesPK other = (EmployeesPK) object;
        if ((this.empnum == null && other.empnum != null) || (this.empnum != null && !this.empnum.equals(other.empnum))) {
            return false;
        }
        if ((this.projno == null && other.projno != null) || (this.projno != null && !this.projno.equals(other.projno))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.lastName == null && other.lastName != null) || (this.lastName != null && !this.lastName.equals(other.lastName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.EmployeesPK[ empnum=" + empnum + ", projno=" + projno + ", name=" + name + ", lastName=" + lastName + " ]";
    }
    
}
