package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Bitacora;
import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Orden;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile ListAttribute<Usuario, Bitacora> bitacoraList;
    public static volatile SingularAttribute<Usuario, String> pin;
    public static volatile ListAttribute<Usuario, Orden> ordenList;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> nombreCompleto;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile SingularAttribute<Usuario, String> rol;

}