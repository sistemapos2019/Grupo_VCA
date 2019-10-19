/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deadbryam
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Ventas.findByInicial", query = "SELECT v FROM Ventas v WHERE v.inicial = :inicial")
    , @NamedQuery(name = "Ventas.findByFinal1", query = "SELECT v FROM Ventas v WHERE v.final1 = :final1")
    , @NamedQuery(name = "Ventas.findByVentas", query = "SELECT v FROM Ventas v WHERE v.ventas = :ventas")
    , @NamedQuery(name = "Ventas.findByPropinas", query = "SELECT v FROM Ventas v WHERE v.propinas = :propinas")
    , @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @Id
    private Date fecha;
    @Column(name = "inicial")
    private Integer inicial;
    @Column(name = "final")
    private Integer final1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ventas")
    private BigDecimal ventas;
    @Column(name = "propinas")
    private BigDecimal propinas;
    @Column(name = "total")
    private BigDecimal total;

    public Ventas() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getInicial() {
        return inicial;
    }

    public void setInicial(Integer inicial) {
        this.inicial = inicial;
    }

    public Integer getFinal1() {
        return final1;
    }

    public void setFinal1(Integer final1) {
        this.final1 = final1;
    }

    public BigDecimal getVentas() {
        return ventas;
    }

    public void setVentas(BigDecimal ventas) {
        this.ventas = ventas;
    }

    public BigDecimal getPropinas() {
        return propinas;
    }

    public void setPropinas(BigDecimal propinas) {
        this.propinas = propinas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
