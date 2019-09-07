<template>
  <v-navigation-drawer
    id="app-drawer"
    v-model="inputValue"
    :src="image"
    app
    color="grey darken-2"
    dark
    floating
    mobile-break-point="991"
    persistent
    width="260"
  >
    <template v-slot:img="attrs">
      <v-img
        v-bind="attrs"
        gradient="to top, #716DFF, #A47DFF"
      />
    </template>

    <v-list-item two-line>
      <v-list-item-avatar color="white">
        <v-img
          src="https://cdn.vuetifyjs.com/images/logos/v.png"
          height="34"
          contain
        />
      </v-list-item-avatar>

      <v-list-item-title class="title">
        SISTEMA POS
      </v-list-item-title>
    </v-list-item>

    <v-divider class="mx-3 mb-3" />

    <v-list nav>
      <!-- Bug in Vuetify for first child of v-list not receiving proper border-radius -->
      <div />

      <v-list-item
        v-for="(link, i) in links"
        :key="i"
        :to="link.to"
        active-class="primary white--text"
      >
        <v-list-item-action>
          <v-icon>{{ link.icon }}</v-icon>
        </v-list-item-action>

        <v-list-item-title v-text="link.text" />
      </v-list-item>

      <v-list-group
        color="white"
        prepend-icon="mdi-apps"
        value="true"
      > <template v-slot:activator>
            <v-list-item-title >Administracion</v-list-item-title>
          </template>
          <v-list-item
            v-for="(administrator, i) in administrations"
            :key="i"
            :to="administrator.to"
            active-class="primary white--text"
          >
            <v-list-item-title >{{administrator.text}}</v-list-item-title>
            <v-list-item-icon>
              <v-icon>{{administrator.icon}}</v-icon>
            </v-list-item-icon>
          </v-list-item>
      </v-list-group>
    </v-list>

  </v-navigation-drawer>
</template>

<script>
// Utilities
  import {
    mapMutations,
    mapState
  } from 'vuex'

  export default {
    props: {
      opened: {
        type: Boolean,
        default: false
      }
    },
    data: () => ({
      administrations: [
      {
        to: "/categorias",
        icon: "mdi-animation",
        text: "Categorias"
      },
      {
        to: "/usuarioroles",
        icon: "mdi-account-group",
        text: "Usuario y Roles"
      },
      {
        to: "/productos",
        icon: "mdi-coffee",
        text: "Productos"
      },
      {
        to: "/parametros",
        icon: "mdi-altimeter",
        text: "Parametros"
      },
      {
        to: "/compras",
        icon: "mdi-application",
        text: "Compras"
      },
      {
        to: "/mesas",
        icon: "mdi-table-chair",
        text: "Mesas"
      }
      ],
      links: [
        {
          to: '/',
          icon: 'mdi-view-dashboard',
          text: 'Dashboard'
        },
        {
          to: '/estadisticas',
          icon: 'mdi-chart-bar',
          text: 'Estadisticas'
        },
        {
          to: '/inventarios',
          icon: 'mdi-dropbox',
          text: 'Inventarios'
        },
        {
          to: '/contabilidad',
          icon: 'mdi-clipboard-outline',
          text: 'Contabilidad'
        },
        {
          to: '/typography',
          icon: 'mdi-format-font',
          text: 'Typography'
        },
        {
          to: '/icons',
          icon: 'mdi-chart-bubble',
          text: 'Icons'
        },
        {
          to: '/maps',
          icon: 'mdi-map-marker',
          text: 'Maps'
        },
        {
          to: '/notifications',
          icon: 'mdi-bell',
          text: 'Notifications'
        }
      ]
    }),

    computed: {
      ...mapState('app', ['image', 'color']),
      inputValue: {
        get () {
          return this.$store.state.app.drawer
        },
        set (val) {
          this.setDrawer(val)
        }
      }
    },

    methods: {
      ...mapMutations('app', ['setDrawer', 'toggleDrawer'])
    }
  }
</script>

<style>
.v-application .primary{
    background-color: #d1caca5d !important;
    border-color: #d1caca5d !important;
    border-radius: 0;
    border-left: 4px solid white !important;
}

.v-list--nav .v-list-item{
  padding: 0 24px !important;
}

.v-navigation-drawer .v-list{
  padding: 0;
}
</style>