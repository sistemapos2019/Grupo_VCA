<template>
  <material-card>
    <v-data-table :headers="headers" :items="bitacoras" :items-per-page="5">
      <template v-slot:top >
        <v-row>
          <v-col cols="12" md="2">
<!-- item.fecha="{item}" {{getDate(item.fecha)}}
             <v-text-field filled color="blue-grey lighten-2" label="Fecha Desde" v-model="fechaInicio"></v-text-field>-->
        <v-menu ref="startInit" v-model="startInit" :close-on-content-click="false" :nudge-right="40" :return-value.sync="fechaInicio"
          transition="scale-transition" min-width="290px" offset-y full-width>
          <template v-slot:activator="{ on }">
            <v-text-field v-model="fechaInicio" label="Fecha Desde" prepend-icon="event" readonly v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="fechaInicio" no-title scrollable>
            <div class="flex-grow-1"></div>
            <v-btn text color="primary" @click="startInit = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.startInit.save(fechaInicio)">OK</v-btn>
          </v-date-picker>
        </v-menu>
          </v-col>
          <v-col cols="12" md="2">
            <v-menu ref="startEnd" v-model="startEnd" :close-on-content-click="false" :nudge-right="40" :return-value.sync="fechaFin"
          transition="scale-transition" min-width="290px" offset-y full-width>
          <template v-slot:activator="{ on }">
            <v-text-field v-model="fechaFin" label="Fecha Desde" prepend-icon="event" readonly v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="fechaFin" no-title scrollable>
            <div class="flex-grow-1"></div>
            <v-btn text color="primary" @click="startEnd = false">Cancel</v-btn>
            <v-btn text color="primary" @click="$refs.startEnd.save(fechaFin)">OK</v-btn>
          </v-date-picker>
        </v-menu>
          </v-col>
          <v-col>
            <v-btn color="primary" @click="getrangoBitacoras(fechaInicio,fechaFin)">Buscar</v-btn>
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
        value: "fecha",
        dataType:"Date",
      },
      {
        sortable: false,
        text: "Suceso",
        value: "suceso"
      },
      {
        sortable: false,
        text: "Usuario",
        value: "usuario"
      }
    ],
    startInit:false,
    startEnd:false,
    fechaInicio:"",
    fechaFin:"",
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
    getrangoBitacoras(inicio,fin){
      if(inicio!=null && inicio!="" && fin!=null && fin!=""){
        rm.getJson("bitacoras/rango?inicio="+inicio+"&fin="+fin)
      .then(r=>{
        this.bitacoras=r.data;
      })
      .catch(e=>{
        this.bitacoras=[];
      });
      }else{
        console.log("Error");
      }
    },
    deleteItem() {
      confirm("Desea borrar el historial de Bitacoras de sucesos?") &&
        this.bitacoras.splice(0, this.bitacoras.length);
    },
    deleteItemRange() {
      confirm("Desea borrar el historial de Bitacoras del rango?") &&
        this.bitacoras.splice(0, 2);
    }
  },
};
</script>
