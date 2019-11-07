<template>
  <v-container fluid>
    <div class="tittle_text">ORDENES PARA LLEVAR</div>
    <v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="dashboard" :items-per-page="5">
          <template v-slot:item.tiempoPreparado="{ item }">
            <v-chip :color="item.preparado" dark>{{ item.tiempoPreparado | tiempo }}</v-chip>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import CuentaEntity from "./../utils/CuentaEntity";
import { mapState } from "vuex";
import { isArray } from "util";
const rm = new restMethods();
export default {
  data() {
    return {
      cuentas: [],
      dashboard: [],
      headers: [
        {
          text: "Orden",
          align: "left",
          sortable: false,
          value: "idOrden"
        },
        { text: "Mesa", value: "mesa" },
        { text: "Cliente", value: "cliente" },
        { text: "Mesero", value: "mesero" },
        { text: "TiempoPreparado", value: "tiempoPreparado" },
        { text: "Total", value: "total" },
      ],
    };
  },
  created() {
    this.getOrdenes();
    this.getDashboard();
  },
  methods: {
    getOrdenes() {
      this.cuentas = [];

      rm.getJson("ordenes")
        .then(r => {
          this.cuentas = r.data;
          //console.log(JSON.stringify(this.cuentas));
          this.cuentas = r.data.map(cuenta => {
            return new CuentaEntity(cuenta);
          });
          //console.log(JSON.stringify(this.cuentas));
        })
        .catch(e => {});
    },
    getDashboard() {
      rm.getJson("dashboardllevar")
        .then(r => {
          this.dashboard = r.data;
          console.log(this.dashboard);
        })
        .catch(e => {});
    }
  },
  filters: {
    negativos: function(value) {
      return value < 0 ? 0.0 : value;
    },
    tiempo: function(value) {
      return ( typeof value === "undefined") ? "00:00 min" : value.replace(/^00:/g,'');
    }
  }
};
</script>

<style lang="scss" scoped>
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
</style>