<template>
  <v-data-table
    :headers="headers"
    :items="categorias"
    sort-by="categorias"
    class="elevation-1"
    :items-per-page="5"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on" @click="crear()">Nueva Categoria</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline black-text">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="12" md="12">
                    <v-text-field v-model="categoria.nombre" label="Nombre de la categoria"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="#504da3" text @click="close">
                <v-icon>mdi-cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="#504da3" text @click="ejecutar(categoria.nombre)">
                <v-icon>mdi-content-save</v-icon>Guardar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.action="{ item }">
      <v-icon small class="mr-2" @click="editar(item)">mdi-pencil</v-icon>
      <!--      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>-->
    </template>
  </v-data-table>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data() {
    return {
      dialog: false,
      nuevo: false,
      categorias: this.getcategorias(),
      headers: [
        {
          text: "Nombre de la Categoria",
          align: "center",
          sortable: false,
          value: "nombre"
        },
        { text: "Actions", value: "action", sortable: false }
      ],
      editedIndex: -1,
      categoria: {
        id: "",
        nombre: ""
      },
      defaultItem: {
        nombre: ""
      }
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nueva Categoria" : "Editar Categoria";
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    /*deleteItem(item) {
      const index = this.categorias.indexOf(item);
      confirm("Desea eliminar la Categoria?") &&
        this.categorias.splice(index, 1);
    },*/
    editar(item) {
      this.editedIndex = 0;
      this.nuevo = false;
      this.categoria = item;
      this.dialog = true;
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    getcategorias() {
      rm.getJson("categorias")
        .then(r => {
          this.categorias = r.data;
        })
        .catch(e => {
          this.categorias = [];
        });
      console.log(JSON.stringify(this.categorias));
    },
    ejecutar(categoriaEdit) {
      if (categoriaEdit != null && this.nuevo && !this.categoria.id) {
        this.categoria.nombre = categoriaEdit;
        console.log(JSON.stringify(this.categoria));
        rm.postJson("categorias", {
          id: this.categoria.id,
          nombre: this.categoria.nombre
        }).then(()=>{
          this.getcategorias();
        });
      } else if (categoriaEdit != null && !this.nuevo && this.categoria.id) {
        console.log("edit" + JSON.stringify(this.categoria));
        this.categoria.nombre = categoriaEdit;
        rm.putJson("categorias/" + parseInt(this.categoria.id), {
          id: this.categoria.id,
          nombre: this.categoria.nombre
        }).then(()=>{
          this.getcategorias();
        });
      }
      this.dialog = false;
      this.categoria.id = null;
      this.categoria.nombre = null;

    },
    crear() {
      this.categoria.nombre = null;
      this.nuevo = true;
      this.categoria.id = null;
      this.dialog = true;
      console.log("Entro al crear");
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.categorias[this.editedIndex], this.editedItem);
      } else {
        this.categorias.push(this.editedItem);
      }
      this.close();
    }
  }
};
</script>