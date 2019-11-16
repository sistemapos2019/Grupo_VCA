/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.custom;

/**
 *
 * @author deadbryam
 */
public class EstadisticaDiaria {

    private String platillos;
    int ordenes, platos;

    public EstadisticaDiaria(String platillos, String ordenes, String platos) {
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
