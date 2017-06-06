/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain.miembros;

import com.gnadenheimer.mg3.domain.TblEventoDetalle;
import com.gnadenheimer.mg3.domain.TblFacturas;
import com.gnadenheimer.mg3.domain.TblFormasDePago;
import com.gnadenheimer.mg3.domain.TblRecibos;
import com.gnadenheimer.mg3.domain.TblTransferencias;
import com.gnadenheimer.mg3.domain.TblUsers;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Period;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_ENTIDADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEntidades.findAll", query = "SELECT t FROM TblEntidades t")
    ,
    @NamedQuery(name = "TblEntidades.findById", query = "SELECT t FROM TblEntidades t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TblEntidades.findByNombres", query = "SELECT t FROM TblEntidades t WHERE t.nombres = :nombres")
    ,
    @NamedQuery(name = "TblEntidades.findByApellidos", query = "SELECT t FROM TblEntidades t WHERE t.apellidos = :apellidos")
    ,
    @NamedQuery(name = "TblEntidades.findByRazonSocial", query = "SELECT t FROM TblEntidades t WHERE t.razonSocial = :razonSocial")
    ,
    @NamedQuery(name = "TblEntidades.findByRucSinDv", query = "SELECT t FROM TblEntidades t WHERE t.rucSinDv = :rucSinDv")
    ,
    @NamedQuery(name = "TblEntidades.findByCtacte", query = "SELECT t FROM TblEntidades t WHERE t.ctacte = :ctacte")
    ,
    @NamedQuery(name = "TblEntidades.findByDomicilio", query = "SELECT t FROM TblEntidades t WHERE t.domicilio = :domicilio")
    ,
    @NamedQuery(name = "TblEntidades.findByBox", query = "SELECT t FROM TblEntidades t WHERE t.box = :box")
    ,
    @NamedQuery(name = "TblEntidades.findByIsMiembroActivo", query = "SELECT t FROM TblEntidades t WHERE t.isMiembroActivo = :isMiembroActivo")
    ,
    @NamedQuery(name = "TblEntidades.findByAporteMensual", query = "SELECT t FROM TblEntidades t WHERE t.aporteMensual = :aporteMensual")
    ,
    @NamedQuery(name = "TblEntidades.findByFechaNacimiento", query = "SELECT t FROM TblEntidades t WHERE t.fechaNacimiento = :fechaNacimiento")
    ,
    @NamedQuery(name = "TblEntidades.findByFechaBautismo", query = "SELECT t FROM TblEntidades t WHERE t.fechaBautismo = :fechaBautismo")
    ,
    @NamedQuery(name = "TblEntidades.findByFechaEntradaCongregacion", query = "SELECT t FROM TblEntidades t WHERE t.fechaEntradaCongregacion = :fechaEntradaCongregacion")
    ,
    @NamedQuery(name = "TblEntidades.findByFechaSalidaCongregacion", query = "SELECT t FROM TblEntidades t WHERE t.fechaSalidaCongregacion = :fechaSalidaCongregacion")
    ,
    @NamedQuery(name = "TblEntidades.findByFechaDefuncion", query = "SELECT t FROM TblEntidades t WHERE t.fechaDefuncion = :fechaDefuncion")})
