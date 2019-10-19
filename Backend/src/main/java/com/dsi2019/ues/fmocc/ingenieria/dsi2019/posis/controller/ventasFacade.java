/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.view.Ventas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deadbryam
 */
@Stateless
public class ventasFacade extends AbstractFacade<Ventas>{

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ventasFacade() {
        super(Ventas.class);
    }
    
    public List<Ventas> ventasByDate(String date[]){
        return executeQuery("SELECT v FROM Ventas v WHERE SUBSTRING(v.fecha,1,4) = :year AND SUBSTRING(v.fecha,6,2) = :month")
                .setParameter("year", date[0])
                .setParameter("month", date[1])
                .getResultList();
    }
    
}
