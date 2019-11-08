<template>
  <v-container fluid>
    <v-data-table :headers="headers" :items-per-page="5" :items="parametros" class="elevation-1">
      <template v-slot:item.valor="{ item }">{{item.valor | getValor(item.id) }}</template>
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="headline black--text">Editar Parametro</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4" lg="12">
                      <p class="title" style="color:black;">{{parametro.nombre}}</p>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6" md="4" lg="12">
                      <v-text-field v-model="parametro.valor" label="Valor"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="#504da3" text @click="close">
                  <v-icon>mdi-cancel</v-icon>Cancelar
                </v-btn>
                <v-btn color="#504da3" text @click="save">
                  <v-icon>mdi-content-save</v-icon>Guardar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data() {
    return {
      dialog: false,
      parametros: this.getparametros(),
      headers: [
        { text: "Id", sortable: false, value: "id" },
        {
          text: "Nombre Parametro",
          align: "center",
          sortable: false,
          value: "nombre"
        },
        { text: "Valor", sortable: false, value: "valor" },
        { text: "Actions", value: "action", sortable: false }
      ],

      editedIndex: -1,
      parametro: {
        id: "",
        nombre: "",
        valor: ""
      },
      defaultItem: {
        nombre: ""
      }
    };
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    editItem(item) {
      this.dialog = true;
      (this.parametro.id = item.id),
        (this.parametro.nombre = item.nombre),
        (this.parametro.valor = item.valor);
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.parametro = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    async save() {
      await rm.putJson("parametros/" + parseInt(this.parametro.id), {
        id: this.parametro.id,
        nombre: this.parametro.nombre,
        valor: this.parametro.valor
      }).then(()=>{
          this.getparametros();
        });
      if (this.parametro.id == 12) {
        let ver;
        await rm
          .getJson("parametros/" + parseInt("12"))
          .then(r => {
            ver = r.data.valor;
            console.log(ver);
          })
          .catch(c => {
            ver = false;
          });
        this.$store.state.pantallaVista = ver;
      }
      rm.postJsonBitacora({
          id:16,
          idParametro: this.parametro.id,
          usuario:{
            id: this.$store.state.IdUsuario,
          }
        }).then(r=>{
          console.log(r.data);
        });

      this.close();
    },
    getparametros() {
      rm.getJson("parametros")
        .then(r => {
          this.parametros = r.data;
        })
        .catch(e => {
          this.parametros = [];
        });
    }
  },
  filters: {
    getValor: function(value, id) {
      if (id == 10 || id == 11) {
        let num = (value * 100 - ((value * 100) % 100)) / 100;
        let otro = (((value * 100) % 100) / 100) * 60;
        otro < 10 ? (otro = "0" + otro) : "";
        num < 10 ? (num = "0" + num) : "";
        return String(num + ":" + otro + " min ");
      } else if (id == 12) {
        return value == 1 ? "SI" : "NO";
      }
      return value;
    }
  }
};
</script>