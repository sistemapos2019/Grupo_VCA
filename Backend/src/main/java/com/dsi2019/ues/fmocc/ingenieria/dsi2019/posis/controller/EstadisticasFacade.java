/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.custom.EstadisticaSemanal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author deadbryam
 */
@Stateless
public class EstadisticasFacade {

    @PersistenceContext(unitName = "com.dsi2019.ues.fmocc.ingenieria.dsi2019_POSis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public String ordenesVendidas(Date fecha) {
        System.out.println("Orden realizadas");
        return executeQuery("SELECT COUNT(o) FROM Orden o WHERE o.fecha = :date")
                .setParameter("date", fecha)
                .getSingleResult().toString();

    }

    public String platilloVendidos(Date fecha) {
        System.out.println("Platillo mas vendido");
        Query q = executeQuery("SELECT d.producto.nombre FROM Orden o INNER JOIN o.detalleordenList d WHERE o.fecha = :date "
                + "AND d.producto.idCategoria.id != (SELECT c.id FROM Categoria c WHERE c.nombre LIKE 'bebidas') "
                + "GROUP BY d.detalleordenPK.idProducto ORDER BY SUM(d.cantidad) DESC")
                .setParameter("date", fecha)
                .setMaxResults(1);

        return (q.getResultList().isEmpty()) ? "Ninguno" : q.getSingleResult().toString();
    }

    public String platosVendidos(Date fecha) {
        System.out.println("Platos vendidos");
        return executeQuery("SELECT CASE WHEN (SUM(d.cantidad) IS NULL) THEN 0 ELSE SUM(d.cantidad) END FROM Orden o INNER JOIN o.detalleordenList d "
                + "WHERE o.fecha = :date AND d.producto.idCategoria.id != (SELECT c.id FROM Categoria c WHERE c.nombre LIKE 'bebidas')")
                .setParameter("date", fecha)
                .getSingleResult().toString();
    }

    public List<Object[]> platillosSemanales(int week, int year) {
        System.out.println("platillos semanales");
        return executeQuery("SELECT CASE WHEN (FUNCTION('DAYOFWEEK',o.fecha) = 1) THEN 7 ELSE FUNCTION('DAYOFWEEK',o.fecha) - 1 END,SUM(d.cantidad)"
                + "FROM Orden o INNER JOIN o.detalleordenList d WHERE d.producto.idCategoria.id != (SELECT c.id FROM Categoria c WHERE c.nombre LIKE 'bebidas')"
                + "AND FUNCTION('WEEK',o.fecha,1) = :week AND FUNCTION('YEAR',o.fecha) = :year GROUP BY o.fecha")
                .setParameter("week", week)
                .setParameter("year", year)
                .getResultList();
    }

    public Object countByDate(Date date) {
        System.out.println("Count by date");
        return executeQuery("SELECT COUNT(o) FROM Orden o WHERE o.fecha = :date")
                .setParameter("date", date)
                .getSingleResult();
    }

    private Query executeQuery(String query) {
        return em.createQuery(query);
    }
}
