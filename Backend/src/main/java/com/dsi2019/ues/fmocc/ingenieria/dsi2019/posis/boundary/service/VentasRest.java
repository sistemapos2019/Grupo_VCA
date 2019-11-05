/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.CompraFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.ventasFacade;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author deadbryam
 */
@Path("ventas")
public class VentasRest {

    @EJB
    ventasFacade ventasFacade;
    @EJB
    CompraFacade compraFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response ventas(@QueryParam("date") @DefaultValue("") String date) {
        return (date.isEmpty()) ? findAll() : ventasByDate(date);
    }

    public Response findAll() {
        return Response
                .ok()
                .entity(ventasFacade.findAll())
                .build();
    }

    public Response ventasByDate(String date) {
        System.out.println(date);
        return Response
                .ok()
                .header("contribuyente", compraFacade.parametros(2))
                .header("nrc", compraFacade.parametros(13))
                .entity(ventasFacade.ventasByDate(date.split("-")))
                .build();
    }

}
