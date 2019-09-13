<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="parametros" sort-by="categorias" class="elevation-1">
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
                    <v-text-field  v-model="editedItem.nombre" label="Nombre"></v-text-field>
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
        value: "nombre"
      },
      {text: "Valor", sortable: false,value:"valor"},
      { text: "Actions", value: "action", sortable: false }
    ],
    parametros: [],
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
      this.parametros = [
        {
          nombre: "Modo Entorno",
          valor:"Mesa",
        },
        {
          nombre: "Nombre ",
          valor:"Negocio la Bendición de Dios",
        },
        {
          nombre: "Descripcion",
          valor:"Servicios de Cafetería y restaurante",
        },
        {
          nombre: "Telefono",
          valor:"(503) 2453-5478",
        },
        {
          nombre: "NIT",
          valor:"0524-045374-102-8",
        },
        {
          nombre: "Giro",
          valor:"Restaurante",
        },
        {
          nombre: "Direccion",
          valor:"BArrio El Calvario calle libertad N23 Santa Ana",
        },
        {
          nombre: "Imprimir Ticket de productos preparados",
          valor:"Si",
        },
        {
          nombre: "Imprimir Ticket de productos no preparados o rapidos",
          valor:"Si",
        },
        {
          nombre: "Tiempo maximo ordenes RAPIDAS (minutos)",
          valor:"4.5",
        },
        {
          nombre: "Tiempo maximo Preparacion Orden",
          valor:"18",
        }
      ];
    },
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
    }
  }
};
</script>