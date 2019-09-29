package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Categoria;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detallecompra;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Detalleorden;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, BigDecimal> precio;
    public static volatile ListAttribute<Producto, Detallecompra> detallecompraList;
    public static volatile ListAttribute<Producto, Detalleorden> detalleordenList;
    public static volatile SingularAttribute<Producto, Integer> preparado;
    public static volatile SingularAttribute<Producto, Integer> id;
    public static volatile SingularAttribute<Producto, Categoria> idCategoria;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, Integer> inventario;

}