<template>
  <v-container fluid>
    <v-card>
      <template v-slop:top>
        <v-toolbar flat color="white">
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn dark class="mb-2 gradient-background" v-on="on">Nuevo Usuario</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline black-text">{{ formtitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.nombreCompleto" label="Nombre del Usuario"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.login" label="Login"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.clave" label="Clave"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.pin" label="Pin"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-select
                        :items="[{rol:'G',val:'Gerente'},{rol:'M',val:'Mesero'}]"
                        item-text="val"
                        item-value="rol"
                        v-model="editedItem.rol"
                        label="Rol"
                      ></v-select>
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
                  <v-icon>mdi-content-save</v-icon>Guardar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>

      <v-list>
        <v-row>
          <v-col>
            <v-subheader>Usuarios</v-subheader>
          </v-col>
          <v-col>
            <v-subheader>Login</v-subheader>
          </v-col>
          <v-col>
            <v-subheader>Clave</v-subheader>
          </v-col>
          <v-col>
            <v-subheader>PIN</v-subheader>
          </v-col>
          <v-col>
            <v-subheader>Actions</v-subheader>
          </v-col>
        </v-row>
        <v-list-item v-for="item in usuarios" :key="item.id">
          <v-list-item-content>
            <v-list-item-title v-text="item.nombreCompleto"></v-list-item-title>
            <v-list-item-subtitle v-text="(item.rol=='G'? 'Gerente':'Mesero')"></v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-content align="left">
            <v-list-item-title v-text="item.login"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-content>
            <v-list-item-title v-text="getContrasenia(item.clave)"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-content>
            <v-list-item-title v-text="item.pin"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-icon>
            <v-icon small color="blue" @click="editItem(item)">mdi-pencil</v-icon>
            <!--   <v-icon small color="blue" @click="deleteItem(item)">mdi-delete</v-icon>-->
          </v-list-item-icon>
        </v-list-item>
      </v-list>
    </v-card>
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
      usuarios: this.getusuarios(),
      editedIndex: -1,
      editedItem: {
        nombreCompleto: "",
        login: "",
        clave: "",
        pin: "",
        rol: ""
      },
      defaultItem: {
        nombreCompleto: "",
        login: "",
        clave: "",
        pin: "",
        rol: ""
      }
    };
  },
  computed: {
    formtitle() {
      return this.editedIndex === -1 ? "Nuevo Usuario" : "Editar Usuario";
    }
  },
  methods: {
    getusuarios() {
      rm.getJson("usuarios")
        .then(r => {
          this.usuarios = r.data;
        })
        .catch(e => {
          this.usuarios = [];
        });
    },
    editItem(item) {
      this.editedIndex = this.usuarios.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.usuarios.indexOf(item);
      confirm("Elminar Usuario de la lista?") && this.usuarios.splice(index, 1);
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.usuarios[this.editedIndex], this.editedItem);
      } else {
        this.usuarios.push(this.editedItem);
      }
      this.close();
    },
    getContrasenia(clave) {
      return clave.replace(new RegExp("[a-zA-Z]", "g"), "*");
    }
  }
};
</script>