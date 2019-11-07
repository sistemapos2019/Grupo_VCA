/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deadbryam
 */
@Entity
@Table(name = "dashboardllevar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dashboardllevar.findAll", query = "SELECT d FROM Dashboardllevar d")
    , @NamedQuery(name = "Dashboardllevar.findByIdOrden", query = "SELECT d FROM Dashboardllevar d WHERE d.idOrden = :idOrden")
    , @NamedQuery(name = "Dashboardllevar.findByMesero", query = "SELECT d FROM Dashboardllevar d WHERE d.mesero = :mesero")
    , @NamedQuery(name = "Dashboardllevar.findByCliente", query = "SELECT d FROM Dashboardllevar d WHERE d.cliente = :cliente")
    , @NamedQuery(name = "Dashboardllevar.findByTotal", query = "SELECT d FROM Dashboardllevar d WHERE d.total = :total")
    , @NamedQuery(name = "Dashboardllevar.findByEstado", query = "SELECT d FROM Dashboardllevar d WHERE d.estado = :estado")
    , @NamedQuery(name = "Dashboardllevar.findByTiempoPreparado", query = "SELECT d FROM Dashboardllevar d WHERE d.tiempoPreparado = :tiempoPreparado")
    , @NamedQuery(name = "Dashboardllevar.findByPreparado", query = "SELECT d FROM Dashboardllevar d WHERE d.preparado = :preparado")})
public class Dashboardllevar implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdOrden")
    private int idOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "Mesero")
    private String mesero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 145)
    @Column(name = "Cliente")
    private String cliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Estado")
    private String estado;
    @Size(max = 17)
    @Column(name = "TiempoPreparado")
    private String tiempoPreparado;
    @Size(max = 6)
    @Column(name = "Preparado")
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

    public String getTiempoPreparado() {
        return tiempoPreparado;
    }

    public void setTiempoPreparado(String tiempoPreparado) {
        this.tiempoPreparado = tiempoPreparado;
    }

    public String getPreparado() {
        return preparado;
    }

    public void setPreparado(String preparado) {
        this.preparado = preparado;
    }
    
}
