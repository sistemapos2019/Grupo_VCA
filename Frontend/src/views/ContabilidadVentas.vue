<template>
  <v-container fluid>
    <v-row>
      <v-col class="tittle_text text-center display-1">LIBRO DE VENTAS AL CONSUMIDOR</v-col>
    </v-row>
    <v-row>
      <v-col lg="3">
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="date"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="month"
              label="Filtrado por fechas"
              prepend-icon="event"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            locale="es"
            color="blue"
            v-model="date"
            type="month"
            no-title
            scrollable
            @change="getVentas(); $refs.menu.save(date); updateMonth();"
          ></v-date-picker>
        </v-menu>
      </v-col>
      <v-spacer></v-spacer>
      <v-col lg="2">
        <v-btn
          @click="generarReporte()"
          style="background: var(--gradient-orange); color:white;"
        >GENERAR REPORTE</v-btn>
      </v-col>
      <v-col lg="2">
        <v-btn
          :to="to"
          style="background: var(--gradient-lightpurple); color:white;"
        >CAMBIAR A COMPRAS</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col lg="12">
        <table id="contableVentas">
          <thead>
            <tr>
              <th
                style="text-align: center;"
                colspan="6"
              >NOMBRE CONTRIBUYENTE: {{contribuyente | upper}}</th>
            </tr>
            <tr>
              <th
                colspan="2"
              >MES: {{month}}</th>
              <th colspan="1">AÑO: {{new Date(date+'-02').getFullYear()}}</th>
              <th colspan="3">NRC:</th>
            </tr>
            <tr>
              <td rowspan="2">FECHA</td>
              <td colspan="2">DOCUMENTOS EMITIDOS</td>
              <td rowspan="2">VENTAS</td>
              <td rowspan="2">PROPINAS</td>
              <td rowspan="2">TOTAL</td>
            </tr>
            <tr>
              <td>DEL No</td>
              <td>AL No</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in ventas" :key="item.id">
              <td>{{item.fecha | date}}</td>
              <td>{{item.inicial}}</td>
              <td>{{item.final1}}</td>
              <td>{{item.propinas | money}}</td>
              <td>{{item.ventas | money}}</td>
              <td>{{item.total | money}}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="3">TOTALES</td>
              <td>{{totales.ventas | money}}</td>
              <td>{{totales.propinas | money}}</td>
              <td>{{totales.total | money}}</td>
              <td></td>
            </tr>
          </tfoot>
        </table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import jsPDF from "jspdf";
import "jspdf-autotable";
const rm = new restMethods();
export default {
  data() {
    return {
      ventas: {},
      menu: false,
      date: new Date().toISOString().substr(0, 7),
      month: "",
      contribuyente: "",
      totales: {
        ventas: 0.0,
        total: 0.0,
        propinas: 0.0
      },
      to: "contcompras"
    };
  },
  methods: {
    getVentas() {
      rm.getJson(`ventas?date=${this.date}`)
        .then(r => {
          this.ventas = r.data;
          this.contribuyente = r.headers.contribuyente;
          r.data.map(m=>{
            this.totales.ventas += m.ventas;
            this.totales.total += m.total;
            this.totales.propinas += m.propinas;
          })
        })
        .catch(e => {});
    },
    generarReporte() {
      const doc = new jsPDF("l", "mm", "a4");
      doc.setFontSize(40);
      doc.text(30, 25, "LIBRO DE VENTAS AL COSUMIDOR");
      doc.autoTable({
        html: "table#contableVentas",
        theme: "grid",
        startY: 50,
        styles: {
          overflow: "linebreak",
          fontSize: 8,
          valign: "middle",
          halign: "center"
        },
        didParseCell: HookData => {
          if (HookData.cell != undefined) {
            if(HookData.cell.text == "TOTALES"){
              HookData.cell.styles.halign = "right";
            }
          }
        }
      });
      //doc.save("table.pdf");
      doc.output("dataurlnewwindow");
    },
    updateMonth(){
      this.month = new Date(this.date+"-02").toLocaleString('default', { month: 'long' }).toUpperCase();
    }
  },
  filters: {
    date(value) {
      return value.substr(0, 10);
    },
    upper(value) {
      return value.toUpperCase();
    },
    money(value) {
      return "$"+value;
    }
  },
  mounted: function() {
    this.getVentas();
    this.updateMonth();
  }
};
</script>

<style>
.v-card__title {
  color: white;
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  line-height: 19px;
}
.v-card__text {
  margin-top: 15px;
  color: white !important;
  opacity: 0.8;
  padding-left: 30px;
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 24px;
}

.tittle_text {
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 20px;
  line-height: 16px;
  opacity: 0.8;
  margin: 10px 0;
}

.v-btn {
  margin: 0 15px;
}

#contableVentas {
  width: 100%;
  border-collapse: collapse;
  vertical-align: middle;
}

#contableVentas tbody > tr,
#contableVentas > thead {
  height: 50px;
  text-align: left;
}
#contableVentas tbody > tr {
  background: white;
}

#contableVentas thead,
#contableVentas tfoot {
  background: var(--gradient-lightblue);
  color: white;
}
#contableVentas tfoot > tr {
  height: 30px;
  font-weight: bold;
}

#contableVentas tr > th:nth-child(1),
#contableVentas tr > td:nth-child(1) {
  padding-left: 8px;
}

#contableVentas > thead,
#contableVentas > tbody > tr,
#contableVentas > tfoot {
  text-align: center;
}
</style>