/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Bitacora;
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
public class BitacoraFacade extends AbstractFacade<Bitacora> {
    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }
    
    public List rango (String FechaInicio,String FechaFin, boolean ver){
        if (ver) {
            return executeQuery("SELECT TRIM(b.fecha),b.suceso,b.idUsuario.nombreCompleto FROM Bitacora b")
                    .getResultList();
        }else{
            return executeQuery("SELECT TRIM(b.fecha),b.suceso,b.idUsuario.nombreCompleto FROM Bitacora b WHERE b.fecha BETWEEN \'"+FechaInicio+" 00:00:00\' AND \'"+FechaFin+" 00:00:00\'")
                    .getResultList();
        }
    }
            
    public List<String> latestRecords(int cantidad){
        return executeQuery("SELECT b.suceso FROM Bitacora b ORDER BY b.fecha DESC")
                .setMaxResults(cantidad)
                .getResultList();
    }
    
}
