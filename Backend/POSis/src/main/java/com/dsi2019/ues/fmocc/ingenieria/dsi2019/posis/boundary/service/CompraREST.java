/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.CompraFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author arevalo
 */
@Path("compras")
public class CompraREST {
    @EJB
    CompraFacade compraFacade;
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Compra entity) {
        if (entity!=null) {
            compraFacade.create(entity);
        }
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Integer id, Compra entity) {
//        if (compraFacade.exist(id)&& entity!=null) {
//            compraFacade.edit(entity);
//        }
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Compra> findAll() {
        return compraFacade.findAll();
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Compra findbyId(@PathParam("id") int id) {
        return compraFacade.findById(id);
    }
}
