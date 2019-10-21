/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detallecompra;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class DetallecompraFacade extends AbstractFacade<Detallecompra> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallecompraFacade() {
        super(Detallecompra.class);
    }
    
    public List detalle(int id){
        return executeQuery("SELECT c.producto.nombre, c.cantidad, c.precioUnitario FROM Detallecompra c WHERE c.detallecompraPK.idCompra=:id")
                .setParameter("id", id).getResultList();
    }
}
