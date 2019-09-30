<template>
  <material-card>
    <v-data-table :headers="headers" :items="bitacoras" :items-per-page="5">
      <template v-slot:top>
        <v-row>
          <v-col cols="12" md="2">
            <v-text-field filled color="blue-grey lighten-2" label="Fecha Desde"></v-text-field>
          </v-col>
          <v-col cols="12" md="2">
            <v-text-field filled color="blue-grey lighten-2" label="Fecha Hasta"></v-text-field>
          </v-col>
        </v-row>
      </template>
    </v-data-table>
    <div class="text-center">
      <v-btn
        dark
        class="gradient-background-orange"
        @click="deleteItemRange"
      >Borrar bitacoras del rango</v-btn>
      <v-btn dark class="gradient-background-orange" style="margin: 0 40px;" @click="deleteItem">Borrar todas las Bitacoras</v-btn>
    </div>
  </material-card>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data () {
    return {
    headers: [
      {
        sortable: false,
        text: "Fecha  Hora",
        value: "fecha"
      },
      {
        sortable: false,
        text: "Suceso",
        value: "suceso"
      },
      {
        sortable: false,
        text: "Usuario",
        value: "idUsuario.nombreCompleto"
      }
    ],
    bitacoras: this.getbitacoras(),
  };},
  methods: {
    getbitacoras() {
      rm.getJson("bitacoras")
        .then(r => {
          this.bitacoras = r.data;
        })
        .catch(e => {
          this.bitacoras = [];
        });
    },
    deleteItem() {
      confirm("Desea borrar el historial de Bitacoras de sucesos?") &&
        this.bitacoras.splice(0, this.bitacoras.length);
    },
    deleteItemRange() {
      confirm("Desea borrar el historial de Bitacoras del rango?") &&
        this.bitacoras.splice(0, 2);
    }
  }
};
</script>
