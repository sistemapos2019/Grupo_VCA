/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arevalo
 */
@Embeddable
public class DetalleordenPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOrden")
    private int idOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProducto")
    private int idProducto;

    public DetalleordenPK() {
    }

    public DetalleordenPK(int idOrden, int idProducto) {
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrden;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleordenPK)) {
            return false;
        }
        DetalleordenPK other = (DetalleordenPK) object;
        if (this.idOrden != other.idOrden) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.DetalleordenPK[ idOrden=" + idOrden + ", idProducto=" + idProducto + " ]";
    }
    
}
