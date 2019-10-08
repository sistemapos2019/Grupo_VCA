/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arevalo
 */
@Entity
@Table(name = "detalleorden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleorden.findAll", query = "SELECT d FROM Detalleorden d"),
    @NamedQuery(name = "Detalleorden.findByIdOrden", query = "SELECT d FROM Detalleorden d WHERE d.detalleordenPK.idOrden = :idOrden"),
    @NamedQuery(name = "Detalleorden.findByIdProducto", query = "SELECT d FROM Detalleorden d WHERE d.detalleordenPK.idProducto = :idProducto"),
    @NamedQuery(name = "Detalleorden.findByCantidad", query = "SELECT d FROM Detalleorden d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleorden.findByPrecioUnitario", query = "SELECT d FROM Detalleorden d WHERE d.precioUnitario = :precioUnitario")})
public class Detalleorden implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleordenPK detalleordenPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioUnitario")
    private BigDecimal precioUnitario;
    @JoinColumn(name = "idOrden", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;
    @JoinColumn(name = "idProducto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public Detalleorden() {
    }

    public Detalleorden(DetalleordenPK detalleordenPK) {
        this.detalleordenPK = detalleordenPK;
    }

    public Detalleorden(DetalleordenPK detalleordenPK, BigDecimal cantidad, BigDecimal precioUnitario) {
        this.detalleordenPK = detalleordenPK;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Detalleorden(int idOrden, int idProducto) {
        this.detalleordenPK = new DetalleordenPK(idOrden, idProducto);
    }

    public DetalleordenPK getDetalleordenPK() {
        return detalleordenPK;
    }

    public void setDetalleordenPK(DetalleordenPK detalleordenPK) {
        this.detalleordenPK = detalleordenPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    @XmlTransient
    @JsonbTransient
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleordenPK != null ? detalleordenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleorden)) {
            return false;
        }
        Detalleorden other = (Detalleorden) object;
        if ((this.detalleordenPK == null && other.detalleordenPK != null) || (this.detalleordenPK != null && !this.detalleordenPK.equals(other.detalleordenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.Detalleorden[ detalleordenPK=" + detalleordenPK + " ]";
    }
    
}
