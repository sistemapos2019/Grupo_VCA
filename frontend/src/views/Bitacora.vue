<template>
         <material-card>
            <v-data-table :headers="headers" :items="bitacora"   :items-per-page="5">
            <template v-slot:top>
            <v-row>
              <v-col cols="12" md="2">
             <v-text-field  filled
              color="blue-grey lighten-2" label="Fecha Desde" ></v-text-field> 
            </v-col>
            <v-col  cols="12" md="2">
             <v-text-field  filled
              color="blue-grey lighten-2" label="Fecha Hasta" ></v-text-field> 
            </v-col>
            </v-row>
            </template>

          </v-data-table>
          <div class="text-center">
            <v-btn color="success" @click="deleteItemRange">Borrar bitacoras del rango</v-btn>
            <v-btn color="success" @click="deleteItem">Borrar todas las Bitacoras</v-btn>
          
          </div>
          
        </material-card>
          
</template>

<script>
  export default {
    data: () => ({
      headers: [
        {
          sortable: false,
          text: 'Fecha  Hora',
          value: 'fecha'
        },
        {     
          sortable: false,
          text: 'Suceso',
          value: 'suceso'
        },
        {
          sortable: false,
          text: 'Usuario',
          value: 'usuario'
        },
      ],
      bitacora: [],}),
 created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.bitacora = [
        {
          fecha: '2019-09-09',
          suceso: 'Niger',
          usuario: 'Oud-Tunrhout',
        },
        {
          fecha: '2019-09-09',
          suceso: 'Curaçao',
          usuario: 'Sinaai-Waas',
        }, 
        {
          fecha: '2019-09-09',
          suceso: 'Netherlands',
          usuario: 'Overland Park',
        }, 
        {
          fecha: '2019-09-09',
          suceso: 'Korea, South',
          usuario: 'Gloucester',
        }, 
        {
          fecha: '2019-09-09',
          suceso: 'Malawi',
          usuario: 'Feldkirchen in Kārnten',
        }, 
        {
          fecha: '2019-09-09',
          suceso: 'Chile',
          usuario: 'Gloucester',
        }
      ];
    },
      deleteItem() {  
      confirm("Desea borrar el historial de Bitacoras de sucesos?") &&
        this.bitacora.splice(0,this.bitacora.length);
    },
    deleteItemRange() {  
      confirm("Desea borrar el historial de Bitacoras del rango?") &&
        this.bitacora.splice(0,2);
    },
      },
  }
</script>
