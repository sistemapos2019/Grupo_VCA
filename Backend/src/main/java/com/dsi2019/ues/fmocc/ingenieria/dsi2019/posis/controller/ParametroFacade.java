/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Parametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class ParametroFacade extends AbstractFacade<Parametro> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroFacade() {
        super(Parametro.class);
    }
    public boolean exist(Integer id){
        return executeQuery("SELECT COUNT(c) FROM Parametros p WHERE p.id=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }
    
    public boolean Preparado(Integer id){
        return executeQuery("SELECT DISTINCT p.nombre ,d.cantidad FROM Orden o , Producto p , DetalleOrden d WHERE d.detalleOrdenPK.producto=p.id AND p.preparado=1 AND d.detalleOrdenPK.id=:id")
                .setParameter("id", id).toString().equals("SI");
    }
    
}
