<template>
  <v-data-table :headers="headers" :items="categorias" sort-by="categorias" class="elevation-1" :items-per-page="5">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template  v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on">Nueva Categoria</v-btn>
          </template>
          <v-card  >
            <v-card-title>
              <span class="headline black-text">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text >
              <v-container >
                <v-row >

                  <v-col cols="12" sm="12" md="12">
                    <v-text-field  v-model="editedItem.name" label="Nombre de la categoria"></v-text-field>
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
        text: "Nombre de la Categoria",
        align: "center",
        sortable: false,
        value: "nombre"
      },
      { text: "Actions", value: "action", sortable: false }
    ],
    categorias: [],
    editedIndex: -1,
    editedItem: {
      nombre: "",
    },
    defaultItem: {
      nombre: "",
    }
  }),
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
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.categorias = [
        {
          nombre: "Platos Tipicos",
        },
        {
          nombre: "Helados",
        },
        {
          nombre: "Postres",
        },
        {
          nombre: "desayunos",
        },
        {
          nombre: "Bebidas calientes",
        },
        {
          nombre: "Bebidas frias",
        },
        {
          nombre: "Platos",
        },
        {
          nombre: "Entradas",
        }
      ];
    },
    editItem(item) {
      this.editedIndex = this.categorias.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.categorias.indexOf(item);
      confirm("Desea eliminar la Categoria?") &&
        this.categorias.splice(index, 1);
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
        Object.assign(this.categorias[this.editedIndex], this.editedItem);
      } else {
        this.categorias.push(this.editedItem);
      }
      this.close();
    }
  }
};
</script>