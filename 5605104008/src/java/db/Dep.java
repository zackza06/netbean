/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "dep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dep.findAll", query = "SELECT d FROM Dep d"),
    @NamedQuery(name = "Dep.findByDepno", query = "SELECT d FROM Dep d WHERE d.depno = :depno"),
    @NamedQuery(name = "Dep.findByDepname", query = "SELECT d FROM Dep d WHERE d.depname = :depname"),
    @NamedQuery(name = "Dep.findByLocation", query = "SELECT d FROM Dep d WHERE d.location = :location")})
public class Dep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "depno")
    private String depno;
    @Size(max = 15)
    @Column(name = "depname")
    private String depname;
    @Size(max = 15)
    @Column(name = "location")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depno")
    private Collection<Employees> employeesCollection;

    public Dep() {
    }

    public Dep(String depno) {
        this.depno = depno;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depno != null ? depno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dep)) {
            return false;
        }
        Dep other = (Dep) object;
        if ((this.depno == null && other.depno != null) || (this.depno != null && !this.depno.equals(other.depno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Dep[ depno=" + depno + " ]";
    }
    
}
