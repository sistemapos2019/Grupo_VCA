<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="desserts" sort-by="categorias" class="elevation-1">
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
                    <v-text-field v-model="editedItem.name" label="Nombre del producto"></v-text-field>
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
        value: "name"
      },
      { text: "Categoria", value: "categorias" },
      { text: "Precio", value: "precio" },
      { text: "Actions", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: "",
      categorias: 0,
      precio: 0
    },
    defaultItem: {
      name: "",
      categorias: 0,
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
      this.desserts = [
        {
          name: "Frozen Yogurt",
          categorias: 159,
          precio: 6.0
        },
        {
          name: "Ice cream sandwich",
          categorias: 237,
          precio: 9.0
        },
        {
          name: "Eclair",
          categorias: 262,
          precio: 16.0
        },
        {
          name: "Cupcake",
          categorias: 305,
          precio: 3.7
        },
        {
          name: "Gingerbread",
          categorias: 356,
          precio: 16.0
        },
        {
          name: "Jelly bean",
          categorias: 375,
          precio: 0.0
        },
        {
          name: "Lollipop",
          categorias: 392,
          precio: 0.2
        },
        {
          name: "Honeycomb",
          categorias: 408,
          precio: 3.2
        },
        {
          name: "Donut",
          categorias: 452,
          precio: 25.0
        },
        {
          name: "KitKat",
          categorias: 518,
          precio: 26.0
        }
      ];
    },
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.desserts.splice(index, 1);
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
        Object.assign(this.desserts[this.editedIndex], this.editedItem);
      } else {
        this.desserts.push(this.editedItem);
      }
      this.close();
    }
  }
};
</script>