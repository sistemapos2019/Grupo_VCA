/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author arevalo
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.BitacoraREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.CORSFilter.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.CategoriaREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.CompraREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.DetallecompraREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.DetalleordenREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.MesaREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.OrdenREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.ParametroREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.ProductoREST.class);
        resources.add(com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service.UsuarioREST.class);
    }
    
}
