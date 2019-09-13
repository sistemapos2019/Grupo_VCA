<template>
  <v-container fluid>
    <div class="tittle_text" style="margin-top:20px;">{{titulo  }}</div>
    <v-row>
      <v-col cols="12" lg="12">
        <v-data-table :headers="headers" :items="items" :items-per-page="5"></v-data-table>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" lg="3">
        <v-btn @click="change('ventas')">VENTAS</v-btn>
        <v-btn @click="change('compras')">COMPRAS</v-btn>
      </v-col>
      <v-col cols="12" lg="3">
        <v-text-field placeholder="Ingrese la fecha"></v-text-field>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      headers: [],
      items: [],
      titulo: null,
    };
  },
  methods: {
    change: function (value) {
      if(value == 'ventas'){
        this.headers = [
          {
            sortable: false,
            text: "Fecha",
            value: "fecha"
          },
          {
            sortable: false,
            text: "Venta",
            value: "venta"
          },
          {
            sortable: false,
            text: "Propinas",
            value: "propinas"
          },
          {
            sortable: false,
            text: "Total",
            value: "total"
          },
        ];
        this.items = [
          {
            fecha: "26/08/96",
            venta: "$60",
            propinas: "$5",
            total: "$65",
          },
        ];
        this.titulo = "CONTABILIDAD VENTAS";
      }else if(value == 'compras'){
        this.headers = [
          {
            sortable: false,
            text: "Fecha de emision",
            value: "fecha"
          },
          {
            sortable: false,
            text: "Numero documento",
            value: "documento"
          },
          {
            sortable: false,
            text: "Nombre proveedor",
            value: "proveedor"
          },
          {
            sortable: false,
            text: "Total compras",
            value: "total"
          },
        ];
        this.items = [
          {
            fecha: "26/03/76",
            documento: "34567-32",
            proveedor: "Juan Valdez",
            total: "$760.65"
          },
        ];
        this.titulo = "CONTABILIDAD COMPRAS";
      };
    }
  },
  mounted() {
    this.change('ventas');
  },
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

.v-btn {
  margin: 0 15px;
}
</style>