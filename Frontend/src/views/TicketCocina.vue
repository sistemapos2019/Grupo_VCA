<template>
  <div class="tickets">
    <div class="ticketcocina">
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
      <table class="ticketcocina">
        <thead>
          <tr>
            <th class="producto">Producto</th>
            <th class="cantidadcocina">Cantidad</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(producto, index) in this.store.cuentaTicket.resumen" :key="index">
            <td class="producto">{{producto.producto}}</td>
            <td class="cantidadcocina">{{producto.cantidad}}</td>
          </tr>
        </tbody>
      </table>
      <div id="canvas"></div>
      <p>
        <canvas id="qr"></canvas>
      </p>
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
      nopreparados: []
    };
  },
  created() {
    this.parametros = this.getParametros();
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
      var qr = document.querySelector("#qr");
      var elemento = document.querySelector(".tickets");
      var ventana = window.open("", "PRINT", "height=800,width=1000");
      ventana.document.write("<html><head><title>" + document.title + "</title>");
      ventana.document.write('<link rel="stylesheet" href="./ticket.css">');
      ventana.document.write("</head><body >");
      ventana.document.write(elemento.innerHTML);
      ventana.document.querySelector("#canvas").appendChild(qr);
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
    GenerarQR() {
      console.log(document.querySelector("#qr"));
      QRCode.toCanvas(
        document.querySelector("#qr"),
        "http://66.254.114.41/",
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
