<template>
  <v-data-table
    :headers="headers"
    :items="desserts"
    sort-by="categorias"
    class="elevation-1"
    :items-per-page="5"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on">Agregar al inventario</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline" style="color: black;">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.id" label="ID"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nombre" label="Nombre del producto"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.descripcion" label="Descipcion del producto"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.cantidad" label="Cantidad"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="blue darken-4" text @click="close">
                <v-icon>mdi-cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="blue darken-4" text @click="save">
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
        text: "ID",
        align: "center",
        sortable: false,
        value: "id"
      },
      {
        text: "Nombre",
        align: "center",
        sortable: false,
        value: "nombre"
      },
      {
        text: "Descripcion",
        align: "center",
        sortable: false,
        value: "descripcion"
      },
      {
        text: "Cantidad",
        align: "center",
        sortable: false,
        value: "cantidad"
      },
      { text: "Actions", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      id: null,
      nombre: "",
      descripcion: "",
      cantidad: 0,
    },
    defaultItem: {
      id: null,
      nombre: "",
      descripcion: "",
      cantidad: 0,
    }
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Agregar inventario" : "Editar inventario";
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
          id: 1,
          nombre: "Coca Cola",
          descripcion: "Bebida carbonatada 500ml",
          cantidad: 200
        },
        {
          id: 2,
          nombre: "Fanta",
          descripcion: "Bebida carbonatada 500ml",
          cantidad: 100
        },
        {
          id: 3,
          nombre: "Pepsi",
          descripcion: "Bebida carbonatada 500ml",
          cantidad: 45
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
      confirm("Estas seguro de borrar esto?") && this.desserts.splice(index, 1);
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