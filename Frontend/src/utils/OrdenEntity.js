export default class OrdenEntity {
    constructor(orden, detalleOrden, propina, llevar) {
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
            this.propina = (orden.total * propina).toFixed(2),
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