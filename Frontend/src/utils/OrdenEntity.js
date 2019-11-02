export default class OrdenEntity {
    constructor(orden, detalleOrden) {
        this.id = orden.cuenta;
        this.cliente = orden.cliente,
            this.estado = "AA",
            this.formaPago = "E",
            this.idMesa = {
                id: orden.mesa
            },
            this.idUsuario = {
                id: orden.mesero
            },
            this.llevar = 0,
            this.observacion = orden.observacion,
            this.propina = 0,
            this.total = orden.total,
            this.detalleordenList = detalleOrden.map(producto => {
                return {
                    cantidad: producto.cantidad,
                    precioUnitario: producto.precio,
                    producto: {
                        id: producto.id
                    }
                }
            });
    }
}