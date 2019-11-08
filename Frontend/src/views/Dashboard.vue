<template>
  <v-container fluid>
    <div class="tittle_text">RESUMEN</div>
    <v-btn dark class="mb-2 gradient-background" @click="nuevaOrden();">Nueva Orden</v-btn>
    <core-login-true :dialog="modal" :path="path"></core-login-true>
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
                <v-btn color="green darken-1" text @click="dialog = false">CANCELAR</v-btn>
                <v-btn color="green darken-1" text @click="cobrarOrden(cobrarIndex)">COBRAR</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="entregado.show" persistent max-width="500">
            <v-card>
              <v-card-title class="headline">
                <v-layout align-center justify-center row fill-height>
                  <v-flex style="text-align: center;color:black;">CUAL ENTREGARA?</v-flex>
                </v-layout>
              </v-card-title>
              <v-layout justify-center fill-height column>
                <v-btn color="red darken-1" text @click="setTimeNull('PP');">PREPARADOS</v-btn>
                <v-btn color="red darken-1" text @click="setTimeNull('NP');">RAPIDOS</v-btn>
              </v-layout>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="entregado.show = false">CANCELAR</v-btn>
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
        <v-data-table :headers="headers" :items="dashboard" :items-per-page="5">
          <template v-slot:item.action="{ item }">
            <v-icon small class="mr-4" @click="editarCuenta(item,'ampliarorden');">add</v-icon>
            <v-icon small class="mr-4" @click="editarCuenta(item,'editarorden');">edit</v-icon>
            <v-icon class="mr-4" @click="ModalCobro(item)">payment</v-icon>
            <v-icon class="mr-4" @click="ModalEntrega(item)">mdi-shaker</v-icon>
          </template>
          <template v-slot:item.tiempoPreparado="{ item }">
            <v-chip :color="item.preparado" dark>{{ item.tiempoPreparado | tiempo }}</v-chip>
          </template>
          <template v-slot:item.tiempoRapido="{ item }">
            <v-chip :color="item.rapido" dark>{{ item.tiempoRapido | tiempo }}</v-chip>
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
      modal: false,
      path: "/",
      entregado: {
        show: false,
        id: null
      },
      cobrarIndex: [],
      pago: 0.00,
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
        { text: "TiempoRapido", value: "tiempoRapido" },
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
    this.getParametros();
    this.store.editando = false;
    console.log(JSON.stringify(this.store));
  },
  computed: {
    ...mapState(["store"])
  },
  components: {
    CoreLoginTrue: () => import("@/components/core/LoginTrue")
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
    nuevaOrden() {
      console.log(this.$store.state.loginPantalla);
      if (this.$store.state.loginPantalla) {
        this.modal = false;
        this.modal = true;
        this.path = "/nuevaorden";
      } else {
        this.modal = false;
        this.$router.push("/nuevaorden");
      }
        rm.postJsonBitacora({
          id:1,
          usuario:{
            id: this.$store.state.IdUsuario,
          }
        });
      // this.$router.push("/nuevaorden");
    },
    getDashboard() {
      rm.getJson("dashboardprincipal")
        .then(r => {
          this.dashboard = r.data;
          console.log(this.dashboard);
        })
        .catch(e => {});
    },
    getParametros(){
      rm.getJson("parametros")
        .then(r => {
          this.store.propina = r.data[13].valor;
          this.store.modoEntorno = r.data[0].valor;
          this.store.imprimirPP = r.data[7].valor;
          this.store.imprimirNP = r.data[8].valor;
          console.log(this.store.propina);
        })
    },
    editarCuenta(cuentaEdit, topath) {
      console.log(cuentaEdit);
      this.store.currentCuenta = this.cuentas.find(
        cuenta => cuenta.cuenta === cuentaEdit.idOrden
      );
      this.store.editando = true;
      if (this.$store.state.loginPantalla) {
        this.modal = false;
        this.modal = true;
        this.path = topath;
      }else{
        this.$router.push(topath);
      }
    },
    ModalCobro(orden) {
      //console.log(JSON.stringify(orden));
      this.dialog = true;
      this.cobrarIndex = orden;
    },
    ModalEntrega(orden) {
      console.log(JSON.stringify(orden));
      this.entregado.show = true;
      this.entregado.id = orden.idOrden;
    },
    cobrarOrden(orden) {
      if (orden.total <= this.pago) {
        this.dialog = false;
        this.store.cuentaTicket = this.cuentas.find(
          cuenta => cuenta.cuenta === orden.idOrden
        );
        this.store.pago = this.pago ;
        console.log(this.store.cuentaTicket);
        rm.postJsonBitacora({
          id:4,
          idOrden:orden.idOrden,
          usuario:{
            id: this.$store.state.IdUsuario,
          }
        });
        
        rm.putJson(`ordenes/finalizar/${orden.idOrden}`, { });

        this.$router.push("/ticket");
      } else {
        this.snackbar = true;
      }
    },
    setTimeNull(value) {
      if (value === "NP") {
        rm.getJson(`ordenes/cerrarorden?type=NP&id=${this.entregado.id}`).then(
          r => {
            console.log(this.entregado.id);
            console.log(r);
            this.getDashboard();
          }
        );
      } else if (value === "PP") {
        rm.getJson(`ordenes/cerrarorden?type=PP&id=${this.entregado.id}`).then(
          () => {
            this.getDashboard();
          }
        );
      }
      this.entregado.show = false;
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
    },
    tiempo: function(value) {
      return typeof value === "undefined"
        ? "Entregado"
        : value.replace(/^00:/g, "");
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