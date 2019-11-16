<template>
  <v-dialog id="core-login-modal" v-model="dialog" max-width="600px">
    <template v-slot:activator="{ on }">
      <v-btn v-on="on" @click="login = ''; password='';getUsers();" icon>
        <v-icon color="tertiary">mdi-account</v-icon>
      </v-btn>
    </template>
    <v-card class="elevation-12">
      <v-toolbar class="gradient-background" dark flat>
        <v-toolbar-title>LOGIN</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-card-text>
        <v-form>
          <v-select
            :items="usuarios"
            item-value="user"
            item-text="user"
            v-model="login"
            prepend-icon="person"
          ></v-select>

          <v-text-field
            id="password"
            label="Password"
            v-model="password"
            name="password"
            prepend-icon="lock"
            type="password"
          ></v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red" text dark @click="dialog = false;">Cancelar</v-btn>
        <v-btn color="red" dark @click="getInfo(login,password)">Login</v-btn>
      </v-card-actions>
    </v-card>
    <v-snackbar v-model="snack">
      {{ text }}
      <v-btn color="pink" text @click="snack = false">Close</v-btn>
    </v-snackbar>
  </v-dialog>
</template>
<script>
import restMethods from "./../../utils/restMethods.js";
import md5 from "js-md5";
import router from "vue-router";
const rm = new restMethods();
const ru = new router();
export default {
  data() {
    return {
      usuarios: [],
      usuario: [],
      password: "",
      login: "",
      snack: false,
      text: "",
      rol: "",
      idUsuario: "",
      dialog: false
    };
  }, 
  methods: {
    autentication(pass) {
      this.rol = this.usuario[0].rol === "M" ? "Mesero" : "Gerente";
      this.idUsuario = this.usuario[0].id;
      console.log("madre mia", this.login);
      console.log("Rol: " + this.rol + " IdUs: " + this.idUsuario);
      if (
        this.usuario[0].pin === pass ||
        this.usuario[0].clave === md5(pass)
      ) {;
        this.$store.state.IdUsuario = this.idUsuario;
        this.$store.state.usuario = this.login;
        this.$store.state.rol = this.rol;
        this.$store.state.colorRol = (this.rol == "Mesero") ? 'red' : 'orange';
        // if (this.$store.state.pantallaVista) {
        //   this.$router.push("/");
        // }
        this.snack = true;
        this.text = `${this.usuario[0].user} logueado correctamente.`;
        setTimeout(() => {
          this.dialog = false;
          // this.$router.next();
        }, 500);
      } else {
        this.snack = true;
        this.text = "Error Clave erronea para " + this.usuario[0].user;
        console.log("Error" + JSON.stringify(this.usuarios));
        this.usuarios = this.getUsers();
        //  this.$router.push("login");
      }
      console.log("verId: " + this.$store.state.IdUsuario);
      console.log("verRol: " + this.$store.state.rol);
    },
    getInfo(login, password) {
      if (password == "" || login == "") {
        this.snack = true;
        this.text = "Error, no ingreso datos";
      } else {
        this.setUsuarioInfo(login);
      }
    },
    getUsers() {
      rm.getJson("usuarios/user")
        .then(r => {
          this.usuarios = r.data;
          console.log(JSON.stringify(this.usuarios));
        })
        .catch(e => {
          this.usuarios = [];
        });
    },
    setUsuarioInfo(login) {
      console.log(login);
      rm.getJson(`usuarios/user?login=${login}`)
        .then(r => {
          this.usuario = r.data;
          console.log("uso", JSON.stringify(this.usuario));
          this.password ? this.autentication(this.password) : "";
        })
        .catch(e => {
          this.usuario = [];
        });
    },
    loginPantalla(){
      rm.getJson('parametros/12').then(r=>{
        this.$store.state.loginPantalla = (r.data.valor==1) ? true : false;
      })
    }
  },
  mounted: function() {
    this.getUsers();
    this.loginPantalla();
  }
};
</script>