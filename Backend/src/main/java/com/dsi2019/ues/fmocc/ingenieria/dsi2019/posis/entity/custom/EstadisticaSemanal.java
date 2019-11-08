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
public class EstadisticaSemanal {

    private int dia, cantidad;

    public EstadisticaSemanal(int dia, int cantidad) {
        this.dia = dia;
        this.cantidad = cantidad;
    }
    
    public EstadisticaSemanal(Object[] values) {
        this.dia = Integer.parseInt(String.valueOf(values[0]));
        this.cantidad =  Integer.parseInt(String.valueOf(values[1]));
    }
    
    public EstadisticaSemanal() {
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
