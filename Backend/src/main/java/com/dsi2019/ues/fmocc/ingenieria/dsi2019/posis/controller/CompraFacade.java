/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
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

    /**
     * Este metodo sirve para encontrar las compras de una un mes determinado
     * @param date es un arreglo tipo String que en [0] debe llevar el anio y en [1] el mes.
     * @return
     */
    public List<Compra> compraByDate(String[] date) {
        return executeQuery("SELECT c FROM Compra c WHERE SUBSTRING(c.fecha, 6, 2) = :month AND SUBSTRING(c.fecha, 1, 4) = :year")
                .setParameter("year", date[0])
                .setParameter("month", date[1])
                .getResultList();
    }

    public Object parametros(int id){
        return executeQuery("SELECT p.valor FROM Parametro p WHERE p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public boolean exist(Integer id){
        return executeQuery("SELECT COUNT(c) FROM Compra c WHERE c.id=:id")
                .setParameter("id", id).getSingleResult().toString().equals("1");
    }

}
