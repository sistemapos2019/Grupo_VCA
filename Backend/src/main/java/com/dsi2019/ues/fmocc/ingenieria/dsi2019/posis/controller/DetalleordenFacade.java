/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detalleorden;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class DetalleordenFacade extends AbstractFacade<Detalleorden> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleordenFacade() {
        super(Detalleorden.class);
    }
    public boolean existe(Integer id) {
        return executeQuery("SELECT COUNT(d) FROM DetalleOrden d WHERE d")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }

    public double Total(Integer id) {
        double total = Double.parseDouble(executeQuery("SELECT SUM(d.precio) FROM DetalleOrden d WHERE d.detalleOrdenPK.id=:id").setParameter("id", id).getSingleResult().toString());
        return total;
    }

    public List<Detalleorden> entidad(Integer id) {
        return executeQuery("SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.id=:id")
                .setParameter("id", id).getResultList();
    }

    public void removeDetalle(int id) {
        executeQuery("DELETE FROM DetalleOrden a WHERE a.detalleOrdenPK.id= :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List estadisticas(){
        return executeQuery("SELECT m.producto1.nombre, SUM(m.cantidad), SUM(m.precio) FROM DetalleOrden m GROUP BY m.producto1.nombre")
                .getResultList();
    }
    
}
