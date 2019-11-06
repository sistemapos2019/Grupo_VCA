import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins'
import vuetify from './plugins/vuetify'
import { sync } from 'vuex-router-sync'
import VueApexCharts from 'vue-apexcharts'

Vue.use(VueApexCharts)
Vue.component('apexchart', VueApexCharts)

sync(store, router)

Vue.config.productionTip = false
//variables globales

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app')
