/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.AbstractFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.BitacoraFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Bitacora;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Bitacora;
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
@Path("bitacoras")
public class BitacoraREST {
    @EJB
    BitacoraFacade bitacoraFacade;
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Bitacora entity) {
        if (entity!=null) {
            bitacoraFacade.create(entity);
        }
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Integer id, Bitacora entity) {
//        if (bitacoraFacade.exist(id)&& entity!=null) {
//            bitacoraFacade.edit(entity);
//        }
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Bitacora> findAll() {
        return bitacoraFacade.findAll();
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Bitacora findbyId(@PathParam("id") int id) {
        return bitacoraFacade.findById(id);
    }
}
