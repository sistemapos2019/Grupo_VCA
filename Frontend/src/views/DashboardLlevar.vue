<template>
  <v-container fluid>
    <div class="tittle_text">ORDENES PARA LLEVAR</div>
    <v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="dashboard" :items-per-page="5">
          <template v-slot:item.action="{ item }">
            <v-icon small class="mr-4" @click="editarCuenta(item);$router.push('ampliarorden');">add</v-icon>
            <v-icon small class="mr-4" @click="editarCuenta(item);$router.push('editarorden');">edit</v-icon>
            <v-icon class="mr-4" @click="ModalCobro(item)">payment</v-icon>
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
      dialog: false,
      cobrarIndex: [],
      pago: null,
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
        { text: "Actions", value: "action", sortable: false }
      ],
      stats: {
        platillos: "Ninguno",
        ordenes: 0,
        pendientes: 0
      }
    };
  },
  created() {
    this.getOrdenes();
    this.getDashboard();
    this.store.editando = false;
    console.log(JSON.stringify(this.store));
  },
  computed: {
    ...mapState(["store"])
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
    },
    editarCuenta(cuentaEdit) {
      console.log(cuentaEdit);
      this.cuentas.forEach(cuenta => {
        if (cuenta.cuenta === cuentaEdit.idOrden) {
          this.store.currentCuenta = cuenta;
        }
      });
      this.store.editando = true;
    },
    ModalCobro(orden) {
      //console.log(JSON.stringify(orden));
      this.dialog = true;
      this.cobrarIndex = orden;
    },
    cobrarOrden(orden) {
      this.$router.push("/ticket");
    },
    getStats() {
      rm.getJson("estadisticas")
        .then(r => {
          let res = r.data;
          delete res.platos;
          res.pendientes = r.headers.count - res.ordenes;

          this.stats = res;
        })
        .catch(e => {
          this.stats = {
            platillos: "",
            ordenes: 0,
            pendientes: 0
          };
        });
      console.log(JSON.stringify(this.diario));
    }
  },
  filters: {
    negativos: function(value) {
      return value < 0 ? 0.0 : value;
    }
  },
  mounted: function() {
    this.getStats();
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