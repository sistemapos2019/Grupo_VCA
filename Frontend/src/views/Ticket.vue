<template>
  <div class="tickets">
    <div class="ticketcliente">
      <p class="centrado">
        <br />
        {{this.parametros[1].valor}}
        <br />
        {{this.parametros[2].valor}}
        <br />
        Telefono: {{this.parametros[3].valor}}
        <br />
        Nit: {{this.parametros[4].valor}}
        <br />
        Giro: {{this.parametros[5].valor}}
        <br />
        Direccion: {{this.parametros[6].valor}}
      </p>
      <p class="izquierda">
        <hr class="hr1">
        {{new Date().toLocaleString()}}
        <br />
        Cuenta: {{this.store.cuentaTicket.cuenta}}
        <br />
        Mesa: {{this.store.cuentaTicket.mesa}}
        <br />
        Mesero: {{this.store.cuentaTicket.mesero}}
        <br />
        <hr class="hr1">
      </p>
      <table class="ticketcliente">
        <thead>
          <tr>
            <th class="producto">Producto</th>
            <th class="cantidad">Ct</th>
            <th class="precio">c/u</th>
            <th class="total">Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(producto, index) in this.store.cuentaTicket.resumen" :key="index">
            <td class="producto">{{producto.producto}}</td>
            <td class="cantidad">{{producto.cantidad}}</td>
            <td class="precio">${{producto.precio}}</td>
            <td class="precio">${{(producto.precio * producto.cantidad).toFixed(2)}}</td>
          </tr>
        </tbody>
      </table>
      <div class="total">
      <p class="izquierda">
        <hr class="hr1">
        Subtotal: ${{(this.store.cuentaTicket.total).toFixed(2)}}
        <br />
        Propina: ${{(this.store.cuentaTicket.propina).toFixed(2)}}
        <br />
        Total: ${{(this.store.cuentaTicket.total + this.store.cuentaTicket.propina).toFixed(2)}}
        <br />
        Efectivo: ${{(this.store.pago)}}
        <br />
        Cambio: ${{(this.store.pago - (this.store.cuentaTicket.total + this.store.cuentaTicket.propina)).toFixed(2)}}
        <br />
        <hr class="hr1">
        ¡GRACIAS POR SU COMPRA!
      </p>
      </div>
    </div>
  </div>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { mapState } from "vuex";
import QRCode from "qrcode";
const rm = new restMethods();
export default {
  data() {
    return {
      parametros: this.getParametros(),
      preparados: [],
      nopreparados: [],
      propina: null,
    };
  },
  created() {
    this.parametros = this.getParametros();
    this.propina = this.store.cuentaTicket.total * this.store.propina;
  },
  updated() {
    this.imprimirElemento();
  },
  computed: {
    ...mapState(["store"])
  },
  methods: {
    imprimirElemento() {
      var elemento = document.querySelector(".tickets");
      var ventana = window.open("", "PRINT", "height=800,width=1000");
      ventana.document.write(
        "<html><head><title>" + document.title + "</title>"
      );
      ventana.document.write('<link rel="stylesheet" href="./ticket.css">');
      ventana.document.write("</head><body >");
      ventana.document.write(elemento.innerHTML);
      ventana.document.write("</body></html>");
      ventana.document.close();
      ventana.focus();
     ventana.onload = function() {
        ventana.print();
        ventana.close();
      };
      this.$router.push("dashboard");
      return true;
    },
    getParametros() {
      rm.getJson("parametros")
        .then(r => {
          this.parametros = r.data;
          console.log(this.parametros);
        })
        .catch(e => {
          this.parametros = [];
        });
    },
    filtrarPreparados() {
      this.preparados = this.store.cuentaTicket.resumen.filter(
        producto => preparado === true
      );
      this.nopreparados = this.store.cuentaTicket.resumen.filter(
        producto => preparado === false
      );
      console.log(this.preparados);
      console.log(this.nopreparados);
    },
  }
};
</script>

<style scoped>
td,
th,
tr,
table {
  border-top: 1px solid black;
  border-collapse: collapse;
}

td.producto,
th.producto {
  width: 75px;
  max-width: 75px;
}

td.cantidad,
th.cantidad {
  width: 40px;
  max-width: 40px;
  word-break: break-all;
}

td.precio,
th.precio {
  width: 40px;
  max-width: 40px;
  word-break: break-all;
}

.centrado {
  text-align: center;
  align-content: center;
}

.izquierda {
  text-align: left;
  align-content: left;
}

.ticket {
  width: 155px;
  max-width: 155px;
  font-size: 11px;
  font-family: "Times New Roman";
}

img {
  max-width: inherit;
  width: inherit;
}
</style>
