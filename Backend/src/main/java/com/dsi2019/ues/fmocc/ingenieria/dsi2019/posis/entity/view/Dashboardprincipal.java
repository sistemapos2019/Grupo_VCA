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
@Table(name = "dashboardprincipal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dashboardprincipal.findAll", query = "SELECT d FROM Dashboardprincipal d")
    , @NamedQuery(name = "Dashboardprincipal.findByIdOrden", query = "SELECT d FROM Dashboardprincipal d WHERE d.idOrden = :idOrden")
    , @NamedQuery(name = "Dashboardprincipal.findByMesa", query = "SELECT d FROM Dashboardprincipal d WHERE d.mesa = :mesa")
    , @NamedQuery(name = "Dashboardprincipal.findByMesero", query = "SELECT d FROM Dashboardprincipal d WHERE d.mesero = :mesero")
    , @NamedQuery(name = "Dashboardprincipal.findByCliente", query = "SELECT d FROM Dashboardprincipal d WHERE d.cliente = :cliente")
    , @NamedQuery(name = "Dashboardprincipal.findByTotal", query = "SELECT d FROM Dashboardprincipal d WHERE d.total = :total")
    , @NamedQuery(name = "Dashboardprincipal.findByEstado", query = "SELECT d FROM Dashboardprincipal d WHERE d.estado = :estado")
    , @NamedQuery(name = "Dashboardprincipal.findByLlevar", query = "SELECT d FROM Dashboardprincipal d WHERE d.llevar = :llevar")
    , @NamedQuery(name = "Dashboardprincipal.findByTiempoPreparado", query = "SELECT d FROM Dashboardprincipal d WHERE d.tiempoPreparado = :tiempoPreparado")
    , @NamedQuery(name = "Dashboardprincipal.findByPreparado", query = "SELECT d FROM Dashboardprincipal d WHERE d.preparado = :preparado")
    , @NamedQuery(name = "Dashboardprincipal.findByTiempoRapido", query = "SELECT d FROM Dashboardprincipal d WHERE d.tiempoRapido = :tiempoRapido")
    , @NamedQuery(name = "Dashboardprincipal.findByRapido", query = "SELECT d FROM Dashboardprincipal d WHERE d.rapido = :rapido")
    , @NamedQuery(name = "Dashboardprincipal.findByTipo", query = "SELECT d FROM Dashboardprincipal d WHERE d.tipo = :tipo")})
public class Dashboardprincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdOrden")
    private int idOrden;
    @Column(name = "Mesa")
    private Integer mesa;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "llevar")
    private int llevar;
    @Size(max = 17)
    @Column(name = "TiempoPreparado")
    private String tiempoPreparado;
    @Size(max = 6)
    @Column(name = "Preparado")
    private String preparado;
    @Size(max = 17)
    @Column(name = "TiempoRapido")
    private String tiempoRapido;
    @Size(max = 6)
    @Column(name = "Rapido")
    private String rapido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "tipo")
    private String tipo;

    public Dashboardprincipal() {
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
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

    public int getLlevar() {
        return llevar;
    }

    public void setLlevar(int llevar) {
        this.llevar = llevar;
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

    public String getTiempoRapido() {
        return tiempoRapido;
    }

    public void setTiempoRapido(String tiempoRapido) {
        this.tiempoRapido = tiempoRapido;
    }

    public String getRapido() {
        return rapido;
    }

    public void setRapido(String rapido) {
        this.rapido = rapido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
