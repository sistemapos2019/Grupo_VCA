/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.boundary.service;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.controller.EstadisticasFacade;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.custom.EstadisticaDiaria;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.custom.EstadisticaSemanal;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        EstadisticaDiaria sj = new EstadisticaDiaria(
                estadisticasFacade.platilloVendidos(string2Date(fecha)),
                estadisticasFacade.ordenesVendidas(string2Date(fecha)),
                estadisticasFacade.platosVendidos(string2Date(fecha))
        );

        String json = new Gson().toJson(sj);
        return Response.ok()
                .entity(json)
                .header("Count", estadisticasFacade.countByDate(string2Date(fecha)))
                .build();
    }

    public Response semanal(String fecha) {
        Calendar cl = Calendar.getInstance(new Locale("es"));
        String json;
        List<EstadisticaSemanal> lst = new ArrayList<>();

        cl.setFirstDayOfWeek(Calendar.MONDAY);
        cl.setTime(string2Date(fecha));
//        cl.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        estadisticasFacade.platillosSemanales(cl.get(cl.WEEK_OF_YEAR), cl.getWeekYear()).forEach((platillosSemanale) -> {
            lst.add(new EstadisticaSemanal(platillosSemanale));
        });

        System.out.println(lst);
        json = new Gson().toJson(lst);

        return Response.ok()
                .entity(json)
                .header("Count", estadisticasFacade.countByDate(string2Date(fecha)))
                .build();
    }

}
