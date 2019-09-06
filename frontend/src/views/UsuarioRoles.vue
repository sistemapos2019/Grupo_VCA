<template>
  <v-card>
    <template v-slop:top>
      <v-toolbar flat color="white">
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on }">
            <v-btn rounded color="secondary" dark class="mb-2" v-on="on">Nuevo Usuario</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.title" label="Nombre del Usuario"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.login" label="Login"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.clave" label="Clave"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                     <v-select :items="item" v-model="editedItem.rol" label="Rol"></v-select>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                     <v-tooltip bottom>
                       <template v-slot:activator="{ on }">
                         <v-btn color="secundary" dark v-on="on" v-model="editItem.avatar">Imagen</v-btn>
                         </template><span>Imagen</span></v-tooltip>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="blue darken-1" text @click="close">
                <v-icon>mdi-cancel</v-icon>Cancel
              </v-btn>
              <v-btn color="blue darken-1" text @click="save">
                <v-icon>mdi-content-save</v-icon>Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>

    <v-list>
      <v-subheader>Usuarios</v-subheader>

      <v-list-item v-for="item in items" :key="item.title">
        <v-list-item-avatar>
          <v-img :src="item.avatar"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="item.title"></v-list-item-title>
          <v-list-item-subtitle v-text="item.rol"></v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-content>
          <v-list-item-title v-text="item.login"></v-list-item-title>
        </v-list-item-content>

        <v-list-item-icon>
          <v-icon small color="blue" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small color="blue" @click="deleteItem(item)">mdi-delete</v-icon>
        </v-list-item-icon>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    items: [
      {title: "Jason Oner", avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg", login: "JasonOner", rol: "Mesero"},
      {title: "Ranee Carlson", avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg", login: "RaneeCarlson", rol: "Mesero"},
      {title: "Cindy Baker", avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg", login: "CindyBaker", rol: "Mesero"},
      {title: "Ali Connors", avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",login: "AliConnors", rol: "Mesero"},
      {title: "La Vistima", avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",login: "Vistima", rol: "Mesero"}
    ],
    item:['Gerente','Mesero','Cocinero'],
    editedIndex: -1,
    editedItem: {
      title: "",
      login: "",
      rol: ""
    },
    defaultItem: {
      title: "",
      login: "",
      rol: ""

    }
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Usuario" : "Editar Usuario";
    }
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.items.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.items.indexOf(item);
      confirm("Are you sure you want to delete this item?") &&
        this.items.splice(index, 1);
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
        Object.assign(this.items[this.editedIndex], this.editedItem);
      } else {
        this.items.push(this.editedItem);
      }
      this.close();
    }
  }
};
</script>