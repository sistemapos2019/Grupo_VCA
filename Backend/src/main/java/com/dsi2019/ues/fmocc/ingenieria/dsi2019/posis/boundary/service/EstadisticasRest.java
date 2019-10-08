/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.EstadisticasFacade;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("estadisticas")
public class EstadisticasRest {

    @EJB
    EstadisticasFacade estadisticasFacade;

    StatsJson sj;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response estadisticas(@QueryParam("date") @DefaultValue("") String fecha,
            @QueryParam("week") @DefaultValue("false") boolean semana) {
        fecha = (fecha.isEmpty()) ? obtenerFechaActual() : fecha;

        System.out.println("bool:" + semana);

        return (semana) ? semanal(fecha) : diario(fecha);
    }

    public String obtenerFechaActual() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
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

    public Response diario(String fecha) {
        sj = new StatsJson(
                estadisticasFacade.platilloVendidos(string2Date(fecha)),
                estadisticasFacade.ordenesVendidas(string2Date(fecha)),
                estadisticasFacade.platosVendidos(string2Date(fecha))
        );

        String json = new Gson().toJson(sj);
        return Response.ok()
                .entity(json)
                .build();
    }

    public Response semanal(String fecha) {
        Date start, end;
        Calendar cl = Calendar.getInstance();
        List<resumenJson> rs = new ArrayList<>();
        String json;

        cl.setTime(string2Date(fecha));
        cl.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        start = cl.getTime();
        cl.add(Calendar.DATE, 6);
        end = cl.getTime();

        for (Object object : estadisticasFacade.platillosSemanales(start, end).toArray()) {
            rs.add(new resumenJson((Object[])object));
        }

        json = new Gson().toJson(rs).replaceAll("^\\[", "").replaceAll("\\]$", "");

        return Response.ok()
                .entity(json)
                .build();
    }

}

final class StatsJson {

    private String platillos;
    int ordenes, platos;

    public StatsJson(String platillos, String ordenes, String platos) {
        this.platillos = platillos;
        this.ordenes = Integer.parseInt(ordenes);
        this.platos = Integer.parseInt(platos);
    }

    public String getPlatillos() {
        return platillos;
    }

    public void setPlatillos(String platillos) {
        this.platillos = platillos;
    }

    public int getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(int ordenes) {
        this.ordenes = ordenes;
    }

    public int getPlatos() {
        return platos;
    }

    public void setPlatos(int platos) {
        this.platos = platos;
    }
}

class resumenJson {

    private int dia, cantidad;

    public resumenJson(int dia, int cantidad) {
        this.dia = dia;
        this.cantidad = cantidad;
    }

    public resumenJson(Object[] array) {
        this.dia = Integer.parseInt(String.valueOf(array[0]));
        this.cantidad = Integer.parseInt(String.valueOf(array[1]));
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
