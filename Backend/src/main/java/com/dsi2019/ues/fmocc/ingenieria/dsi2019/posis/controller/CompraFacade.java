/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {
    
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CompraFacade() {
        super(Compra.class);
    }
    
    public List<Compra> CompraById(Date date) {
        return executeQuery("SELECT c FROM Compra c WHERE c.fecha = :date")
                .setParameter("date", date)
                .getResultList();
    }
    
    public Object[] totales(Date date) {
        return (Object[]) executeQuery("SELECT SUM(c.montoInterno),SUM(c.iva),SUM(c.total) FROM Compra c WHERE c.fecha = :date")
                .setParameter("date", date)
                .getSingleResult();
    }
    
    public Object nombre(){
        return executeQuery("SELECT p.valor FROM Parametro p WHERE p.id = 2")
                .getSingleResult();
    }
    
}
