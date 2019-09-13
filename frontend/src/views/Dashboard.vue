<template>
  <v-container fluid>
    <div class="tittle_text">RESUMEN</div>
    <v-btn rounded color="secondary" dark class="mb-2" to="/nuevaorden">Nueva Orden</v-btn>
    <v-row>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039);"
        >
          <v-card-title>Ventas realizadas</v-card-title>
          <v-card-text>34 ventas</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-orange"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Ventas pendientes</v-card-title>
          <v-card-text>10 ventas</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-lightpurple"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Estado</v-card-title>
          <v-card-text>Abierto</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto gradient-background-lightblue"
          color="#D1B6FF"
          height="135px"
          style="box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Mesas disponibles</v-card-title>
          <v-card-text>3 mesas libres</v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <div class="tittle_text" style="margin-top:20px;">ORDENES</div>
    <v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="items" :items-per-page="5">
          <template v-slot:item.action="{ item }">
            <v-icon small class="mr-2" @click="$router.push('editarorden');">edit</v-icon>
            <v-icon small @click="deleteItem(item)">payment</v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      headers: [
        {
          text: "Cuenta",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Mesa", value: "mesa" },
        { text: "Cliente", value: "cliente" },
        { text: "Mesero", value: "mesero" },
        { text: "Total", value: "total" },
        { text: "Actions", value: "action", sortable: false }
      ],
      items: [
        {
          cliente: "primer cliente",
          estado: false,
          fecha: "2019-06-20T00:00:00Z[UTC]",
          id: 1,
          mesa: 2,
          mesero: "primero",
          total: 4.35
        },
        {
          cliente: "tercer cliente",
          estado: false,
          fecha: "2019-06-20T00:00:00Z[UTC]",
          id: 2,
          mesa: 2,
          mesero: "primero",
          total: 20.0
        },
        {
          cliente: "tercer cliente",
          estado: true,
          fecha: "2019-06-20T00:00:00Z[UTC]",
          id: 3,
          mesa: 2,
          mesero: "primero",
          total: 20.0
        },
        {
          cliente: "cliente random",
          estado: true,
          fecha: "2019-06-07T00:00:00Z[UTC]",
          id: 4,
          mesa: 4,
          mesero: "mesero1"
        },
        {
          cliente: "cliente33",
          estado: false,
          fecha: "2019-06-05T00:00:00Z[UTC]",
          id: 5,
          mesa: 3,
          mesero: "mesero4",
          observaciones: "esto es prueba para las ventas",
          total: 33.5
        },
        {
          cliente: "cliente34",
          estado: false,
          fecha: "2019-06-05T00:00:00Z[UTC]",
          id: 6,
          mesa: 2,
          mesero: "mesero 3",
          observaciones: "prueba para ventas",
          total: 1.5
        },
        {
          cliente: "cliente35",
          id: 7,
          mesa: 2,
          mesero: "mesero3",
          observaciones: "nopasa"
        },
        {
          cliente: "cliente35",
          id: 8,
          mesa: 2,
          mesero: "mesero3",
          observaciones: "nopasa"
        },
        {
          cliente: "cliente35",
          id: 9,
          mesa: 2,
          mesero: "mesero3",
          observaciones: "nopasa"
        },
        {
          cliente: "cliente35",
          id: 10,
          mesa: 2,
          mesero: "mesero3",
          observaciones: "nopasa"
        }
      ]
    };
  }
};
</script>

<style>
.v-card__title {
  color: white;
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 15px;
  line-height: 19px;
}
.v-card__text {
  margin-top: 15px;
  color: white !important;
  opacity: 0.8;
  padding-left: 30px;
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 24px;
}

.tittle_text {
  font-family: ABeeZee;
  font-style: normal;
  font-weight: normal;
  font-size: 20px;
  line-height: 16px;
  opacity: 0.8;
  margin: 10px 0;
}
</style>