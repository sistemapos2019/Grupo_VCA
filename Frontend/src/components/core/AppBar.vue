<template>
  <v-app-bar id="core-app-bar" absolute app color="transparent" flat height="88">
    <v-toolbar-title class="tertiary--text font-weight-light align-self-center">
      <v-btn v-if="responsive" dark icon @click.stop="onClick">
        <v-icon>mdi-view-list</v-icon>
      </v-btn>
      {{ title }}
    </v-toolbar-title>

    <v-spacer />

    <v-toolbar-items>
      <v-row align="center" justify="center" class="mx-0">
        <!-- <v-btn style="padding-left:40px;" text icon :color="this.$store.state.colorRol" v-if="this.$store.state.loginPantalla"> -->
          <v-icon :color="this.$store.state.colorRol" v-if="this.$store.state.loginPantalla" dark>mdi-account-circle</v-icon>
        <!-- </v-btn> -->

        <v-chip class="ma-6" :color="this.$store.state.colorRol" text-color="white" style="margin-left: 10px !important;">
          {{this.$store.state.usuario | Upper}}
          <v-icon right>mdi-star</v-icon>
        </v-chip>

        <v-btn icon to="/">
          <v-icon color="tertiary">mdi-view-dashboard</v-icon>
        </v-btn>

        <v-menu bottom left offset-y transition="slide-y-transition">
          <template v-slot:activator="{ attrs, on }">
            <v-btn class="toolbar-items" icon to v-bind="attrs" v-on="on">
              <v-badge color="error" overlap>
                <template slot="badge">{{ notifications.length }}</template>
                <v-icon color="tertiary">mdi-bell</v-icon>
              </v-badge>
            </v-btn>
          </template>

          <v-card>
            <v-list dense>
              <v-list-item
                v-for="notification in notifications"
                :key="notification"
                @click="onClick"
              >
                <v-list-item-title v-text="notification" />
              </v-list-item>
            </v-list>
          </v-card>
        </v-menu>
        <core-login-modal></core-login-modal>
      </v-row>
    </v-toolbar-items>
  </v-app-bar>
</template>

<script>
// Utilities
import { mapMutations } from "vuex";

import rest from "./../../utils/restMethods.js";
const rm = new rest();

export default {
  data: () => ({
    notifications: [],
    title: null,
    responsive: false,
    color: "green"
  }),

  watch: {
    $route(val) {
      this.title = val.name;
    }
  },

  mounted() {
    this.onResponsiveInverted();
    window.addEventListener("resize", this.onResponsiveInverted);
    this.bitacora();
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResponsiveInverted);
  },

  methods: {
    ...mapMutations("app", ["setDrawer", "toggleDrawer"]),
    onClick() {
      this.setDrawer(!this.$store.state.app.drawer);
    },
    onResponsiveInverted() {
      if (window.innerWidth < 991) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
    bitacora() {
      rm.getJson("bitacoras/latest").then(r => {
        this.notifications = r.data;
      });
    }
  },
  components: {
    CoreLoginModal: () => import("@/components/core/LoginModal")
  },
  filters: {
    Upper(value) {
      return typeof value === "undefined" ? "" : value.toUpperCase();
    }
  }
};
</script>

<style>
/* Fix coming in v2.0.8 */
#core-app-bar {
  width: auto;
}

#core-app-bar a {
  text-decoration: none;
}
</style>
