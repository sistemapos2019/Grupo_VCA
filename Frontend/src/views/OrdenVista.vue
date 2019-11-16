<template>
  <v-container fluid>
    <v-layout>
      <v-dialog v-model="dialog" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">
            <v-layout align-center justify-center row fill-height>
              <v-flex style="text-align: center;">COBRAR ORDEN</v-flex>
            </v-layout>
          </v-card-title>
          <v-layout justify-center fill-height column>
            <v-layout row align-center justify-center>
              <v-flex xs6>TOTAL:</v-flex>
              <v-flex xs6>{{cobrarIndex.total}}</v-flex>
            </v-layout>
            <br />
            <v-layout row align-center justify-center>
              <v-flex xs6>PAGO:</v-flex>
              <v-flex xs6>
                <v-text-field v-model="pago" label="PAGO" single-line></v-text-field>
              </v-flex>
            </v-layout>
            <br />
            <v-layout row align-center justify-center>
              <v-flex xs6>CAMBIO:</v-flex>
              <v-flex xs6>{{(pago - cobrarIndex.total).toFixed(2) | negativos}}</v-flex>
            </v-layout>
          </v-layout>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="dialog = false">CANCELAR</v-btn>
            <v-btn color="green darken-1" text @click="cobrarOrden(cobrarIndex)">COBRAR</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-snackbar v-model="snackbar" :timeout="1000">INGRESE DATOS.</v-snackbar>

      <v-flex xs12 offset-xs1>
        <v-card class="mx-auto" height="95px">
          <v-card-text>
            <v-layout row wrap>
              <v-flex xs6>&nbsp;&nbsp;&nbsp;ORDEN: {{detalles.cuenta}}</v-flex>
              <v-flex xs6>MESA: {{detalles.mesa}}</v-flex>
              <v-flex xs6>&nbsp;&nbsp;&nbsp;MESERO: {{detalles.mesero}}</v-flex>
              <v-flex xs6>CLIENTE: {{detalles.cliente}}</v-flex>
            </v-layout>
          </v-card-text>
        </v-card>
        <v-data-table
          :headers="headers"
          :items="productos"
          hide-default-footer
          :search="search"
          :page.sync="page"
          :items-per-page="itemsperpage"
          @page-count="pageCount = $event"
          class="elevation-1"
        >
          <template v-slot:items="productos">
            <td>{{ productos.item.cantidad }}</td>
            <td>{{ productos.item.producto }}</td>
            <td>${{ productos.item.precio }}</td>
            <td>${{ (productos.item.precio * productos.item.cantidad).toFixed(2) }}</td>
          </template>
          <template v-slot:no-data>
            <v-alert :value="true" icon="warning">Aun no elije productos</v-alert>
          </template>
        </v-data-table>
        <v-layout row align-center justify-center>
          <v-pagination v-model="page" :length="pageCount"></v-pagination>
        </v-layout>

        <v-layout row wrap justify-center align-center>
          <v-flex xs9>
            <v-btn text @click="guardar()">GUARDAR</v-btn>
          </v-flex>
          <v-flex xs3 class="text-xs-center">
            <b>Total ${{total()}}</b>
          </v-flex>
          <v-flex xs12>
            <v-text-field v-model="detalles.observacion" label="Observaciones" single-line></v-text-field>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import OrdenEntity from "./../utils/OrdenEntity";
import restMethods from "../utils/restMethods.js";
const rest = new restMethods();
export default {
  data() {
    return {
      headers: [
        { text: "Cantidad", sortable: false, value: "cantidad" },
        { text: "Producto", sortable: false, value: "producto" },
        { text: "Precio", sortable: false, value: "precio" },
        { text: "Subtotal", sortable: false, value: "subtotal" }
      ],
      search: "",
      itemsperpage: 6,
      page: 1,
      pageCount: 0,
      selected: [],
      dialog: false,
      pago: null,
      snackbar: false,
      cobrarIndex: [],
      productosExtra: []
    };
  },
  methods: {
    total() {
      var resultado = 0;
      for (var item in this.productos) {
        resultado +=
          this.productos[item].precio * this.productos[item].cantidad;
      }
      this.detalles.total = resultado.toFixed(2);
      return resultado;
    },
    guardar() {
      if (this.detalles !== null && this.productos.length > 0) {
      this.detalles.mesero = this.$store.state.IdUsuario;

        this.productosExtra = JSON.parse(JSON.stringify(this.productos));
        if (this.store.ampliando) {
           this.store.editando = false;
          this.sumarizarProductos();
        }
        let orden = new OrdenEntity(this.detalles, this.productos, this.store.propina);
        console.log(this.productosExtra);

         
         this.store.cuentaTicket = this.detalles;
        
        if (this.store.editando) {
         rest.putJson(`ordenes`, orden);
         this.$router.push("/dashboardprincipal");
        } else if(this.store.ampliando){

          this.store.ampliando = false;
         this.detalles.resumen = this.productosExtra;
          rest.putJson(`ordenes`, orden).then(response => this.detalles.cuenta = response.data);
          console.log("algo safdshsedtfsdhgsrfs")
           this.$router.push("/ticketcocina");

        } else{
          this.detalles.resumen = this.productos;
          rest.postJson(`ordenes`, orden).then(response => this.detalles.cuenta = response.data);
           this.$router.push("/ticketcocina");
        }

        this.store.alert = true;
      } else {
        this.snackbar = true;
      }
    },
    sumarizarProductos() {
      this.detalles.resumen.forEach(current => {
        //se suman las cantidades de los productos viejos con los nuevos
        this.productos.forEach(nuevo => {
          if (nuevo.id === current.id) {
            nuevo.cantidad += current.cantidad;
          }
        });
        //Se agregan los productos existentes a la nueva lista de productos, primero se agregan todos los productos diferentes
        if (this.productos.every(producto => producto.id !== current.id)) {
          this.productos.push(current);
        }
      });
    }
  },
  computed: {
    ...mapState(["cuentas", "store", "cuentaTicket"])
  },
  filters: {
    negativos: function(value) {
      return value < 0 ? 0.0 : value;
    }
  },
  props: ["productos", "detalles", "tamanio"]
};
</script>

