/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author adriang
 */
@Entity
@Table(name = "TBL_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUsers.findAll", query = "SELECT t FROM TblUsers t"),
    @NamedQuery(name = "TblUsers.findById", query = "SELECT t FROM TblUsers t WHERE t.id = :id"),
    @NamedQuery(name = "TblUsers.findByNombre", query = "SELECT t FROM TblUsers t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblUsers.findByPassword", query = "SELECT t FROM TblUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TblUsers.findByNombrecompleto", query = "SELECT t FROM TblUsers t WHERE t.nombrecompleto = :nombrecompleto")})
public class TblUsers implements Serializable {

    @OneToMany(mappedBy = "idUser")
    private List<TblTimbradosNotasDeCredito> tblTimbradosNotasDeCreditoList;

    @OneToMany(mappedBy = "idUser")
    private List<TblEventoDetalle> tblEventoDetalleList;

    @OneToMany(mappedBy = "idUser")
    private List<TblRecibos> tblRecibosList;
    @OneToMany(mappedBy = "idUser")
    private List<TblEventos> tblEventosList;
    @OneToMany(mappedBy = "idUser")
    private List<TblTransferencias> tblTransferenciasList;

    @OneToMany(mappedBy = "idUser")
    private List<TblTimbrados> tblTimbradosList;
    @OneToMany(mappedBy = "idUser")
    private List<TblFacturas> tblFacturasList;
    @OneToMany(mappedBy = "idUser")
    private List<TblEntidades> tblEntidadesList;

    @OneToMany(mappedBy = "idUser")
    private List<TblTimbradosAutofacturas> tblTimbradosAutofacturasList;

    @OneToMany(mappedBy = "idUser")
    private List<TblFacturasCompra> tblFacturasCompraList;
    @OneToMany(mappedBy = "idUser")
    private List<TblAutofacturas> tblAutofacturasList;

    @OneToMany(mappedBy = "idUser")
    private List<TblAsientos> tblAsientosList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRECOMPLETO")
    private String nombrecompleto;
    @JoinTable(name = "TBL_GRUPOS_USERS", joinColumns = {
        @JoinColumn(name = "ID_USER", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID")})
    @ManyToMany
    private List<TblGrupos> tblGruposList;
    @JoinTable(name = "TBL_ROLES_USERS", joinColumns = {
        @JoinColumn(name = "ID_USER", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID")})
    @ManyToMany
    private List<TblRoles> tblRolesList;

    public TblUsers() {
    }

    public TblUsers(Integer id) {
        this.id = id;
    }

    public TblUsers(Integer id, String nombre, String password, String nombrecompleto) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.nombrecompleto = nombrecompleto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    @XmlTransient
    public List<TblGrupos> getTblGruposList() {
        return tblGruposList;
    }

    public void setTblGruposList(List<TblGrupos> tblGruposList) {
        this.tblGruposList = tblGruposList;
    }

    @XmlTransient
    public List<TblRoles> getTblRolesList() {
        return tblRolesList;
    }

    public void setTblRolesList(List<TblRoles> tblRolesList) {
        this.tblRolesList = tblRolesList;
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
        if (!(object instanceof TblUsers)) {
            return false;
        }
        TblUsers other = (TblUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.TblUsers[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    @XmlTransient
    public List<TblFacturasCompra> getTblFacturasCompraList() {
        return tblFacturasCompraList;
    }

    public void setTblFacturasCompraList(List<TblFacturasCompra> tblFacturasCompraList) {
        this.tblFacturasCompraList = tblFacturasCompraList;
    }

    @XmlTransient
    public List<TblAutofacturas> getTblAutofacturasList() {
        return tblAutofacturasList;
    }

    public void setTblAutofacturasList(List<TblAutofacturas> tblAutofacturasList) {
        this.tblAutofacturasList = tblAutofacturasList;
    }

    @XmlTransient
    public List<TblTimbradosAutofacturas> getTblTimbradosAutofacturasList() {
        return tblTimbradosAutofacturasList;
    }

    public void setTblTimbradosAutofacturasList(List<TblTimbradosAutofacturas> tblTimbradosAutofacturasList) {
        this.tblTimbradosAutofacturasList = tblTimbradosAutofacturasList;
    }

    @XmlTransient
    public List<TblTimbrados> getTblTimbradosList() {
        return tblTimbradosList;
    }

    public void setTblTimbradosList(List<TblTimbrados> tblTimbradosList) {
        this.tblTimbradosList = tblTimbradosList;
    }

    @XmlTransient
    public List<TblFacturas> getTblFacturasList() {
        return tblFacturasList;
    }

    public void setTblFacturasList(List<TblFacturas> tblFacturasList) {
        this.tblFacturasList = tblFacturasList;
    }

    @XmlTransient
    public List<TblEntidades> getTblEntidadesList() {
        return tblEntidadesList;
    }

    public void setTblEntidadesList(List<TblEntidades> tblEntidadesList) {
        this.tblEntidadesList = tblEntidadesList;
    }

    @XmlTransient
    public List<TblRecibos> getTblRecibosList() {
        return tblRecibosList;
    }

    public void setTblRecibosList(List<TblRecibos> tblRecibosList) {
        this.tblRecibosList = tblRecibosList;
    }

    @XmlTransient
    public List<TblEventos> getTblEventosList() {
        return tblEventosList;
    }

    public void setTblEventosList(List<TblEventos> tblEventosList) {
        this.tblEventosList = tblEventosList;
    }

    @XmlTransient
    public List<TblTransferencias> getTblTransferenciasList() {
        return tblTransferenciasList;
    }

    public void setTblTransferenciasList(List<TblTransferencias> tblTransferenciasList) {
        this.tblTransferenciasList = tblTransferenciasList;
    }

    @XmlTransient
    public List<TblEventoDetalle> getTblEventoDetalleList() {
        return tblEventoDetalleList;
    }

    public void setTblEventoDetalleList(List<TblEventoDetalle> tblEventoDetalleList) {
        this.tblEventoDetalleList = tblEventoDetalleList;
    }

    @XmlTransient
    public List<TblTimbradosNotasDeCredito> getTblTimbradosNotasDeCreditoList() {
        return tblTimbradosNotasDeCreditoList;
    }

    public void setTblTimbradosNotasDeCreditoList(List<TblTimbradosNotasDeCredito> tblTimbradosNotasDeCreditoList) {
        this.tblTimbradosNotasDeCreditoList = tblTimbradosNotasDeCreditoList;
    }

}
