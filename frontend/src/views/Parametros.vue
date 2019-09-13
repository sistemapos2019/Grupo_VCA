<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="desserts" sort-by="categorias" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template  v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on">Agregar Parametro</v-btn>
          </template>
          <v-card  >
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text >
              <v-container >
                <v-row >
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field  v-model="editedItem.name" label="Nombre"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field  v-model="editedItem.valor" label="Valor"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions >
              <div class="flex-grow-1" ></div>
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
        text: "Nombre Parametro",
        align: "center",
        sortable: false,
        value: "name"
      },
      {text: "Valor", sortable: false,value:"valor"},
      { text: "Actions", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: "",
    },
    defaultItem: {
      name: "",
    }
  }),
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
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.desserts = [
        {
          name: "Modo Entorno",
          valor:"Si",
        },
        {
          name: "Nombre ",
          valor:"SisBarv.2",
        },
        {
          name: "Descripcion",
          valor:"Interfaces no funcionales",
        },
        {
          name: "Cupcake",
          valor:"SisBarv.2",
        },
        {
          name: "Gingerbread",
          valor:"SisBarv.2",
        },
        {
          name: "Jelly bean",
          valor:"SisBarv.2",
        },
        {
          name: "Lollipop",
          valor:"SisBarv.2",
        },
        {
          name: "Honeycomb",
          valor:"SisBarv.2",
        },
        {
          name: "Donut",
          valor:"SisBarv.2",
        },
        {
          name: "KitKat",
          valor:"SisBarv.2",
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