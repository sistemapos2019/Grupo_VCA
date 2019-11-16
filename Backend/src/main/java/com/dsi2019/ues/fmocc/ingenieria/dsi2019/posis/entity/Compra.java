/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author arevalo
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id"),
    @NamedQuery(name = "Compra.findByFecha", query = "SELECT c FROM Compra c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Compra.findByNdocumento", query = "SELECT c FROM Compra c WHERE c.ndocumento = :ndocumento"),
    @NamedQuery(name = "Compra.findByNrc", query = "SELECT c FROM Compra c WHERE c.nrc = :nrc"),
    @NamedQuery(name = "Compra.findByNitdui", query = "SELECT c FROM Compra c WHERE c.nitdui = :nitdui"),
    @NamedQuery(name = "Compra.findByNombreProveedor", query = "SELECT c FROM Compra c WHERE c.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Compra.findByMontoInterno", query = "SELECT c FROM Compra c WHERE c.montoInterno = :montoInterno"),
    @NamedQuery(name = "Compra.findByIva", query = "SELECT c FROM Compra c WHERE c.iva = :iva"),
    @NamedQuery(name = "Compra.findByPercepcion", query = "SELECT c FROM Compra c WHERE c.percepcion = :percepcion"),
    @NamedQuery(name = "Compra.findByTotal", query = "SELECT c FROM Compra c WHERE c.total = :total")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "Ndocumento")
    private String ndocumento;
    @Size(max = 45)
    @Column(name = "NRC")
    private String nrc;
    @Size(max = 45)
    @Column(name = "NITDUI")
    private String nitdui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "nombreProveedor")
    private String nombreProveedor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "montoInterno")
    private BigDecimal montoInterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "percepcion")
    private BigDecimal percepcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<Detallecompra> detallecompraList;

    public Compra() {
        setFecha(new Date());
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Compra(Integer id, Date fecha, String nombreProveedor, BigDecimal montoInterno, BigDecimal iva, BigDecimal percepcion, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.nombreProveedor = nombreProveedor;
        this.montoInterno = montoInterno;
        this.iva = iva;
        this.percepcion = percepcion;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNitdui() {
        return nitdui;
    }

    public void setNitdui(String nitdui) {
        this.nitdui = nitdui;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public BigDecimal getMontoInterno() {
        return montoInterno;
    }

    public void setMontoInterno(BigDecimal montoInterno) {
        this.montoInterno = montoInterno;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(BigDecimal percepcion) {
        this.percepcion = percepcion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @JsonbTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.Compra[ id=" + id + " ]";
    }
    
}
