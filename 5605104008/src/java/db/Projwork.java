/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "projwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projwork.findAll", query = "SELECT p FROM Projwork p"),
    @NamedQuery(name = "Projwork.findByProjno", query = "SELECT p FROM Projwork p WHERE p.projworkPK.projno = :projno"),
    @NamedQuery(name = "Projwork.findByEmpnum", query = "SELECT p FROM Projwork p WHERE p.projworkPK.empnum = :empnum"),
    @NamedQuery(name = "Projwork.findByHours", query = "SELECT p FROM Projwork p WHERE p.hours = :hours")})
public class Projwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjworkPK projworkPK;
    @Column(name = "hours")
    private Short hours;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projwork")
    private Collection<Employees> employeesCollection;
    @JoinColumn(name = "projno", referencedColumnName = "projno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Project project;

    public Projwork() {
    }

    public Projwork(ProjworkPK projworkPK) {
        this.projworkPK = projworkPK;
    }

    public Projwork(String projno, String empnum) {
        this.projworkPK = new ProjworkPK(projno, empnum);
    }

    public ProjworkPK getProjworkPK() {
        return projworkPK;
    }

    public void setProjworkPK(ProjworkPK projworkPK) {
        this.projworkPK = projworkPK;
    }

    public Short getHours() {
        return hours;
    }

    public void setHours(Short hours) {
        this.hours = hours;
    }

    @XmlTransient
    public Collection<Employees> getEmployeesCollection() {
        return employeesCollection;
    }

    public void setEmployeesCollection(Collection<Employees> employeesCollection) {
        this.employeesCollection = employeesCollection;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projworkPK != null ? projworkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projwork)) {
            return false;
        }
        Projwork other = (Projwork) object;
        if ((this.projworkPK == null && other.projworkPK != null) || (this.projworkPK != null && !this.projworkPK.equals(other.projworkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Projwork[ projworkPK=" + projworkPK + " ]";
    }
    
}
