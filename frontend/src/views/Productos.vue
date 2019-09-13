<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="productos" sort-by="categorias" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template  v-slot:activator="{ on }">
            <v-btn rounded color="secondary"  dark class="mb-2" v-on="on">Nuevo Producto</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nombre" label="Nombre del producto"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.categorias" label="Categorias"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.precio" label="Precio"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="blue darken-1" text @click="close">
                <v-icon>mdi-cancel</v-icon>Cancel
              </v-btn>
              <v-btn color="blue darken-1" text @click="save">
                <v-icon>mdi-content-save</v-icon>Save
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
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
  </v-data-table>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    headers: [
      {
        text: "Nombre del Producto",
        align: "left",
        sortable: false,
        value: "nombre"
      },
      { text: "Categoria", value: "categorias" },
      { text: "Precio", value: "precio" },
      { text: "Actions", value: "action", sortable: false }
    ],
    productos: [],
    editedIndex: -1,
    editedItem: {
      nombre: "",
      categorias: "",
      precio: 0
    },
    defaultItem: {
      nombre: "",
      categorias: "",
      precio: 0
    }
  }),
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
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.productos = [
        {
          nombre: "Sushi",
          categorias: "Pasta",
          precio: "$5.00"
        },
        {
          nombre: "pealla",
          categorias: "Platillo",
          precio: "$7.50"
        },
        {
          nombre: "Frozen",
          categorias: "Bebidas Heladas",
          precio: "$1.25"
        },
        {
          nombre: "Carne Azada",
          categorias: "Plato Tipico",
          precio: "$2.50"
        },
        {
          nombre: "Capuchino",
          categorias: "Bebida Caliente",
          precio: "$1.50"
        },
        {
          nombre: "Café",
          categorias: "Bebida Caliente",
          precio: "$1.00"
        },
        {
          nombre: "Comida Mexicana",
          categorias: "platillos",
          precio: "$1.50"
        },
        {
          nombre: "Tres Leches",
          categorias: "Postres",
          precio: "$2.00"
        },
        {
          nombre: "Mariscada en Salsa Rosa",
          categorias: "Mariscos",
          precio: "$7.50"
        },
        {
          nombre: "Pilsener",
          categorias: "Bebida Calientes",
          precio: "$1.50"
        }
      ];
    },
    editItem(item) {
      this.editedIndex = this.productos.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.productos.indexOf(item);
      confirm("Eliminar Producto?") &&
        this.productos.splice(index, 1);
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
    }
  }
};
</script>