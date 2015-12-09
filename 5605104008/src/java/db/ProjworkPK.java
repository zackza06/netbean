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
public class ProjworkPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "projno")
    private String projno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "empnum")
    private String empnum;

    public ProjworkPK() {
    }

    public ProjworkPK(String projno, String empnum) {
        this.projno = projno;
        this.empnum = empnum;
    }

    public String getProjno() {
        return projno;
    }

    public void setProjno(String projno) {
        this.projno = projno;
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projno != null ? projno.hashCode() : 0);
        hash += (empnum != null ? empnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjworkPK)) {
            return false;
        }
        ProjworkPK other = (ProjworkPK) object;
        if ((this.projno == null && other.projno != null) || (this.projno != null && !this.projno.equals(other.projno))) {
            return false;
        }
        if ((this.empnum == null && other.empnum != null) || (this.empnum != null && !this.empnum.equals(other.empnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ProjworkPK[ projno=" + projno + ", empnum=" + empnum + " ]";
    }
    
}
