/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjno", query = "SELECT p FROM Project p WHERE p.projno = :projno"),
    @NamedQuery(name = "Project.findByProdesc", query = "SELECT p FROM Project p WHERE p.prodesc = :prodesc"),
    @NamedQuery(name = "Project.findByStartdate", query = "SELECT p FROM Project p WHERE p.startdate = :startdate"),
    @NamedQuery(name = "Project.findByEnddate", query = "SELECT p FROM Project p WHERE p.enddate = :enddate"),
    @NamedQuery(name = "Project.findByBudget", query = "SELECT p FROM Project p WHERE p.budget = :budget")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "projno")
    private String projno;
    @Size(max = 20)
    @Column(name = "prodesc")
    private String prodesc;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "budget")
    private BigDecimal budget;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Collection<Projwork> projworkCollection;

    public Project() {
    }

    public Project(String projno) {
        this.projno = projno;
    }

    public String getProjno() {
        return projno;
    }

    public void setProjno(String projno) {
        this.projno = projno;
    }

    public String getProdesc() {
        return prodesc;
    }

    public void setProdesc(String prodesc) {
        this.prodesc = prodesc;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @XmlTransient
    public Collection<Projwork> getProjworkCollection() {
        return projworkCollection;
    }

    public void setProjworkCollection(Collection<Projwork> projworkCollection) {
        this.projworkCollection = projworkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projno != null ? projno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projno == null && other.projno != null) || (this.projno != null && !this.projno.equals(other.projno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Project[ projno=" + projno + " ]";
    }
    
}
