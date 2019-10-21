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
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
@Path("bitacoras")
public class BitacoraREST {

    @EJB
    BitacoraFacade bitacoraFacade;
    List<ResponseJson> rs;
    String json;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Bitacora entity) {
        if (entity != null) {
            bitacoraFacade.create(entity);
            return Response.ok(entity)
                    .header("Registro Creado", 1)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
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
    public Response findAll() {
          rs= new ArrayList<>();
        for (Object object : bitacoraFacade.rango("", "",true).toArray()) {
            rs.add(new ResponseJson((Object[]) object));
        }
        json = new Gson().toJson(rs);
        return Response.ok()
                .entity(json)
                .header("Total Registro", 1).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Bitacora findbyId(@PathParam("id") int id) {
        return bitacoraFacade.findById(id);
    }

    @GET
    @Path("rango")
    @Produces({MediaType.APPLICATION_JSON})
    public Response Rango(@QueryParam("inicio") @DefaultValue("1998-10-27") String Inicio,
            @QueryParam("fin") @DefaultValue("2020-10-27") String Fin) {
        rs= new ArrayList<>();
        for (Object object : bitacoraFacade.rango(Inicio, Fin,false).toArray()) {
            rs.add(new ResponseJson((Object[]) object));
        }

        json = new Gson().toJson(rs);

        return Response.ok()
                .entity(json)
                .build();
        // return Response.ok(bitacoraFacade.Rango(Inicio, Fin)).header("Total Registros", 1).build();

    }
}

class ResponseJson {

    private String suceso, usuario,fecha;

    public ResponseJson(String fecha, String suceso, String usuario) {
        this.fecha = fecha;
        this.suceso = suceso;
        this.usuario = usuario;
    }

    public ResponseJson(Object[] array) {
        this.fecha=String.valueOf(array[0]);
        this.suceso = String.valueOf(array[1]);
        this.usuario = String.valueOf(array[2]);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSuceso() {
        return suceso;
    }

    public void setSuceso(String suceso) {
        this.suceso = suceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
