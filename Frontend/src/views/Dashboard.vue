<template>
  <v-container fluid>
    <div class="tittle_text">RESUMEN</div>
    <v-btn rounded color="secondary" dark class="mb-2" to="/nuevaorden">Nueva Orden</v-btn>
    <v-row>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039);"
        >
          <v-dialog v-model="dialog" persistent max-width="500">
            <v-card>
              <v-card-title class="headline">
                <v-layout align-center justify-center row fill-height>
                  <v-flex style="text-align: center;">CUENTA N:{{cobrarIndex.cuenta}}</v-flex>
                </v-layout>
              </v-card-title>
              <v-layout justify-center fill-height column>
                <v-layout row align-center justify-center>
                  <v-flex xs5>TOTAL:</v-flex>
                  <v-flex xs5>{{cobrarIndex.total}}</v-flex>
                </v-layout>
                <br />
                <v-layout row align-center justify-center>
                  <v-flex xs5>PAGO:</v-flex>
                  <v-flex xs5>
                    <v-text-field v-model="pago" label="PAGO" single-line></v-text-field>
                  </v-flex>
                </v-layout>
                <br />
                <v-layout row align-center justify-center>
                  <v-flex xs5>CAMBIO:</v-flex>
                  <v-flex xs5>{{(pago - cobrarIndex.total).toFixed(2) | negativos}}</v-flex>
                </v-layout>
              </v-layout>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" flat @click="dialog = false">CANCELAR</v-btn>
                <v-btn color="green darken-1" flat @click="cobrarOrden(cobrarIndex)">COBRAR</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-card-title>Ventas realizadas</v-card-title>
          <v-card-text>{{stats.ordenes}}</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-orange"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Ventas pendientes</v-card-title>
          <v-card-text>{{stats.pendientes}}</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-lightpurple"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Estado</v-card-title>
          <v-card-text>Abierto</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-lightblue"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Platillo mas vendido</v-card-title>
          <v-card-text>{{stats.platillos}}</v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <div class="tittle_text" style="margin-top:20px;">ORDENES</div>
    <v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="cuentas" :items-per-page="5">
          <template v-slot:item.action="{ item }">
            <v-icon small class="mr-4" @click="$router.push('editarorden');">add</v-icon>
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
import { isArray } from 'util';
const rm = new restMethods();
export default {
  data() {
    return {
      dialog: false,
      cobrarIndex: [],
      pago: null,
      cuentas: [],
      headers: [
        {
          text: "Cuenta",
          align: "left",
          sortable: false,
          value: "cuenta"
        },
        { text: "Mesa", value: "mesa" },
        { text: "Cliente", value: "cliente" },
        { text: "Mesero", value: "mesero" },
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
    console.log(JSON.stringify(this.cuentaEditar));
  },
   computed: {
    ...mapState(["cuentaEditar"])

  },
  methods: {
    getOrdenes() {
      this.cuentas = [];

      rm.getJson("ordenes")
        .then(r => {
          this.cuentas = r.data;
          //console.log(JSON.stringify(this.cuentas));
      this.cuentas = r.data.map(cuenta =>{
        return  new CuentaEntity(cuenta);
      })
        //console.log(JSON.stringify(this.cuentas));
      }).catch(e=>{
        });
    },
    editarCuenta(cuentaEdit){
      this.cuentaEditar.currentCuenta = cuentaEdit;
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
    },
  },
  filters: {
    negativos: function(value) {
      return value < 0 ? 0.0 : value;
    }
  },
  mounted: function(){
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