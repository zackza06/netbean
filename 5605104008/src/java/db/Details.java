/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Details.findAll", query = "SELECT d FROM Details d"),
    @NamedQuery(name = "Details.findByName", query = "SELECT d FROM Details d WHERE d.detailsPK.name = :name"),
    @NamedQuery(name = "Details.findByLastName", query = "SELECT d FROM Details d WHERE d.detailsPK.lastName = :lastName")})
public class Details implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailsPK detailsPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "details")
    private Collection<Employees> employeesCollection;

    public Details() {
    }

    public Details(DetailsPK detailsPK) {
        this.detailsPK = detailsPK;
    }

    public Details(String name, String lastName) {
        this.detailsPK = new DetailsPK(name, lastName);
    }

    public DetailsPK getDetailsPK() {
        return detailsPK;
    }

    public void setDetailsPK(DetailsPK detailsPK) {
        this.detailsPK = detailsPK;
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
        hash += (detailsPK != null ? detailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Details)) {
            return false;
        }
        Details other = (Details) object;
        if ((this.detailsPK == null && other.detailsPK != null) || (this.detailsPK != null && !this.detailsPK.equals(other.detailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Details[ detailsPK=" + detailsPK + " ]";
    }
    
}
