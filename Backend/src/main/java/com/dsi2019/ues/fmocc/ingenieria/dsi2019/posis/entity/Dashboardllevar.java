/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "dashboardllevar", catalog = "pos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dashboardllevar.findAll", query = "SELECT d FROM Dashboardllevar d"),
    @NamedQuery(name = "Dashboardllevar.findByIdOrden", query = "SELECT d FROM Dashboardllevar d WHERE d.idOrden = :idOrden"),
    @NamedQuery(name = "Dashboardllevar.findByMesero", query = "SELECT d FROM Dashboardllevar d WHERE d.mesero = :mesero"),
    @NamedQuery(name = "Dashboardllevar.findByCliente", query = "SELECT d FROM Dashboardllevar d WHERE d.cliente = :cliente"),
    @NamedQuery(name = "Dashboardllevar.findByTotal", query = "SELECT d FROM Dashboardllevar d WHERE d.total = :total"),
    @NamedQuery(name = "Dashboardllevar.findByEstado", query = "SELECT d FROM Dashboardllevar d WHERE d.estado = :estado"),
    @NamedQuery(name = "Dashboardllevar.findByTiempoPreparado", query = "SELECT d FROM Dashboardllevar d WHERE d.tiempoPreparado = :tiempoPreparado"),
    @NamedQuery(name = "Dashboardllevar.findByPreparado", query = "SELECT d FROM Dashboardllevar d WHERE d.preparado = :preparado")})
public class Dashboardllevar implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "IdOrden", nullable = false)
    private int idOrden;
    @Basic(optional = false)
    @Column(name = "Mesero", nullable = false, length = 145)
    private String mesero;
    @Basic(optional = false)
    @Column(name = "Cliente", nullable = false, length = 145)
    private String cliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total", precision = 8, scale = 2)
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "Estado", nullable = false, length = 2)
    private String estado;
    @Column(name = "TiempoPreparado", precision = 18, scale = 1)
    private Double tiempoPreparado;
    @Column(name = "Preparado", length = 8)
    private String preparado;

    public Dashboardllevar() {
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTiempoPreparado() {
        return tiempoPreparado;
    }

    public void setTiempoPreparado(Double tiempoPreparado) {
        this.tiempoPreparado = tiempoPreparado;
    }

    public String getPreparado() {
        return preparado;
    }

    public void setPreparado(String preparado) {
        this.preparado = preparado;
    }
    
}
