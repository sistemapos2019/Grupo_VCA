<template>
  <v-container fluid>
    <v-data-table
      :headers="headers"
      :items-per-page="5"
      :items="compras"
      sort-by="clave"
      class="elevation-1"
    >
      <template v-slot:item.fecha="{ item }">{{item.fecha | getFecha}}</template>

      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn dark class="mb-2 gradient-background" v-on="on" @click="create()">Nueva Compra</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.ndocumento" label="N° Documento"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.nitdui" label="NIT/DUI"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.nrc" label="NRC"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.nombreProveedor" label="Proveedor"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.montoInterno" label="Monto"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.iva" label="IVA"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.percepcion" label="Percepcion"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="compra.total" label="Total"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="#504da3" text @click="close">
                  <v-icon>mdi-cancel</v-icon>Cancelar
                </v-btn>
                <v-btn color="#504da3" text @click="save">
                  <v-icon>mdi-content-save</v-icon>
                  {{compraA?'Continuar':'Guardar'}}
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!--Esto es para detalles productos a las compras -->
          <v-dialog v-model="detalle" max-width="500px">
            <v-card>
              <v-card-title>DETALLE DE LA COMPRA {{numeroCompra}}</v-card-title>
              <v-data-table :items="detalleCompra" :headers="headersDetalle" hide-default-footer>
                <template v-slot:body.append="{ headers }">
                  <tr>
                    <td align="center" :colspan="headers.length">Total: {{totalDetalle}}</td>
                  </tr>
                </template>
              </v-data-table>
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="#504da3" text @click="close">
                  <v-icon>mdi-cancel</v-icon>Cerrar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!--finaliza los detalles de las ordenes-->
          <!--Esto es para agregar productos a las compras -->
          <v-dialog v-model="agregarDetalle" max-width="400px">
            <v-card>
              <v-card-title>Agregar Productos</v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="16" sm="14" md="12">
                      <v-select
                        :items="producto"
                        item-text="nombre"
                        item-value="id"
                        v-model="detalles.idProducto"
                        label="Producto"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="detalles.cantidad" label="Cantidad"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="detalles.precioUnitario" label="Precio"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <div class="flex-grow-1"></div>
                <v-btn color="#504da3" text @click="closeE">
                  <v-icon>mdi-cancel</v-icon>Cancelar
                </v-btn>
                <v-btn color="#504da3" text @click="saveE">
                  <v-icon>mdi-content-save</v-icon>Guardar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!--finaliza el agregar productos a las compras -->
        </v-toolbar>
      </template>
      <template v-slot:item.detalle="{ item }">
        <v-icon small class="mr-2" @click="getDetalle(item)">mdi-eye-settings</v-icon>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
        <!--<v-icon small @click="deleteItem(item)">mdi-delete</v-icon>-->
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
import { setTimeout } from "timers";
const rm = new restMethods();
export default {
  data() {
    return {
      dialog: false,
      detalle: false,
      detalleCompra: this.getDetalle(""),
      totalDetalle: "",
      producto: this.det(),
      agregarDetalle: false,
      compraA: false,
      numeroCompra: "",
      headersDetalle: [
        {
          text: "Cantidad",
          align: "left",
          sortable: false,
          value: "cantidad"
        },
        {
          text: "Producto",
          sortable: false,
          value: "nombre"
        },
        {
          text: "Precio Unitario",
          sortable: false,
          value: "precio"
        }
      ],
      headers: [
        {
          text: "fecha de la compra",
          align: "left",
          sortable: false,
          value: "fecha"
        },
        { text: "N° Documento", value: "ndocumento" },
        { text: "NRC", value: "nrc" },
        { text: "NIT/DUI", value: "nitdui" },
        { text: "Proveedor", value: "nombreProveedor" },
        { text: "Monto", value: "montoInterno" },
        { text: "IVA", value: "iva" },
        { text: "Percepcion", value: "percepcion" },
        { text: "Total", value: "total" },
        { text: "Detalle", value: "detalle", sortable: false },
        { text: "Actions", value: "action", sortable: false }
      ],
      compras: this.getcompras(),
      editedIndex: -1,
      indexDetalle: -1,
      compra: {
        id:"",
        nombreProveedor: "",
        fecha: "",
        ndocumento: "",
        nrc: "",
        nitdui: "",
        montoInterno: "",
        iva: "",
        percepcion: "",
        total: ""
      },
      detalles: {
        idCompra: "",
        idProducto: "",
        cantidad: "",
        precioUnitario: ""
      },
      fechaCompra: "2018-11-07"
    };
  },
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
  methods: {
    getcompras() {
      rm.getJson("compras")
        .then(r => {
          this.compras = r.data;
        })
        .catch(e => {
          this.compras = [];
        });
    },
    getDetalle(item) {
      this.numeroCompra = item.ndocumento;
      this.totalDetalle = item.total;
      rm.getJson("detallecompras?compra=" + item.id)
        .then(r => {
          this.detalleCompra = r.data;
        })
        .catch(e => {
          this.detalleCompra = [];
        });
      this.detalle = true;
    },
    create() {
      this.dialog = true;
      this.editedIndex = -1;
      this.compraA = true;
      console.log("crear");
    },
    det() {
      this.agregarDetalle = true;
      this.detalles = null;
      rm.getJson("productos")
        .then(r => {
          this.producto = r.data;
          console.log(JSON.stringify(this.producto));
        })
        .catch(e => {
          this.producto = [];
        });
    },
    editItem(item) {
      this.editedIndex = 0;
      this.compra = item;
      this.dialog = true;
      this.compraA = false;
    },
    closeE() {
      this.agregarDetalle = false;
      this.detalles.idCompra = null;
      this.detalles.idProducto = null;
      this.detalles.cantidad = null;
      this.detalles.precioUnitario = null;
    },
    saveE() {
      console.log("yayayaya");
      console.log("a" + this.detalles.idCompra);
      console.log("b" + this.detalles.idProducto);
      console.log("c" + this.detalles.cantidad);
      console.log("d" + this.detalles.precioUnitario);
      rm.postJson("detallecompras", {
        idCompra: this.detalles.idCompra,
        idProducto: this.detalles.idProducto,
        cantida: this.detalles.cantidad,
        precioUnitario: this.detalles.precioUnitario
      });
    },
    close() {
      this.dialog = false;
      this.detalle = false;
      setTimeout(() => {
        this.compra = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        console.log("editar: "+JSON.stringify(this.compra));
        rm.putJson("compras/"+parseInt(this.compra.id),{
          id:this.compra.idCompra,
          iva: parseFloat(this.compra.iva),
          montoInterno: parseFloat(this.compra.montoInterno),
          ndocumento: this.compra.montoInterno,
          nitdui: this.compra.nitdui,
          nombreProveedor: this.compra.nombreProveedor,
          nrc: this.compra.nrc,
          percepcion: parseFloat(this.compra.percepcion),
          total: parseFloat(this.compra.total)
        });
        this.bitacora = 18;
      } else {
        if (
          this.compra.iva !== null &&
          this.compra.montoInterno !== null &&
          this.compra.percepcion !== null &&
          this.compra.nombreProveedor !== null &&
          this.editedIndex === -1
        ) {
          rm.postJson("compras", {
            id: null,
            iva: parseFloat(this.compra.iva),
            montoInterno: parseFloat(this.compra.montoInterno),
            ndocumento: this.compra.montoInterno,
            nitdui: this.compra.nitdui,
            nombreProveedor: this.compra.nombreProveedor,
            nrc: this.compra.nrc,
            percepcion: parseFloat(this.compra.percepcion),
            total: parseFloat(this.compra.total)
          }).then(r => {
            this.getcompras();
          });
          this.bitacora = 6;
          this.agregarDetalle = true;
        }
      }
      rm.postJsonBitacora({
        id: this.bitacora,
        idCompra: this.compra.idCompra,
        usuario: {
          id: this.$store.state.IdUsuario
        }
      });

      this.close();
    }
  },
  filters: {
    getFecha: function(value) {
      let date = new Date(value.replace("[UTC]", ""));
      let dia = date.getUTCDate().valueOf();
      let mes = (date.getUTCMonth() + 1).valueOf();
      if (dia < 10) {
        dia = "0" + dia;
      }
      if (mes < 10) {
        mes = "0" + mes;
      }
      return String(date.getUTCFullYear() + "-" + mes + "-" + dia);
    }
  }
};
</script>