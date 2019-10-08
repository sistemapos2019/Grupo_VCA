/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.UsuarioFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("usuarios")
public class UsuarioREST {
    
    @EJB
    UsuarioFacade usuarioFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Usuario entity) {
        if (entity!=null) {
            entity=usuarioFacade.create(entity);
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
    public Response edit(@PathParam("id") Integer id, Usuario entity) {
        if (usuarioFacade.exist(id) && entity!=null) {
            usuarioFacade.edit(entity);
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
        if (!usuarioFacade.findAll().isEmpty()) {
             return Response.ok()
                .entity(usuarioFacade.findAll())
                .header("Total-Reg",1)
                .build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
            
    }
    
    @GET
    @Path("rango")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("first") @DefaultValue("0") Integer first,
                               @QueryParam("size") @DefaultValue("5") Integer size) {
        
        return Response.ok(usuarioFacade.findRange(first, size))
                .header("Total-Reg",usuarioFacade.findRange(first, size).size())
                .build();
    }
    @GET
    @Path("user")
    @Produces({MediaType.APPLICATION_JSON})
   public  Response User(@QueryParam("login") @DefaultValue("") String login){
        if (!login.isEmpty()) {
            return Response.ok(usuarioFacade.LoginUser(login))
                    .header("Total-Registro",1)
                    .build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
                
   }
    
}
