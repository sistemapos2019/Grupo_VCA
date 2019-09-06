<template>
  <v-data-table :headers="headers" :items-per-page="5" :items="desserts" sort-by="clave" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn rounded color="secondary" dark class="mb-2" v-on="on">Nueva Compra</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.date" label="Fecha de la compra"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.ndocumento" label="N° Documento"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nitdui" label="NIT/DUI"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.proveedor" label="Proveedor"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.monto" label="Monto"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.iva" label="IVA"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.percepcion" label="Percepcion"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.total" label="Total"></v-text-field>
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
        text: "fecha de la compra",
        align: "left",
        sortable: false,
        value: "date"
      },
      { text: "N° Documento", value: "ndocumento" },
      { text: "NIT/DUI", value: "nitdui" },
      { text: "Proveedor", value: "proveedor" },
      { text: "Monto", value: "monto" },
      { text: "IVA", value: "iva" },
      { text: "Percepcio", value: "percepcion" },
      { text: "Total", value: "total" },

      { text: "Actions", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      proveedor: "",
      date: "",
      ndocumento: "",
      nitdui: "",
      monto: 0,
      iva: 0,
      percepcion: "",
      total: 0
    },
    defaultItem: {
      proveedor: "",
      date: "",
      ndocumento: "",
      nitdui: "",
      monto: 0,
      iva: 0,
      percepcion: "",
      total: 0
    }
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nueva Compra" : "Editar Compra";
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
          proveedor: "Frozen Yogurt",
          date: "2019-09-01",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Ice cream sandwich",
          date: "2019-09-02",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Eclair",
          date: "2019-09-03",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Cupcake",
          date: "2019-09-04",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Gingerbread",
          date: "2019-09-05",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Jelly bean",
          date: "2019-09-06",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Lollipop",
          date: "2019-09-07",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Honeycomb",
          date: "2019-09-08",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "Donut",
          date: "2019-09-09",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
        },
        {
          proveedor: "KitKat",
          date: "2019-09-10",
          ndocumento: "123422323",
          nitdui: "",
          monto: 0,
          iva: 0,
          percepcion: "",
          total: 0
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