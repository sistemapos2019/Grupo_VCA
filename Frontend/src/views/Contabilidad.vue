<template>
  <v-container fluid>
    <v-row>
      <v-col class="tittle_text text-center display-1">LIBRO DE COMPRAS</v-col>
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
              v-model="date"
              label="Filtrado por fechas"
              prepend-icon="event"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            color="blue"
            v-model="date"
            no-title
            scrollable
            @change="getCompras(); $refs.menu.save(date);"
          ></v-date-picker>
        </v-menu>
      </v-col>
      <v-spacer></v-spacer>
      <v-col lg="2">
        <v-btn
          @click="generarReporte()"
          style="background: linear-gradient(323.35deg, #F7C598 6.2%, #FF8886 97.74%); color:white;"
        >GENERAR REPORTE</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col lg="12">
        <table id="contable">
          <thead>
            <tr>
              <th colspan="6">NOMBRE CONTRIBUYENTE: {{contribuyente}}</th>
              <th colspan="3">NRC: </th>
              <th colspan="2">MES: {{new Date(date).toLocaleString('default', { month: 'long' })}}</th>
              <th colspan="1">AÑO: {{new Date(date).getFullYear()}}</th>
            </tr>
            <tr>
              <td rowspan="2">N</td>
              <td rowspan="2">Fecha</td>
              <td rowspan="2">Numero documento</td>
              <td rowspan="2">NRC</td>
              <td rowspan="2">NIT/DUI sujeto</td>
              <td rowspan="2">Nombre proveedor</td>
              <td colspan="3" style="text-align: center;">Compras gravadas</td>
              <td rowspan="2">IVA</td>
              <td rowspan="2">Total compras</td>
              <td rowspan="2">Compras excluidas</td>
            </tr>
            <tr>
              <td>Internas</td>
              <td>Importaciones Internacionales</td>
              <td>Credito fiscal</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in compras" :key="item.id">
              <td>{{item.id}}</td>
              <td>{{item.fecha | date }}</td>
              <td>{{item.ndocumento}}</td>
              <td>{{item.nrc}}</td>
              <td>{{item.nitdui}}</td>
              <td>{{item.nombreProveedor}}</td>
              <td>{{item.montoInterno}}</td>
              <td></td>
              <td></td>
              <td>{{item.iva}}</td>
              <td style="text-align: center;">{{item.total}}</td>
              <td></td>
            </tr>
            
          </tbody>
          <tfoot>
            <tr>
              <td colspan="6">TOTALES</td>
              <td>{{totales.interno}}</td>
              <td></td>
              <td></td>
              <td>{{totales.iva}}</td>
              <td style="text-align: center;">{{totales.general}}</td>
              <td></td>
            </tr>
          </tfoot>
        </table>
      </v-col>
    </v-row>

    <!--v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="items" :items-per-page="5"></v-data-table>
      </v-col>
      <v-col cols="12" lg="3">
        <v-btn style="background: linear-gradient(146.29deg, #44DEC5 9.19%, #4EBCFA 100%); color:white;" >VENTAS</v-btn>
        <v-btn style="background: linear-gradient(146.29deg, #44DEC5 9.19%, #4EBCFA 100%); color:white;" >COMPRAS</v-btn>
      </v-col>
      <v-col cols="12" lg="3">
        <v-text-field placeholder="Ingrese la fecha"></v-text-field>
      </v-col>
    </v-row-->
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
      compras: {},
      menu: false,
      date: new Date().toISOString().substr(0, 10),
      contribuyente: "",
      totales: {
        iva: 0.0,
        interno: 0.0,
        general: 0.0
      }
    };
  },
  methods: {
    getCompras() {
      rm.getJson(`compras?date=${this.date}`)
        .then(r => {
          this.compras = r.data;
          this.contribuyente = r.headers.contribuyente;
          this.totales.iva = r.headers["total-iva"];
          this.totales.general = r.headers["total-general"];
          this.totales.interno = r.headers["total-monto"];
        })
        .catch(e => {});
    },
    generarReporte() {
      const doc = new jsPDF("l", "mm", "a4");
      doc.setFontSize(40);
      doc.text(80, 25, "LIBRO DE COMPRAS");
      doc.autoTable({
        html: "table#contable",
        theme: "grid",
        startY: 50,
        styles: {
          overflow: "linebreak",
          fontSize: 8,
          valign: "middle",
        },
        didParseCell: (HookData) => {
          if(((HookData.cell != undefined) ? HookData.cell.text : "")=="Compras gravadas"){
            HookData.cell.styles.halign= 'center';
          }
        }
      });
      //doc.save("table.pdf");
      doc.output("dataurlnewwindow");
    }
  },
  filters: {
    date(value) {
      return value.substr(0, 10);
    }
  },
  mounted: function() {
    this.getCompras();
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

#contable {
  width: 100%;
  border-collapse: collapse;
}

#contable tbody > tr ,
#contable > thead{
  height: 50px;
  text-align: left;
}

#contable thead,
#contable tfoot{
  background: linear-gradient(146.29deg, #44DEC5 9.19%, #4EBCFA 100%);
  color: white;
}
#contable tfoot > tr{
  height: 30px;
}

#contable > thead > tr:nth-child(1) > th:nth-child(1),
#contable > thead > tr:nth-child(2) > td:nth-child(1),
#contable > tfoot > tr > td:nth-child(1){
  padding-left: 10px;
}
</style>