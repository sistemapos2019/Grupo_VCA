<template>
  <v-container fluid>
    <material-card>
      <v-data-table :headers="headers" :items="bitacoras" :items-per-page="5">
        <template v-slot:top>
          <v-row>
            <v-col cols="12" md="2">
              <!-- item.fecha="{item}" {{getDate(item.fecha)}}
              <v-text-field filled color="blue-grey lighten-2" label="Fecha Desde" v-model="fechaInicio"></v-text-field>-->
              <v-menu
                ref="startInit"
                v-model="startInit"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="fechaInicio"
                transition="scale-transition"
                min-width="290px"
                offset-y
                full-width
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="fechaInicio"
                    label="Fecha Desde"
                    prepend-icon="event"
                    readonly
                    v-on="on"
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
              <v-menu
                ref="startEnd"
                v-model="startEnd"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="fechaFin"
                transition="scale-transition"
                min-width="290px"
                offset-y
                full-width
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="fechaFin"
                    label="Fecha Hasta"
                    prepend-icon="event"
                    readonly
                    v-on="on"
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
              <v-btn class="gradient-background" dark @click="getrangoBitacoras(fechaInicio,fechaFin)">Buscar</v-btn>
            </v-col>
          </v-row>
          <!-- asdasdas-->
          <template>
          <v-toolbar flat color="white">
          <v-dialog  v-model="deleteB" max-width="500px">
       
            <v-card>
              <v-card-title>
                <span class="headline black--text">{{ formTitle }}</span>
              </v-card-title>
            <v-card-actions >
              <div class="flex-grow-1" ></div>
              <v-btn color="#504da3" text @click="deleteB=false">
                <v-icon>mdi-cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="#504da3" text @click="deleteItem">
                <v-icon>mdi-content-save</v-icon>Eliminar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
      </template>
      <!-- asdas-->
        </template>
 
        

       
      </v-data-table>
      <div class="text-center">
        <v-btn
          dark
          class="gradient-background-orange"
          @click="deleteItemRange(1)"
        >Borrar bitacoras del rango</v-btn>
        <v-btn
          dark
          class="gradient-background-orange"
          style="margin: 0 40px;"
          @click="deleteItemRange(0)"
        >Borrar todas las Bitacoras</v-btn>
      </div>
    </material-card>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data() {
    return {
      headers: [
        {
          sortable: false,
          text: "Fecha  Hora",
          value: "fecha",
          dataType: "Date"
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
      startInit: false,
      startEnd: false,
      fechaInicio: "",
      fechaFin: "",
      Eliminar:0,
      deleteB:false,
      bitacoras: this.getbitacoras()
    };
  },
  computed: {
    formTitle() {
      return this.Eliminar === -1 ? "Se Eliminaran las Bitacoras del Rango" : "Se Eliminaran Todas las Bitacoras";
    }
  },
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
    getrangoBitacoras(inicio, fin) {
      if (inicio != null && inicio != "" && fin != null && fin != "") {
        rm.getJson("bitacoras/rango?inicio=" + inicio + "&fin=" + fin)
          .then(r => {
            this.bitacoras = r.data;
          })
          .catch(e => {
            this.bitacoras = [];
          });
      } else {
        console.log("Error");
      }
    },
    deleteItem() {
      let source="bitacoras/delete?tipo=";
      if(this.Eliminar===-1){
        if(this.fechaInicio!="" && this.fechaFin!=""){
          rm.deleteJson(source,1+"&inicio="+this.fechaInicio+"&fin="+this.fechaFin);
          this.deleteB=false;
        }else{
          confirm("Ingresar Fechas del rango")
          this.deleteB=false;
          console.log("error")
        }
      }else{
        rm.deleteJson(source,0);
        this.deleteB=false;
      }
    },
    deleteItemRange(val) {
      this.deleteB=true;
      val?this.Eliminar=-1:this.Eliminar=0;
    }
  }
};
</script>
