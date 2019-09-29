package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detallecompra;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, String> nombreProveedor;
    public static volatile SingularAttribute<Compra, Date> fecha;
    public static volatile SingularAttribute<Compra, BigDecimal> total;
    public static volatile ListAttribute<Compra, Detallecompra> detallecompraList;
    public static volatile SingularAttribute<Compra, BigDecimal> percepcion;
    public static volatile SingularAttribute<Compra, BigDecimal> iva;
    public static volatile SingularAttribute<Compra, BigDecimal> montoInterno;
    public static volatile SingularAttribute<Compra, Integer> id;
    public static volatile SingularAttribute<Compra, String> ndocumento;
    public static volatile SingularAttribute<Compra, String> nitdui;
    public static volatile SingularAttribute<Compra, String> nrc;

}