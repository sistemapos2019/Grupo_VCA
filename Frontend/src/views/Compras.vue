<template>
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
                    <v-text-field
                      v-model="editedItem.fecha"
                      :disabled="true"
                      label="Fecha de la compra"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.ndocumento" label="N° Documento"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nitdui" label="NIT/DUI"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nrc" label="NRC"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.nombreProveedor" label="Proveedor"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.montoInterno" label="Monto"></v-text-field>
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
      <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
    </template>
  </v-data-table>
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
      producto: "",
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
      editedItem: {
        nombreProveedor: "",
        fecha: "",
        ndocumento: "",
        nrc: "",
        nitdui: "",
        montoInterno: "",
        iva: "",
        percepcion: "",
        total: "",
      },
      detalles: {
        idCompra: "",
        idProducto: "",
        cantidad: "",
        precioUnitario: ""
      },
      fechaCompra:"2018-11-07",
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
        })
        .catch(e => {
          this.producto = [];
        });
    },
    editItem(item) {
      this.editedIndex = this.compras.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
      this.compraA = false;
    },
    deleteItem(item) {
      const index = this.compras.indexOf(item);
      confirm("Desea Eliminar la Compra?") && this.compras.splice(index, 1);
    },
    closeE() {
      this.agregarDetalle = false;
      this.detalles = null;
    },
    saveE() {
      console.log("yayayaya");
    },
    close() {
      this.dialog = false;
      this.detalle = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        console.log("editar");
      } else {
        if (this.editItem != null && this.editedIndex == -1) {
          console.log("a", this.editedItem.iva);
          console.log("a", this.editedItem.montoInterno);
          console.log("a", this.editedItem.ndocumento);
          console.log("a", this.editedItem.nitdui);
          console.log("a", this.editedItem.nombreProveedor);
          console.log("a", this.editedItem.nrc);
          console.log("a", this.editedItem.percepcion);
          console.log("a", this.editedItem.total);
          console.log("fecha: "+new Date());

          rm.postJson("compras?fecha="+this.fechaCompra+"&doc="+this.editItem.ndocumento+"&nit="+this.editItem.nitdui+"&name="+this.editItem.nombreProveedor+"&nrc="+this.editItem.nrc+"&iva="+this.editItem.iva+"&monto="+this.editItem.montoInterno+"&perc="+this.editItem.percepcion, {
            id: null,
          }).then(() => {
            this.getcompras();
          });
        }
        console.log("crear");
      }
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