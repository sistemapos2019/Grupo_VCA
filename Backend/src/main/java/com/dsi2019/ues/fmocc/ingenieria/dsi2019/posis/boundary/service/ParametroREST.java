/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.ParametroFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Parametro;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author arevalo
 */
@Path("parametros")
public class ParametroREST {

    @EJB
    ParametroFacade parametrosFacade;

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Parametro entity) {
        if (parametrosFacade.exist(id)&& entity!=null) {
            parametrosFacade.edit(entity);
            return Response.ok(entity)
                    .header("Registro Editado con Exito", id)
                    .build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        return Response.ok(parametrosFacade.findAll())
                .header("Total-Registros", 1)
                .build();
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findbyId(@PathParam("id") int id) {
        if(parametrosFacade.exist(id)){
        return Response.ok(parametrosFacade.findById(id)).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
    }
}