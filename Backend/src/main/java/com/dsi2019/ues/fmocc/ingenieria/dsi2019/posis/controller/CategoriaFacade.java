/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    public boolean exist(Integer id){
        return executeQuery("SELECT COUNT(c) FROM Categoria c WHERE c.id=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }
    
}
