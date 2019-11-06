/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.CompraFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.DetalleordenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("compras")
public class CompraREST {

    @EJB
    CompraFacade compraFacade;
    @EJB
    DetalleordenFacade detalleCompraFacade;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Compra entity) {
        if (entity != null) {
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
    public Response compras(@QueryParam("date") @DefaultValue("") String date) {
        return (date.isEmpty()) ? findAll() : CompraByDate(date);
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Compra findbyId(@PathParam("id") int id) {
        return compraFacade.findById(id);
    }

    public Response findAll(){
        return Response
                .ok()
                .entity(compraFacade.findAll())
                .build();
    }
    
    public Response CompraByDate(String fecha){
        return Response
                .ok()
                .header("contribuyente", compraFacade.parametros(2))
                .header("nrc", compraFacade.parametros(13))
                .entity(compraFacade.compraByDate(fecha.split("-")))
                .build();
    }
    
    public Date string2Date(String fecha) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(EstadisticasRest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
