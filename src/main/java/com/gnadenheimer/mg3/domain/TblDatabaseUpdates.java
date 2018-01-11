/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_DATABASE_UPDATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDatabaseUpdates.findAll", query = "SELECT t FROM TblDatabaseUpdates t"),
    @NamedQuery(name = "TblDatabaseUpdates.findById", query = "SELECT t FROM TblDatabaseUpdates t WHERE t.id = :id")})
public class TblDatabaseUpdates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    public TblDatabaseUpdates() {
    }

    public TblDatabaseUpdates(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDatabaseUpdates)) {
            return false;
        }
        TblDatabaseUpdates other = (TblDatabaseUpdates) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblDatabaseUpdates[ id=" + id + " ]";
    }

}
