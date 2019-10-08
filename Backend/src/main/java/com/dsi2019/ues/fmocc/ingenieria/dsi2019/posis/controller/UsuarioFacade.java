/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arevalo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public boolean exist(Integer id){
        return executeQuery("SELECT COUNT(m) FROM Usuario m WHERE m.id=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }
    
    public String LoginUser(String id){
        if (executeQuery("SELECT COUNT(s) FROM Usuario s WHERE s.login=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1")) {
             return executeQuery("SELECT us.clave,us.pin FROM Usuario us WHERE us.login=:id")
                .setParameter("id", id).getSingleResult().toString();
        }
        return "0";
    }
    
}
