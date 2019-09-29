<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="mesas" sort-by="categorias" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template  v-slot:activator="{ on }">
            <v-btn dark class="mb-2 gradient-background" v-on="on">Nueva Mesa</v-btn>
          </template>
          <v-card  >
            <v-card-title>
              <span class="headline black--text">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text >
              <v-container >
                <v-row >
                  <v-col cols="12" sm="12" md="12">
                    <v-text-field  v-model="editedItem.mesa" label="Nombre de Mesa"></v-text-field>
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
        text: "Nombre de Mesa",
        align: "center",
        sortable: false,
        value: "mesa"
      },
      { text: "Actions", value: "action", sortable: false }
    ],
    mesas: [],
    editedIndex: -1,
    editedItem: {
      mesa: "",
    },
    defaultItem: {
      mesa: "",
    }
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nueva Mesa" : "Editar Mesa";
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
      this.mesas = [
        {
          mesa: "uno",
        },
        {
          mesa: "dos",
        },
        {
          mesa: "tres",
        },
        {
          mesa: "cuatro",
        },
        {
          mesa: "cinco",
        },
        {
          mesa: "seis",
        },
        {
          mesa: "siete",
        },
        {
          mesa: "ocho",
        },
        {
          mesa: "nueve",
        },
        {
          mesa: "diez",
        }
      ];
    },
    editItem(item) {
      this.editedIndex = this.mesas.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.mesas.indexOf(item);
      confirm("Desea Eliminar la Mesa?") &&
        this.mesas.splice(index, 1);
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
        Object.assign(this.mesas[this.editedIndex], this.editedItem);
      } else {
        this.mesas.push(this.editedItem);
      }
      this.close();
    }
  }
};
</script>