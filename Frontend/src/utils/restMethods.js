import Axios from 'axios'

export default class RestMethods {
    constructor() {
        this.baseUri = 'http://localhost:8080/POSis-1.0-SNAPSHOT/ws/'
        this.json = null
        this.suceso=''
        this.parPan
    }

    getJson(resource) {
        if (resource != null) {
            return Axios.get(this.baseUri + resource)
        }
    }

    postJson(resource, registro) {
        if (resource != null && registro != null) {
            return Axios.post(this.baseUri + resource, registro)
        }
    }

    putJson(resource, registro) {
        if (resource != null && registro != null, { headers: { 'Content-Type': 'application/json' } }) {
            return Axios.put(this.baseUri + resource, registro)
        }
    }

    deleteJson(resource, id) {
        if (resource != null && id != null) {
            Axios.delete(this.baseUri + resource + id, {
                headers: {
                    'Origin': 'http://localhost:8081/',
                    'Access-Control-Request-Method': 'DELETE',
                    'Access-Control-Request-Headers': 'Content-Type',
                },
            })
                .then(response => {
                    console.log(response)
                })
        }
    }

    postJsonBitacora(registro) {
        if (registro != null) {
            switch (registro.id) {
                case 1:
                    this.suceso = 'Realizó una nueva Orden' 
                    break
                case 2:
                    this.suceso = 'Agrego productos a la Orden N°' + registro.idOrden 
                    break
                case 3:
                    this.suceso = 'Editó los productos existentes de la Orden N°' + registro.idOrden 
                    break
                case 4:
                    this.suceso = 'Finalizó la Orden N°' + registro.idOrden 
                    break
                case 5:
                    this.suceso = 'Consulto las estadisticas de ventas' 
                    break
                case 6:
                    this.suceso = 'Editó las existencias del producto ' + registro.nombreProducto 
                    break
                case 7:
                    this.suceso = 'Consulto la contabilidad del Libro de ' + registro.libro 
                    break
                case 8:
                    this.suceso = 'Generó reporte del Libro de ' + registro.libro + ' del mes de ' + registro.mes 
                    break
                case 9:
                    this.suceso = 'Agrego una nueva Categoria' 
                    break
                case 10:
                    this.suceso = 'Edito la Categoria N° ' + registro.idCategoria 
                    break
                case 11:
                    this.suceso = 'Edito credenciales del usuario N° ' + registro.idCredencial 
                    break
                case 12:
                    this.suceso = 'Creo un nuevo Usuario' 
                    break
                case 13:
                    this.suceso = 'Edito el Producto N° ' + registro.idProducto 
                    break
                case 14:
                    this.suceso = 'Elimino el Producto N° ' + registro.idProducto 
                    break
                case 15:
                    this.suceso = 'Agrego un nuevo Producto ' 
                    break
                case 16:
                    this.suceso = 'Edito el valor del Parametro N° ' + registro.idParametro 
                    break
                case 17:
                    this.suceso = 'Consulto los detalles de la Compra con Documento N° ' + registro.idCompra 
                    break
                case 18:
                    this.suceso = 'Edito la Compra N° ' + registro.idCompra 
                    break
                case 19:
                    this.suceso = 'Edito la Mesa N° ' + registro.idMesa 
                    break
                case 20:
                    this.suceso = 'Agrego una nueva Mesa ' 
                    break
                case 21:
                    this.suceso = 'El Usuario entro al Sistema' 
                    break

            }
            return Axios.post(this.baseUri + "bitacoras", {
                fecha: new Date(),
                suceso: this.suceso,
                idUsuario: registro.usuario
            })
        }
    }
}
