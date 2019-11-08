<template>
  <v-container fluid>
    <v-data-table
      :headers="headers"
      :items-per-page="5"
      :items="productos"
      sort-by="categorias"
      class="elevation-1"
    >
      <template v-slot:item.preparado="{ item }">{{ item.preparado?'SI':'NO' }}</template>
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn dark class="mb-2 gradient-background" v-on="on" @click="crear()">Nuevo Producto</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline black-text">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="producto.nombre" label="Nombre del producto"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="producto.precio" label="Precio del producto"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="producto.inventario" label="Existencia"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-select
                        :items="[{preparado:1,val:'SI' },{preparado:0,val:'NO' }]"
                        item-text="val"
                        item-value="preparado"
                        v-model="producto.preparado"
                        label="Preparado"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-select
                        :items="categorias"
                        item-value="id"
                        item-text="nombre"
                        v-model="producto.idCategoria.id"
                        label="Categorias"
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="#504da3" text @click="close">
                <v-icon>mdi-cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="#504da3" text @click="guardar()">
                <v-icon>mdi-content-save</v-icon>Guardar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.action="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
     <!-- <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>-->
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
      bitacora:null,
      headers: [
        {
          text: "Nombre del Producto",
          align: "left",
          sortable: false,
          value: "nombre"
        },
        { text: "Categoria", value: "idCategoria.nombre" },
        { text: "Precio", value: "precio" },
        { text: "Existencias", value: "inventario" },
        { text: "Preparado", value: "preparado" },
        { text: "Actions", value: "action", sortable: false }
      ],
      productos: [],
      categorias: this.getcategorias(),
      editedIndex: -1,
      producto: {
        id: "",
        nombre: "",
        idCategoria: {
          id: "",
          nombre: ""
        },
        precio: 0,
        inventario: "",
        preparado: ""
      },
      defaultItem: {
        nombre: "",
        categorias: "",
        precio: 0,
        inventario: "",
        preparado: ""
      }
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Producto" : "Editar Producto";
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    getproductos() {
      rm.getJson("productos")
        .then(r => {
          this.productos = r.data;
        })
        .catch(e => {
          this.productos = [];
        });
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
    editItem(item) {
      this.editedIndex = 0;
      this.dialog = true;
      this.producto.id = item.id;
      this.producto.preparado = item.preparado;
      this.producto.nombre = item.nombre;
      this.producto.inventario = item.inventario;
      this.producto.precio = item.precio;
      this.producto.idCategoria.id = item.idCategoria.id;
      this.producto.idCategoria.nombre = item.idCategoria.nombre;
    },
    crear() {
      this.productoItem();
      this.editedIndex = -1;
    },
    guardar() {
      if (this.editedIndex != -1) {
        rm.putJson("productos/" + this.producto.id, {
          id: this.producto.id,
          nombre: this.producto.nombre,
          idCategoria: { id: this.producto.idCategoria.id },
          inventario: this.producto.inventario,
          precio: this.producto.precio,
          preparado: this.producto.preparado
        }).then(()=>{
          this.getproductos();
        });
        this.bitacora=13;
        console.log("Editar" + JSON.stringify(this.producto));
      } else {
        rm.postJson("productos", {
          id: null,
          nombre: this.producto.nombre,
          idCategoria: { id: this.producto.idCategoria.id },
          inventario: this.producto.inventario,
          precio: this.producto.precio,
          preparado: this.producto.preparado
        }).then(()=>{
          this.getproductos();
        });
        this.bitacora=15;
        console.log("Crear " + JSON.stringify(this.producto));
      }
  rm.postJsonBitacora({
    id:this.bitacora,
    idProducto: this.producto.id,
    usuario:{
      id: this.$store.state.IdUsuario,
    }
  });

      this.productoItem();

      this.close();
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
        Object.assign(this.productos[this.editedIndex], this.editedItem);
      } else {
        this.productos.push(this.editedItem);
      }
      this.close();
    },
    productoItem() {
      this.producto.id = null;
      this.producto.nombre = null;
      this.producto.idCategoria.id = null;
      this.producto.idCategoria.nombre = null;
      this.producto.precio = null;
      this.producto.inventario = null;
      this.producto.preparado = null;
    }
  },
  mounted: function(){
    this.getproductos();
  }
};
</script>