export default class CuentaEntity {
    constructor(cuenta) {
        this.cuenta = cuenta.id;
        this.mesa = cuenta.idMesa.id;
        this.mesero = cuenta.idUsuario.nombreCompleto;
        this.cliente = cuenta.cliente;
        this.observacion = cuenta.observacion;
        this.total = cuenta.total;
        this.resumen = cuenta.detalleordenList.map(detalle => {
            return {
                id: detalle.producto.id,
                producto: detalle.producto.nombre,
                precio: detalle.producto.precio,
                cantidad: detalle.cantidad,
                preparado: detalle.producto.preparado
            }
        });
    }
}