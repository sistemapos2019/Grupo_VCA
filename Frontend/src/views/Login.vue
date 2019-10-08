<template>
    <v-content>
      <v-container fluid fill-height >
        <v-layout align-center justify-center >
          <v-flex xs12 sm8 md4 >
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat >
                <v-toolbar-title>Login Users</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-select :items="usuarios" item-value="login" item-text="login" v-model="login"
                  prepend-icon="person"></v-select>

                  <v-text-field id="password" label="Password" 
                  v-model="password" name="password" prepend-icon="lock" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="Autentication(login,password)">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
</template>
<script>
import restMethods from "./../utils/restMethods.js"
const rm = new restMethods();
export default {
    data(){
        return {
          usuarios:this.getUsers(),
            password:"",
            login:"",
        };
    },
    methods:{
      getUsers(){
        rm.getJson("usuarios")
        .then(r=>{
          this.usuarios=r.data;
        })
        .catch(e=>{
          this.usuarios=[];
        });
      },
        async Autentication(login,password){
          let user;
        if(login!="" && login!=null){
        await rm.getJson("usuarios/user?login="+login)
         .then(r=>{
          user= String(r.data);
         })
         .catch(e=>{
          user= String("");
         });
           if(user===password){
             console.log("paso por que son iguales");
           }else{
             console.log("las claves son distintas");
             confirm("La Clave no es la Correcta ....Favor ingresar nuevamente la clave");
           }
        }else{
          confirm("Nombre de Usuario Erroneo");
        }
      },
    },
}
</script>