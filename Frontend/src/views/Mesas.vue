<template>
  <v-container fluid>
    <v-data-table
      :headers="headers"
      :items-per-page="5"
      :items="mesas"
      sort-by="categorias"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn dark class="mb-2 gradient-background" v-on="on" @click="create()">Nueva Mesa</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline black--text">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="12" md="12">
                      <v-text-field v-model="arrayMesas.mesa" label="Nombre de Mesa"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

            <v-card-actions >
              <div class="flex-grow-1" ></div>
              <v-btn color="#504da3" text @click="close()">
                <v-icon>mdi-cancel</v-icon>Cancelar
              </v-btn>
              <v-btn color="#504da3" text @click="save">
                <v-icon>mdi-content-save</v-icon>Guardar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template  v-slot:item.action="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
      <!--<v-icon small @click="deleteItem(item)">mdi-delete</v-icon>-->
    </template>
  </v-data-table>
  </v-container>
</template>

<script>
import restMethods from "./../utils/restMethods.js";
const rm = new restMethods();
export default {
  data() {
    return {
      dialog: false,
      bitacora: null,
      mesas: this.getMesas(),
      headers: [
        {
          text: "Identificador de Mesa",
          align: "center",
          sortable: false,
          value: "id"
        },
        {
          text: "Nombre de Mesa",
          align: "center",
          sortable: false,
          value: "mesa"
        },
        { text: "Actions", value: "action", sortable: false }
      ],

      editedIndex: -1,
      arrayMesas: {
        mesa: ""
      },
      defaultItem: {
        mesa: ""
      }
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nueva Mesa" : "Editar Mesa";
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    getMesas() {
      rm.getJson("mesas")
        .then(r => {
          this.mesas = r.data;
        })
        .catch(c => {
          this.mesas = [];
        });
    },
    editItem(item) {
      this.editedIndex = 0;
      this.arrayMesas = item;
      this.dialog = true;
    },
    create(){
      this.dialog=true;
      this.editedIndex=-1;
      console.log("crear");
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.arrayMesas = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        rm.putJson("mesas/"+parseInt(this.arrayMesas.id),{
          id:this.arrayMesas.id,
          mesa: this.arrayMesas.mesa,
        }).then(()=>{
          this.getMesas();
        });
        this.bitacora=19;
      } else {
        rm.postJson("mesas",{
          mesa:this.arrayMesas.mesa,
        }).then(()=>{
          this.getMesas();
        });
        this.bitacora=20;
      }
      rm.postJsonBitacora({
        id:this.bitacora,
        idMesa:this.arrayMesas.id,
        usuario:{
          id:this.$store.state.IdUsuario,
        }
      });
      this.close();
    }
  }
};
</script>