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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findById", query = "SELECT o FROM Orden o WHERE o.id = :id"),
    @NamedQuery(name = "Orden.findByFecha", query = "SELECT o FROM Orden o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "Orden.findByLlevar", query = "SELECT o FROM Orden o WHERE o.llevar = :llevar"),
    @NamedQuery(name = "Orden.findByEstado", query = "SELECT o FROM Orden o WHERE o.estado = :estado"),
    @NamedQuery(name = "Orden.findByObservacion", query = "SELECT o FROM Orden o WHERE o.observacion = :observacion"),
    @NamedQuery(name = "Orden.findByTiempoPreparado", query = "SELECT o FROM Orden o WHERE o.tiempoPreparado = :tiempoPreparado"),
    @NamedQuery(name = "Orden.findByTiempoRapido", query = "SELECT o FROM Orden o WHERE o.tiempoRapido = :tiempoRapido"),
    @NamedQuery(name = "Orden.findByTotal", query = "SELECT o FROM Orden o WHERE o.total = :total"),
    @NamedQuery(name = "Orden.findByPropina", query = "SELECT o FROM Orden o WHERE o.propina = :propina"),
    @NamedQuery(name = "Orden.findByFormaPago", query = "SELECT o FROM Orden o WHERE o.formaPago = :formaPago"),
    @NamedQuery(name = "Orden.findByCliente", query = "SELECT o FROM Orden o WHERE o.cliente = :cliente")})
public class Orden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "llevar")
    private int llevar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estado")
    private String estado;
    @Size(max = 245)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "tiempoPreparado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoPreparado;
    @Column(name = "tiempoRapido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoRapido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "propina")
    private BigDecimal propina;
    @Size(max = 1)
    @Column(name = "formaPago")
    private String formaPago;
    @Size(max = 145)
    @Column(name = "cliente")
    private String cliente;
    @JoinColumn(name = "idMesa", referencedColumnName = "id")
    @ManyToOne
    private Mesa idMesa;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<Detalleorden> detalleordenList;

    public Orden() {
    }

    public Orden(Integer id) {
        this.id = id;
    }

    public Orden(Integer id, int llevar, String estado) {
        this.id = id;
        this.llevar = llevar;
        this.estado = estado;
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

    public int getLlevar() {
        return llevar;
    }

    public void setLlevar(int llevar) {
        this.llevar = llevar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getTiempoPreparado() {
        return tiempoPreparado;
    }

    public void setTiempoPreparado(Date tiempoPreparado) {
        this.tiempoPreparado = tiempoPreparado;
    }

    public Date getTiempoRapido() {
        return tiempoRapido;
    }

    public void setTiempoRapido(Date tiempoRapido) {
        this.tiempoRapido = tiempoRapido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPropina() {
        return propina;
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Mesa getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Mesa idMesa) {
        this.idMesa = idMesa;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @JsonbTransient
    public List<Detalleorden> getDetalleordenList() {
        return detalleordenList;
    }

    public void setDetalleordenList(List<Detalleorden> detalleordenList) {
        this.detalleordenList = detalleordenList;
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
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.Orden[ id=" + id + " ]";
    }
    
}
