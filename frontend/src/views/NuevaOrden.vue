<template>
  <v-container fluid class="container">
    <v-layout>
      <v-flex xs7>
        <v-card max-width="500" class="mx-auto card" height="230px" >
          <v-form>
            <v-container>
              <v-row>
                <v-col cols="2">
                  <v-text-field v-model="detalle.cuenta" label="Orden:" required></v-text-field>
                </v-col>
                <v-col cols="5">
                  <v-text-field v-model="detalle.mesero" label="Mesero:" required></v-text-field>
                </v-col>
                <v-col cols="5">
                  <v-text-field v-model="detalle.cliente" label="Cliente:" required></v-text-field>
                </v-col>
                <v-col cols="3">
                  <v-text-field v-model="detalle.mesa" label="Mesa:" required></v-text-field>
                </v-col>
                <v-col cols="7">
                  <v-text-field label="Seleccione un Producto" v-model="search"></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
          <v-container>
            <v-tabs v-model="active" fixed-tabs slider-color="yellow" centered>
              <v-tab
                v-for="(categoria, index) in categorias"
                :key="index"
                ripple
                @click="setCategoria(categoria)"
              >{{ categoria }}</v-tab>
            </v-tabs>
          </v-container>
        </v-card>
        <v-container fluid>
          <v-list>
            <v-list-item v-for="(producto, index) in filter" :key="index" ripple>
              <v-list-item-content>
                <v-list-item-title>{{producto.nombre}}</v-list-item-title>
              </v-list-item-content>
              <v-btn flat icon @click="disminuir(index,producto)">
                <v-icon>remove</v-icon>
              </v-btn>
              <v-btn flat icon @click="aumentar(index, producto)">
                <v-icon>add</v-icon>
              </v-btn>
              <v-list-item-action>{{producto.cantidad}}</v-list-item-action>
            </v-list-item>
          </v-list>
        </v-container>
      </v-flex>
      <v-flex xs6>
        <vista :productos="resumen" :detalles="detalle" :tamanio="tamanio"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import OrdenVista from "./OrdenVista";
import restMethods from "../utils/restMethods.js";
const rest = new restMethods();
export default {
  components: {
    vista: OrdenVista
  },
  data() {
    return {
      active: null,
      search: "",
      resumen: [],
      categoria: "PRINCIPAL",
      tamanio: 0,

      categorias: ["PRINCIPAL", "BEBIDAS FRIAS", "BEBIDAS CALIENTES", "POSTRES"],
      producto: { nombre: "", precio: 0.0, cantidad: 0 },
      detalle: {
        cuenta: null,
        mesa: null,
        cliente: "",
        mesero: "",
        total: null,
        resumen: []
      },
      productos: [],
      articulos: []
    };
  },
  created(){
    this.getProductos();
  },
  computed: {
    filter() {
      if (this.articulos != null) {
        let filtrados = this.articulos.filter( producto => producto.categoria === this.categoria);
        console.log(JSON.stringify(filtrados));
        return filtrados.filter(producto =>
          producto.nombre.toLowerCase().includes(this.search.toLowerCase())
        );
      }
    }
  },
  methods: {
    armarResumen() {
      this.articulos = this.productos.map(producto => {
        return {
          nombre: producto.nombre,
          precio: producto.precio,
          cantidad: 0,
          categoria: producto.categoria.nombre,
          id: producto.id
        };
      });
      //console.log(JSON.stringify(this.articulos))
    },
    getCategorias() {
      rest
        .getJson("categorias")
        .then(r => {
          let f = r.data.map(m => {
            return m.nombre;
          });
          this.categorias = f;
          //console.log(this.categorias);
        })
        .catch(e => {
          this.categorias = [""];
        });
    },
    getProductos() {
      this.productos = [
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 1,
          nombre: "PEPSI",
          precio: 0.5
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 2,
          nombre: "COCA COLA",
          precio: 0.6
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 3,
          nombre: "SUPREMA",
          precio: 1.5
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 4,
          nombre: "VODKA",
          precio: 7.0
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 5,
          nombre: "PILSENER",
          precio: 1.0
        },
        {
          categoria: { id: 1, nombre: "BEBIDAS CALIENTES" },
          id: 6,
          nombre: "CAFE",
          precio: 1.0,
          preparado: true
        },
        {
          categoria: { id: 1, nombre: "BEBIDAS CALIENTES" },
          id: 7,
          nombre: "CHOCOLATE",
          precio: 1.6,
          preparado: true
        },
        {
          categoria: { id: 1, nombre: "BEBIDAS CALIENTES" },
          id: 8,
          nombre: "TE CALIENTE",
          precio: 0.75,
          preparado: true
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 9,
          nombre: "LICUADOS",
          precio: 1.15,
          preparado: true
        },
        {
          categoria: { id: 2, nombre: "BEBIDAS FRIAS" },
          id: 10,
          nombre: "FROZEN",
          precio: 1.35,
          preparado: true
        }
      ];
      this.armarResumen();
    },
    aumentar(index, s) {
      if (s.cantidad >= 0) {
        s.cantidad++;
      }
      let registro = this.setProductos(s);
      this.addProducto(registro);
      this.setCantidad(registro);
    },
    disminuir(index, s) {
      if (s.cantidad > 0) {
        s.cantidad--;
        let registro = this.setProductos(s);
        this.setCantidad(registro, s);
      }
    },
    setCategoria(categoria) {
      this.categoria = categoria;
      console.log(this.categoria);
    },
    setProductos(producto, index) {
      let registro = { producto: "", precio: "", cantidad: null, id: null };

      registro.producto = producto.nombre;
      registro.precio = producto.precio;
      registro.cantidad = producto.cantidad;
      registro.id = producto.id;

      //console.log(JSON.parse(JSON.stringify(registro)));
      //console.log(JSON.parse(JSON.stringify(this.resumen)));

      return registro;
    },
    addProducto(registro) {
      if (
        !this.resumen.some(producto => producto.producto === registro.producto)
      ) {
        this.resumen.push(registro);
      }
    },
    setCantidad(registro, producto) {
      this.resumen.forEach(producto => {
        if (
          producto.producto === registro.producto &&
          producto.cantidad !== registro.cantidad
        ) {
          producto.cantidad = registro.cantidad;
        }
      });

      this.resumen = this.resumen.filter(producto => producto.cantidad !== 0);
      this.tamanio = this.resumen.length;
      // console.log(JSON.parse(JSON.stringify(this.resumen)));
    }
  }
};
</script>

<style>
.card {
  padding: 1%;
}

.container {
  padding: 0%;
  margin-top: 5px;
}

.flex {
  padding-left: 1%;
  padding-right: 1%;
}

.v-text-field {
  padding-top: 0%;
}
</style>

