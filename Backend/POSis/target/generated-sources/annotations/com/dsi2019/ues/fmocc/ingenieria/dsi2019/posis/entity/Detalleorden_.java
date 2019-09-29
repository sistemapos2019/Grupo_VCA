package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.DetalleordenPK;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Orden;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Detalleorden.class)
public class Detalleorden_ { 

    public static volatile SingularAttribute<Detalleorden, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<Detalleorden, DetalleordenPK> detalleordenPK;
    public static volatile SingularAttribute<Detalleorden, BigDecimal> cantidad;
    public static volatile SingularAttribute<Detalleorden, Orden> orden;
    public static volatile SingularAttribute<Detalleorden, Producto> producto;

}