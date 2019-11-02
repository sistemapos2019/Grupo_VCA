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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "dashboardprincipal", catalog = "pos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dashboardprincipal.findAll", query = "SELECT d FROM Dashboardprincipal d"),
    @NamedQuery(name = "Dashboardprincipal.findByIdOrden", query = "SELECT d FROM Dashboardprincipal d WHERE d.idOrden = :idOrden"),
    @NamedQuery(name = "Dashboardprincipal.findByMesa", query = "SELECT d FROM Dashboardprincipal d WHERE d.mesa = :mesa"),
    @NamedQuery(name = "Dashboardprincipal.findByMesero", query = "SELECT d FROM Dashboardprincipal d WHERE d.mesero = :mesero"),
    @NamedQuery(name = "Dashboardprincipal.findByCliente", query = "SELECT d FROM Dashboardprincipal d WHERE d.cliente = :cliente"),
    @NamedQuery(name = "Dashboardprincipal.findByTotal", query = "SELECT d FROM Dashboardprincipal d WHERE d.total = :total"),
    @NamedQuery(name = "Dashboardprincipal.findByEstado", query = "SELECT d FROM Dashboardprincipal d WHERE d.estado = :estado"),
    @NamedQuery(name = "Dashboardprincipal.findByLlevar", query = "SELECT d FROM Dashboardprincipal d WHERE d.llevar = :llevar"),
    @NamedQuery(name = "Dashboardprincipal.findByTiempoPreparado", query = "SELECT d FROM Dashboardprincipal d WHERE d.tiempoPreparado = :tiempoPreparado"),
    @NamedQuery(name = "Dashboardprincipal.findByPreparado", query = "SELECT d FROM Dashboardprincipal d WHERE d.preparado = :preparado"),
    @NamedQuery(name = "Dashboardprincipal.findByTiempoRapido", query = "SELECT d FROM Dashboardprincipal d WHERE d.tiempoRapido = :tiempoRapido"),
    @NamedQuery(name = "Dashboardprincipal.findByRapido", query = "SELECT d FROM Dashboardprincipal d WHERE d.rapido = :rapido"),
    @NamedQuery(name = "Dashboardprincipal.findByTipo", query = "SELECT d FROM Dashboardprincipal d WHERE d.tipo = :tipo")})
public class Dashboardprincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdOrden", nullable = false)
    private int idOrden;
    @Column(name = "Mesa")
    private Integer mesa;
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
    @Basic(optional = false)
    @Column(name = "llevar", nullable = false)
    private int llevar;
    @Column(name = "TiempoPreparado", precision = 18, scale = 1)
    private Double tiempoPreparado;
    @Column(name = "Preparado", length = 8)
    private String preparado;
    @Column(name = "TiempoRapido", precision = 18, scale = 1)
    private Double tiempoRapido;
    @Column(name = "Rapido", length = 8)
    private String rapido;
    @Basic(optional = false)
    @Column(name = "tipo", nullable = false, length = 6)
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

    public Double getTiempoRapido() {
        return tiempoRapido;
    }

    public void setTiempoRapido(Double tiempoRapido) {
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