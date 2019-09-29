package com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity;

import com.dsi2019.ues.fmocc.ingenieria.dsi2019.posis.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-29T00:04:40")
@StaticMetamodel(Bitacora.class)
public class Bitacora_ { 

    public static volatile SingularAttribute<Bitacora, Date> fecha;
    public static volatile SingularAttribute<Bitacora, Usuario> idUsuario;
    public static volatile SingularAttribute<Bitacora, Integer> id;
    public static volatile SingularAttribute<Bitacora, String> suceso;

}