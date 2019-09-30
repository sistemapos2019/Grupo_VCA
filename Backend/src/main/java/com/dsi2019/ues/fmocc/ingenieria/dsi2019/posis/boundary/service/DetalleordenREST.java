/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.DetalleordenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.OrdenFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.ProductoFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detalleorden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.DetalleordenPK;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Mesa;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Orden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Path("detalleorden")
public class DetalleordenREST {

    @PathParam("idOrden")
    Integer idOrden;

    @EJB
    DetalleordenFacade detalleOrdenFacade;
    @EJB
    OrdenFacade ordenFacade;
    @EJB
    ProductoFacade productoFacade;
    Response envio;
    Orden orden;
    Mesa mesa;
    Usuario usuario;
    Detalleorden entity;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@QueryParam("mesero") String mesero,
            @QueryParam("cliente") String cliente,
            @QueryParam("observaciones") String observaciones,
            @QueryParam("idcuenta") @DefaultValue("0") int idcuenta,
            List<Detalleorden> lst) {
        if (!mesero.equals("") && mesero != null && lst != null) {
            orden = new Orden();
            if (idcuenta == 0) {
                orden.setEstado("AA");
                orden.setCliente(cliente);
                orden.setIdMesa(mesa);
                orden.setIdUsuario(usuario);
                orden.setObservacion(observaciones);
                orden.setFecha(new Date());
                orden = ordenFacade.crear(orden);
            } else {
                orden = ordenFacade.findById(idcuenta);
                orden.setObservacion(observaciones);
                ordenFacade.edit(orden);
                detalleOrdenFacade.removeDetalle(idcuenta);
            }
            for (Detalleorden lst1 : lst) {
                if (productoFacade.exist(lst1.getProducto().getId())) {
                    entity = new Detalleorden();
                    entity.setCantidad(lst1.getCantidad());
                    entity.setPrecioUnitario(lst1.getProducto().getPrecio());
                    entity.setDetalleordenPK(new DetalleordenPK(orden.getId(), lst1.getProducto().getId()));
                }
                detalleOrdenFacade.create(entity);
            }
            ordenFacade.edit(orden);
            return Response.status(Response.Status.OK)
                    .header("Registro Creado", lst.size())
                    .build();

        }
        return Response.status(Response.Status.NOT_FOUND)
                .header("Error al crear", lst.size())
                .build();

    }

    @GET
    @Path("stats")
    @Produces(MediaType.APPLICATION_JSON)
    public List stats() {
        return detalleOrdenFacade.estadisticas();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Detalleorden> findAll() {
        System.out.println(detalleOrdenFacade.findAll());
        return detalleOrdenFacade.findAll();
    }

}