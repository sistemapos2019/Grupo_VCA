<template>
  <div class="tickets">
    <div class="ticketcocina">

      <!--div class="ticketGeneral">
      <p class="izquierda">
        <br />
        {{new Date().toLocaleString()}}
        <br />
        Cuenta: {{this.store.cuentaTicket.cuenta}}
        <br />
        Mesa: {{this.store.cuentaTicket.mesa}}
        <br />
        Mesero: {{this.store.cuentaTicket.mesero}}
        <br />
      </p>
      <table class="ticketcocina tabla">
        <thead>
          <tr>
            <th class="producto tabla">Producto</th>
            <th class="cantidadcocina tabla">Cantidad</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(producto, index) in this.store.cuentaTicket.resumen" :key="index">
            <td class="producto tabla">{{producto.producto}}</td>
            <td class="cantidadcocina tabla">{{producto.cantidad}}</td>
          </tr>
        </tbody>
      </table>
      <div id="canvas1"></div>
    </div-->

    <div class="ticketPreparados">
      <p class="izquierda">
        Preparados
        <br />
        {{new Date().toLocaleString()}}
        <br />
        Cuenta: {{this.store.cuentaTicket.cuenta}}
        <br />
        Mesa: {{this.store.cuentaTicket.mesa}}
        <br />
        Mesero: {{this.store.cuentaTicket.mesero}}
        <br />
      </p>
      <table class="ticketcocina tabla">
        <thead>
          <tr>
            <th class="producto tabla">Producto</th>
            <th class="cantidadcocina tabla">Cantidad</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(producto, index) in this.preparados" :key="index">
            <td class="producto tabla">{{producto.producto}}</td>
            <td class="cantidadcocina tabla">{{producto.cantidad}}</td>
          </tr>
        </tbody>
      </table>
      <div id="canvas2"></div>
    </div>

    <div class="ticketNoPreparados">
      <p class="izquierda">
        NoPreparados
        <br />
        {{new Date().toLocaleString()}}
        <br />
        Cuenta: {{this.store.cuentaTicket.cuenta}}
        <br />
        Mesa: {{this.store.cuentaTicket.mesa}}
        <br />
        Mesero: {{this.store.cuentaTicket.mesero}}
        <br />
      </p>
      <table class="ticketcocina tabla">
        <thead>
          <tr>
            <th class="producto tabla">Producto</th>
            <th class="cantidadcocina tabla">Cantidad</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(producto, index) in this.nopreparados" :key="index">
            <td class="producto tabla">{{producto.producto}}</td>
            <td class="cantidadcocina tabla">{{producto.cantidad}}</td>
          </tr>
        </tbody>
      </table>
      <div id="canvas3"></div>
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
      preparados: [],
      nopreparados: [],
      qr1: null,
      qr2: null,
      qr3: null
    };
  },
  created() {
    this.filtrarPreparados();

  },
  mounted() {
    this.GenerarQR();
    this.imprimirElemento();
  },
  computed: {
    ...mapState(["store"])
  },
  methods: {
    imprimirElemento() {
      var ticketPP = document.querySelector(".ticketPreparados");
      var ticketNP = document.querySelector(".ticketNoPreparados");

      var ventana = window.open("", "PRINT", "height=800,width=1000");
      ventana.document.write("<html><head><title>" + document.title + "</title>");
      ventana.document.write('<link rel="stylesheet" href="./ticket.css">');
      ventana.document.write("</head><body >");
console.log(this.store.imprimirNP)
console.log(this.store.imprimirPP)

      if (this.store.imprimirPP === "SI") {
        ventana.document.write(ticketPP.innerHTML);
        ventana.document.querySelector("#canvas2").appendChild(this.qr2);
      } if(this.store.imprimirNP === "SI"){
        ventana.document.write(ticketNP.innerHTML);
        ventana.document.querySelector("#canvas3").appendChild(this.qr3);
      }
      
      
      ventana.document.write("</body></html>");
      ventana.document.close();
      ventana.focus();
      this.$router.push("dashboard");
      ventana.onload = function() {
        ventana.print();
        ventana.close();
      };
      return true;
    },
    filtrarPreparados() {
      console.log(this.store.cuentaTicket.resumen)
      this.preparados = this.store.cuentaTicket.resumen.filter(
        producto => producto.preparado === 1
      );
      this.nopreparados = this.store.cuentaTicket.resumen.filter(
        producto => producto.preparado === 0
      );
      console.log(this.preparados);
      console.log(this.nopreparados);
    },
    GenerarQR() {
      this.qr2 = document.createElement("canvas");
      console.log(this.store.cuentaTicket.cuenta)

       QRCode.toCanvas(
        this.qr2,
        `http://localhost:8080/Backend/ws/ordenes/cerrarorden?type=NP&id=${this.store.cuentaTicket.cuenta}`,
        { toSJISFunc: QRCode.toSJIS },
        function(error) {
          if (error) console.error(error);
          console.log("success!");
        }
      );
       this.qr3 = document.createElement("canvas");

       QRCode.toCanvas(
        this.qr3,
        `http://localhost:8080/Backend/ws/ordenes/cerrarorden?type=PP&id=${this.store.cuentaTicket.cuenta}`,
        { toSJISFunc: QRCode.toSJIS },
        function(error) {
          if (error) console.error(error);
          console.log("success!");
        }
      );
    }
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
