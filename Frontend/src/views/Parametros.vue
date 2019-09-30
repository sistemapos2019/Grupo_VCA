<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="parametros"  class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template  v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on">Agregar Parametro</v-btn>
          </template>
          <v-card  >
            <v-card-title>
              <span class="headline black--text">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text >
              <v-container >
                <v-row >
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field disabled solo v-model="editedItem.nombre" label="Nombre"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field  v-model="editedItem.valor" label="Valor"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions >
              <div class="flex-grow-1" ></div>
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
      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
    </template>
  </v-data-table>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data() {
    return{
    dialog: false,
    parametros: this.getparametros(),
    headers: [
      {text: "Id", sortable: false,value:"id"},
      {
        text: "Nombre Parametro",
        align: "center",
        sortable: false,
        value: "nombre"
      },
      {text: "Valor", sortable: false,value:"valor"},
      { text: "Actions", value: "action", sortable: false }
    ],
    
    editedIndex: -1,
    editedItem: {
      nombre: "",valor:""
    },
    defaultItem: {
      nombre: "",
    }
  };},
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Agregar Parametro" : "Editar Parametro";
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.parametros.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.parametros.indexOf(item);
      confirm("Desea Eliminar el Parámetro?") &&
        this.parametros.splice(index, 1);
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.parametros[this.editedIndex], this.editedItem);
      } else {
        this.parametros.push(this.editedItem);
      }
      this.close();
    },
    getparametros() {
      console.log("no se q pex.............");
      rm.getJson("parametros")
        .then(r => {
          this.parametros = r.data;
        })
        .catch(e => {
          this.parametros = [];
        });
    },

  }
};
</script>