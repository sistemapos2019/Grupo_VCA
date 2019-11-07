<template>
    <v-content>
      <v-container fluid fill-height >
        <v-layout align-center justify-center >
          <v-flex xs12 sm8 md4 >
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title style="color:black;">Login Users</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-select :items="usuarios" item-value="user"  item-text="user" v-model="login"
                  prepend-icon="person"></v-select>

                  <v-text-field id="password" label="Password"  
                  v-model="password" name="password" prepend-icon="lock" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary"  href='#/categorias' @click="getInfo(login,password)">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
         <div>
           <v-snackbar v-model="snack">{{ text }}
             <v-btn color="pink" text @click="snack = false">Close</v-btn>
           </v-snackbar>
         </div>
        </v-layout>
      </v-container>
    </v-content>
</template>
<script>
import restMethods from "./../utils/restMethods.js"
import md5 from 'js-md5'
import router from 'vue-router'
const rm = new restMethods();
const ru = new router();
export default {
    data(){
        return {
          usuarios:this.getInfo("",null),
            password:"",
            login:"",
            snack:false,
            text:"",
            rol:"",
            idUsuario:"",
        };
    },
    methods:{
        autentication(password){
          this.rol= this.usuarios[0].rol==='M'?'Mesero':'Gerente';
          this.idUsuario=this.usuarios[0].id;
          console.log("Rol: "+this.rol+" IdUs: "+this.idUsuario);
         if( this.usuarios[0].pin===password || this.usuarios[0].clave===md5(password) ){
           this.$store.state.IdUsuario=this.idUsuario;
           this.$store.state.rol=this.rol;
          if(this.$store.state.pantallaVista){
            this.$router.push('/');
          }

         }else{
           this.snack=true;
           this.text="Error Clave erronea para "+this.usuarios[0].user;
           console.log("Error"+JSON.stringify(this.usuarios));
           this.usuarios=this.getInfo("");
           this.$router.push("login");
         }
         console.log("verId: "+this.$store.state.IdUsuario);
           console.log("verRol: "+this.$store.state.rol);

      },
      async getInfo(login,password){
       await rm.getJson("usuarios/user?login="+login)
        .then(r=>{
          this.usuarios=r.data;
          console.log(JSON.stringify(this.usuarios));
        })
        .catch(e=>{
          this.usuarios=[];
        });
        
        password? this.autentication(password):'';
      },
    }
}

</script>