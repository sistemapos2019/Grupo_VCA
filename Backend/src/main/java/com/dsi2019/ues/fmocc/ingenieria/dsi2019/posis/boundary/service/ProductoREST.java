/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.ProductoFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Producto;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author arevalo
 */
@Path("productos")
public class ProductoREST {

    @EJB
    ProductoFacade productoFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Producto entity) {
        if (entity!=null) {
            entity=productoFacade.create(entity);
            return Response.status(Response.Status.CREATED)
                    .entity(entity)
                    .header("Registro Creado", entity)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al Crear", 1).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Producto entity) {
        if (productoFacade.exist(id) && entity!=null) {
            productoFacade.edit(entity);
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .header("Registro Modificado", id)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al Modificar", 1).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(productoFacade.findAll())
                .header("Total-Reg",1)
                .build();
    }
    
    @GET
    @Path("rango")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("first") @DefaultValue("0") Integer first,
                               @QueryParam("size") @DefaultValue("5") Integer size) {
        
        return Response.ok(productoFacade.findRange(first, size))
                .header("Total-Reg",productoFacade.findRange(first, size).size())
                .build();
    }

}