public class TblEntidades implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nombres;

    private String apellidos = "";

    private String razonSocial = "";

    private String rucSinDv = "44444401";

    private Integer ctacte = 99999;

    private String domicilio;

    private Integer box;

    private Boolean isMiembroActivo;

    private Integer aporteMensual;

    private Long aporteSaldoAnterior;

    private LocalDateTime fechaNacimiento;

    private LocalDateTime fechaBautismo;

    private LocalDateTime fechaEntradaCongregacion;

    private LocalDateTime fechaSalidaCongregacion;

    private LocalDateTime fechaDefuncion;

    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList;

    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList1;

    private List<TblEventoDetalle> tblEventoDetalleList;

    private List<TblRecibos> tblRecibosList;

    private List<TblTransferencias> tblTransferenciasList;

    private List<TblFacturas> tblFacturasList;

    private TblAreasServicioEnIglesia idAreaServicioEnIglesia;

    private List<TblEntidades> tblEntidadesList;

    private TblEntidades idEntidadPaganteAportes;

    private TblFormasDePago idFormaDePagoPreferida;

    private TblMiembrosAlergias idMiembrosAlergia;

    private TblMiembrosCategoriasDePago idMiembrosCategoriaDePago;

    private Integer mesInicioAporte;

    private Integer mesFinAporte;

    private TblUsers idUser;

    public TblEntidades() {
    }

    public TblEntidades(Integer id) {
        this.id = id;
    }

    public TblEntidades(Integer id, String nombres, String apellidos, String razonSocial, String rucSinDv, Integer ctacte, Boolean isMiembroActivo, Integer aporteMensual) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.razonSocial = razonSocial;
        this.rucSinDv = rucSinDv;
        this.ctacte = ctacte;
        this.isMiembroActivo = isMiembroActivo;
        this.aporteMensual = aporteMensual;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NOMBRES")
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "APELLIDOS")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "RAZON_SOCIAL")
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Size(max = 20)
    @Column(name = "RUC_SIN_DV")
    public String getRucSinDv() {
        return rucSinDv;
    }

    public void setRucSinDv(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "CTACTE")
    public Integer getCtacte() {
        return ctacte;
    }

    public void setCtacte(Integer ctacte) {
        this.ctacte = ctacte;
    }

    @Size(max = 50)
    @Column(name = "DOMICILIO")
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Column(name = "BOX")
    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_MIEMBRO_ACTIVO")
    public Boolean getIsMiembroActivo() {
        return isMiembroActivo;
    }

    public void setIsMiembroActivo(Boolean isMiembroActivo) {
        this.isMiembroActivo = isMiembroActivo;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "APORTE_MENSUAL")
    public Integer getAporteMensual() {
        return aporteMensual;
    }

    public void setAporteMensual(Integer aporteMensual) {
        this.aporteMensual = aporteMensual;
    }

    @Column(name = "FECHA_NACIMIENTO")
    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "FECHA_BAUTISMO")
    public LocalDateTime getFechaBautismo() {
        return fechaBautismo;
    }

    public void setFechaBautismo(LocalDateTime fechaBautismo) {
        this.fechaBautismo = fechaBautismo;
    }

    @Column(name = "FECHA_ENTRADA_CONGREGACION")
    public LocalDateTime getFechaEntradaCongregacion() {
        return fechaEntradaCongregacion;
    }

    public void setFechaEntradaCongregacion(LocalDateTime fechaEntradaCongregacion) {
        this.fechaEntradaCongregacion = fechaEntradaCongregacion;
    }

    @Column(name = "FECHA_SALIDA_CONGREGACION")
    public LocalDateTime getFechaSalidaCongregacion() {
        return fechaSalidaCongregacion;
    }

    public void setFechaSalidaCongregacion(LocalDateTime fechaSalidaCongregacion) {
        this.fechaSalidaCongregacion = fechaSalidaCongregacion;
    }

    @Column(name = "FECHA_DEFUNCION")
    public LocalDateTime getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(LocalDateTime fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    @XmlTransient
    @OneToMany(mappedBy = "idEntidad2")
    public List<TblMiembrosRelaciones> getTblMiembrosRelacionesList() {
        return tblMiembrosRelacionesList;
    }

    public void setTblMiembrosRelacionesList(List<TblMiembrosRelaciones> tblMiembrosRelacionesList) {
        this.tblMiembrosRelacionesList = tblMiembrosRelacionesList;
    }

    @XmlTransient
    @OneToMany(mappedBy = "idEntidad1")
    public List<TblMiembrosRelaciones> getTblMiembrosRelacionesList1() {
        return tblMiembrosRelacionesList1;
    }

    public void setTblMiembrosRelacionesList1(List<TblMiembrosRelaciones> tblMiembrosRelacionesList1) {
        this.tblMiembrosRelacionesList1 = tblMiembrosRelacionesList1;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad")
    public List<TblEventoDetalle> getTblEventoDetalleList() {
        return tblEventoDetalleList;
    }

    public void setTblEventoDetalleList(List<TblEventoDetalle> tblEventoDetalleList) {
        this.tblEventoDetalleList = tblEventoDetalleList;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad")
    public List<TblRecibos> getTblRecibosList() {
        return tblRecibosList;
    }

    public void setTblRecibosList(List<TblRecibos> tblRecibosList) {
        this.tblRecibosList = tblRecibosList;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad")
    public List<TblTransferencias> getTblTransferenciasList() {
        return tblTransferenciasList;
    }

    public void setTblTransferenciasList(List<TblTransferencias> tblTransferenciasList) {
        this.tblTransferenciasList = tblTransferenciasList;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad")
    public List<TblFacturas> getTblFacturasList() {
        return tblFacturasList;
    }

    public void setTblFacturasList(List<TblFacturas> tblFacturasList) {
        this.tblFacturasList = tblFacturasList;
    }

    @JoinColumn(name = "ID_AREA_SERVICIO_EN_IGLESIA", referencedColumnName = "ID")
    @ManyToOne
    public TblAreasServicioEnIglesia getIdAreaServicioEnIglesia() {
        return idAreaServicioEnIglesia;
    }

    public void setIdAreaServicioEnIglesia(TblAreasServicioEnIglesia idAreaServicioEnIglesia) {
        this.idAreaServicioEnIglesia = idAreaServicioEnIglesia;
    }

    @XmlTransient
    @OneToMany(mappedBy = "idEntidadPaganteAportes")
    public List<TblEntidades> getTblEntidadesList() {
        return tblEntidadesList;
    }

    public void setTblEntidadesList(List<TblEntidades> tblEntidadesList) {
        this.tblEntidadesList = tblEntidadesList;
    }

    @JoinColumn(name = "ID_ENTIDAD_PAGANTE_APORTES", referencedColumnName = "ID")
    @ManyToOne
    public TblEntidades getIdEntidadPaganteAportes() {
        return idEntidadPaganteAportes;
    }

    public void setIdEntidadPaganteAportes(TblEntidades idEntidadPaganteAportes) {
        this.idEntidadPaganteAportes = idEntidadPaganteAportes;
    }

    @JoinColumn(name = "ID_FORMA_DE_PAGO_PREFERIDA", referencedColumnName = "ID")
    @ManyToOne
    public TblFormasDePago getIdFormaDePagoPreferida() {
        return idFormaDePagoPreferida;
    }

    public void setIdFormaDePagoPreferida(TblFormasDePago idFormaDePagoPreferida) {
        this.idFormaDePagoPreferida = idFormaDePagoPreferida;
    }

    @JoinColumn(name = "ID_MIEMBROS_ALERGIA", referencedColumnName = "ID")
    @ManyToOne
    public TblMiembrosAlergias getIdMiembrosAlergia() {
        return idMiembrosAlergia;
    }

    public void setIdMiembrosAlergia(TblMiembrosAlergias idMiembrosAlergia) {
        this.idMiembrosAlergia = idMiembrosAlergia;
    }

    @JoinColumn(name = "ID_MIEMBROS_CATEGORIA_DE_PAGO", referencedColumnName = "ID")
    @ManyToOne
    public TblMiembrosCategoriasDePago getIdMiembrosCategoriaDePago() {
        return idMiembrosCategoriaDePago;
    }

    public void setIdMiembrosCategoriaDePago(TblMiembrosCategoriasDePago idMiembrosCategoriaDePago) {
        this.idMiembrosCategoriaDePago = idMiembrosCategoriaDePago;
    }

    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    /*
    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEntidades)) {
            return false;
        }
        TblEntidades other = (TblEntidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
     */
    @Transient
    public String getNombreCompleto() {
        return apellidos + " " + nombres;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    /**
     * @return the aporteSaldoAnterior
     */
    @Column(name = "APORTE_SALDO_ANTERIOR")
    public Long getAporteSaldoAnterior() {
        return aporteSaldoAnterior;
    }

    /**
     * @param aporteSaldoAnterior the aporteSaldoAnterior to set
     */
    public void setAporteSaldoAnterior(Long aporteSaldoAnterior) {
        this.aporteSaldoAnterior = aporteSaldoAnterior;
    }

    /**
     * @return the mesInicioAporte
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "MES_INICIO_APORTE")
    public Integer getMesInicioAporte() {
        return mesInicioAporte;
    }

    /**
     * @param mesInicioAporte the mesInicioAporte to set
     */
    public void setMesInicioAporte(Integer mesInicioAporte) {
        this.mesInicioAporte = mesInicioAporte;
    }

    /**
     * @return the mesFinAporte
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "MES_FIN_APORTE")
    public Integer getMesFinAporte() {
        return mesFinAporte;
    }

    /**
     * @param mesFinAporte the mesFinAporte to set
     */
    public void setMesFinAporte(Integer mesFinAporte) {
        this.mesFinAporte = mesFinAporte;
    }

    @Transient
    public Integer getEdad() {
        if (getFechaNacimiento() != null) {
            return Period.between(getFechaNacimiento().toLocalDate(), LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    @Transient
    public String getEdadText() {
        if (getFechaNacimiento() != null) {
            return "Edad: " + String.valueOf(Period.between(getFechaNacimiento().toLocalDate(), LocalDate.now()).getYears()) + " años. ";
        } else {
            return "Edad: No disponible.";
        }
    }

    private String edadBautismoText;

    @Transient
    public String getEdadBautismoText() {
        if (getFechaBautismo() != null) {
            return "Bautizado hace: " + String.valueOf(Period.between(getFechaBautismo().toLocalDate(), LocalDate.now()).getYears()) + " años. ";
        } else {
            return "Bautizado hace: No disponible.";
        }
    }
}
