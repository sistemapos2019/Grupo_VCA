package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Compra;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.DetallecompraPK;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Detallecompra.class)
public class Detallecompra_ { 

    public static volatile SingularAttribute<Detallecompra, Compra> compra;
    public static volatile SingularAttribute<Detallecompra, DetallecompraPK> detallecompraPK;
    public static volatile SingularAttribute<Detallecompra, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<Detallecompra, BigDecimal> cantidad;
    public static volatile SingularAttribute<Detallecompra, Producto> producto;

}