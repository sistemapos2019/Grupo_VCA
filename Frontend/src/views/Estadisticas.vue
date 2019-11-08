<template>
  <v-container fluid>
    <div class="tittle_text">VENTAS DIARIAS</div>
    <v-row>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="150px"
          style="background: linear-gradient(-49deg, #736DFF, #A97FFF);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039);"
        >
          <v-card-title>Platos vendidos</v-card-title>
          <v-card-text>{{diario.platos}} platos</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="150px"
          style="background: linear-gradient(323.35deg, #F7C598 6.2%, #FF8886 97.74%);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Ordenes finalizadas</v-card-title>
          <v-card-text>{{diario.ordenes}} ordenes</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-card
          max-width="344"
          class="mx-auto"
          color="#D1B6FF"
          height="150px"
          style="background: linear-gradient(147.2deg, #F9B4BD 0%, #9573DB 100%);
        box-shadow: 3px 3px 4px rgba(100, 100, 100, 0.498039)"
        >
          <v-card-title>Platillo mas vendido</v-card-title>
          <v-card-text>{{diario.platillos}}</v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" lg="3">
        <v-date-picker v-model="picker" locale="es" @change="getStats(); getWeekStats();" color="purple lighten-2"></v-date-picker>
      </v-col>
    </v-row>
    <div class="tittle_text" style="margin-top:-200px;">VENTAS SEMANALES</div>
    <v-row>
      <v-col cols="12" lg="4" offset-lg="1">
        <apexchart width="400" type="bar" :options="options" :series="series"></apexchart>
      </v-col>
      <v-col cols="12" lg="4">
        <apexchart width="400" type="bar" :options="options" :series="series"></apexchart>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
const rm = new restMethods();
export default {
  data() {
    return {
      picker: new Date().toISOString().substr(0, 10),
      diario: {
        platillos: "",
        ordenes: 0,
        platos: 0
      },
      options: {
        chart: {
          id: "estadisticas"
        },
        fill: {
          colors: ["#9c7bff"]
        },
        xaxis: {
          categories: [
            "lunes",
            "martes",
            "miercoles",
            "jueves",
            "viernes",
            "sabado",
            "domingo"
          ]
        }
      },
      series: [
        {
          name: "ventas",
          data: [0, 0, 0, 0, 0, 0, 0]
        }
      ]
    };
  },
  created(){
    rm.postJsonBitacora({
          id:5,
          usuario:{
            id: this.$store.state.IdUsuario,
          }
        });
  },
  methods: {
    getStats() {
      let path =
        this.picker != undefined
          ? `estadisticas?date=${this.picker}`
          : `estadisticas`;
      rm.getJson(path)
        .then(r => {
          this.diario = r.data;
        })
        .catch(e => {
          this.diario = {
            platillos: "",
            ordenes: 0,
            platos: 0
          };
        });
      console.log(JSON.stringify(this.diario));
    },
    getWeekStats() {
      let path =
        this.picker != undefined
          ? `estadisticas?date=${this.picker}&week=true`
          : `estadisticas?week=true`;

      rm.getJson(path)
        .then(r => {
          let newData = [0,0,0,0,0,0,0]; 

          r.data.map(m=>{
            newData.splice(m.dia-1,1,m.cantidad);
          });

          this.series = [{
            data: newData
          }]
        })
        .catch(e => {
          console.log("error en estadisticas semanales ", e);
        });
        console.log(JSON.stringify(this.series))
    }
  },
  mounted: function() {
    this.getStats();
    this.getWeekStats();
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

.v-picker__title {
  background: linear-gradient(348.19deg, #736dff 6.2%, #a97fff 100%);
}
</style>