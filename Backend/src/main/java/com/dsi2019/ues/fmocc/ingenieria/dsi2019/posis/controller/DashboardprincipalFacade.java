/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.view.Dashboardprincipal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author kevin
 */
@Stateless
public class DashboardprincipalFacade extends AbstractFacade<Dashboardprincipal> {

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DashboardprincipalFacade() {
        super(Dashboardprincipal.class);
    }
    
     public List<Dashboardprincipal> getDashboard(){
        return em.createNamedQuery("Dashboardprincipal.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();
    }
    
}
