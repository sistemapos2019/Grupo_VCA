package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detalleorden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Mesa;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Orden.class)
public class Orden_ { 

    public static volatile SingularAttribute<Orden, String> estado;
    public static volatile SingularAttribute<Orden, Usuario> idUsuario;
    public static volatile SingularAttribute<Orden, BigDecimal> propina;
    public static volatile SingularAttribute<Orden, Integer> llevar;
    public static volatile SingularAttribute<Orden, Date> fecha;
    public static volatile SingularAttribute<Orden, String> cliente;
    public static volatile SingularAttribute<Orden, BigDecimal> total;
    public static volatile SingularAttribute<Orden, Mesa> idMesa;
    public static volatile ListAttribute<Orden, Detalleorden> detalleordenList;
    public static volatile SingularAttribute<Orden, Date> tiempoRapido;
    public static volatile SingularAttribute<Orden, Date> tiempoPreparado;
    public static volatile SingularAttribute<Orden, Integer> id;
    public static volatile SingularAttribute<Orden, String> formaPago;
    public static volatile SingularAttribute<Orden, String> observacion;

}