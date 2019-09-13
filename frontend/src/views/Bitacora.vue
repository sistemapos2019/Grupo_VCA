<template>
  <material-card>
    <v-data-table :headers="headers" :items="bitacora" :items-per-page="5">
      <template v-slot:top>
        <v-row>
          <v-col cols="12" md="2">
            <v-text-field filled color="blue-grey lighten-2" label="Fecha Desde"></v-text-field>
          </v-col>
          <v-col cols="12" md="2">
            <v-text-field filled color="blue-grey lighten-2" label="Fecha Hasta"></v-text-field>
          </v-col>
        </v-row>
      </template>
    </v-data-table>
    <div class="text-center">
      <v-btn
        dark
        class="gradient-background-orange"
        @click="deleteItemRange"
      >Borrar bitacoras del rango</v-btn>
      <v-btn dark class="gradient-background-orange" style="margin: 0 40px;" @click="deleteItem">Borrar todas las Bitacoras</v-btn>
    </div>
  </material-card>
</template>

<script>
export default {
  data: () => ({
    headers: [
      {
        sortable: false,
        text: "Fecha  Hora",
        value: "fecha"
      },
      {
        sortable: false,
        text: "Suceso",
        value: "suceso"
      },
      {
        sortable: false,
        text: "Usuario",
        value: "usuario"
      }
    ],
    bitacora: []
  }),
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.bitacora = [
        {
          fecha: '2019-09-09 14:50',
          suceso: 'Agrego productos a la orden N° 22',
          usuario: 'Ranee Carlson',
        },
        {
          fecha: '2019-09-09  13:25',
          suceso: 'Agrego una nueva orden',
          usuario: 'Ranee Carlson',
        }, 
        {
          fecha: '2019-09-09  11:36',
          suceso: 'Finalizo orden N°1',
          usuario: 'Casandra Petronila',
        }, 
        {
          fecha: '2019-09-09  10:05',
          suceso: 'Cobro la Orden N° 3',
          usuario: 'Cindy Baker',
        }, 
        {
          fecha: '2019-09-08 15:20',
          suceso: 'Ingreso al sistema',
          usuario: 'Casandra Petronila',
        }, 
        {
          fecha: '2019-09-08  13:23',
          suceso: 'Finalizo la secion en el sistema',
          usuario: 'Ranee Carlson',
        }
      ];
    },
    deleteItem() {
      confirm("Desea borrar el historial de Bitacoras de sucesos?") &&
        this.bitacora.splice(0, this.bitacora.length);
    },
    deleteItemRange() {
      confirm("Desea borrar el historial de Bitacoras del rango?") &&
        this.bitacora.splice(0, 2);
    }
  }
};
</script>
