<template>
  <v-container fluid>
    <div class="tittle_text">RESUMEN</div>
    <v-row>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="135px"
          style="background: linear-gradient(-49deg, #736DFF, #A97FFF);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039);"
        >
        
          <v-card-title>Ventas realizadas</v-card-title>
          <v-card-text>34 ventas</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="135px"
          style="background: linear-gradient(323.35deg, #F7C598 6.2%, #FF8886 97.74%);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Ventas pendientes</v-card-title>
          <v-card-text>10 ventas</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="135px"
          style="background: linear-gradient(147.2deg, #F9B4BD 0%, #9573DB 100%);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Estado</v-card-title>
          <v-card-text>Abierto</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="135px"
          style="background: linear-gradient(146.29deg, #44DEC5 9.19%, #4EBCFA 100%);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Mesas disponibles</v-card-title>
          <v-card-text>3 mesas libres</v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <div class="tittle_text" style="margin-top:20px;">ORDENES</div>
    <v-row>
      <v-col class="l12 s12">
        <v-data-table
        :headers="headers"
        :items="items"
        items-per-page="5"
      ></v-data-table>
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
          sortable: false,
          text: "ID",
          value: "id"
        },
        {
          sortable: false,
          text: "Name",
          value: "name"
        },
        {
          sortable: false,
          text: "Salary",
          value: "salary",
          align: "right"
        },
        {
          sortable: false,
          text: "Country",
          value: "country",
          align: "right"
        },
        {
          sortable: false,
          text: "City",
          value: "city",
          align: "right"
        }
      ],
      items: [
        {
          id: 1,
          name: "Dakota Rice",
          country: "Niger",
          city: "Oud-Tunrhout",
          salary: "$35,738"
        },
        {
          id: 2,
          name: "Minerva Hooper",
          country: "Curaçao",
          city: "Sinaai-Waas",
          salary: "$23,738"
        },
        {
          id: 3,
          name: "Sage Rodriguez",
          country: "Netherlands",
          city: "Overland Park",
          salary: "$56,142"
        },
        {
          id: 4,
          name: "Philip Chanley",
          country: "Korea, South",
          city: "Gloucester",
          salary: "$38,735"
        },
        {
          id: 5,
          name: "Doris Greene",
          country: "Malawi",
          city: "Feldkirchen in Kārnten",
          salary: "$63,542"
        }
      ],
    };
  },
  methods: {
    complete(index) {
      this.list[index] = !this.list[index];
    }
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