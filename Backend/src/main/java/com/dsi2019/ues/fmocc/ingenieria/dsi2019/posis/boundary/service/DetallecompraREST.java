/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.DetallecompraFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detallecompra;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.DetallecompraPK;
import com.google.gson.Gson;
import java.util.ArrayList;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author arevalo
 */
@Path("detallecompras")
public class DetallecompraREST  {
    @EJB
    DetallecompraFacade detalleFacade;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response datalle(@QueryParam("compra") @DefaultValue("0") int compra){
        List<Detalle> dc=new ArrayList<>();
        String json;
        if (compra!=0) {
            for (Object object :detalleFacade.detalle(compra).toArray()) {
                dc.add(new Detalle((Object[]) object));
            }
            json= new Gson().toJson(dc);
            return Response.ok()
                    .entity(json)
                    .build();
        }
        return Response.status(Response.Status.FOUND).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Detallecompra entity){
        if (entity!=null) {
            detalleFacade.create(entity);
            return Response.ok(entity).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
}
class Detalle{
    private String nombre,precio,cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Detalle(String nombre, String precio, String cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public Detalle(Object[] object){
        this.nombre = String.valueOf(object[0]);
        this.precio = String.valueOf(object[2]);
        this.cantidad = String.valueOf(object[1]);
    }
}
