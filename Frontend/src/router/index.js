/**
 * Vue Router
 *
 * @library
 *
 * https://router.vuejs.org/en/
 */

// Lib imports
import Vue from 'vue'
import VueAnalytics from 'vue-analytics'
import Router from 'vue-router'
import Meta from 'vue-meta'

// Routes
import paths from './paths'
import store from '../store';

function route(path, view, name) {
    return {
        name: name || view,
        path,
        component: (resovle) =>
            import (
                `@/views/${view}.vue`
            ).then(resovle),
    }
}

Vue.use(Router)

// Create a new router
const router = new Router({
    mode: 'history',
    routes: paths.map(path => route(path.path, path.view, path.name)).concat([
        { path: '*', redirect: '/' },
    ]),
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        }
        if (to.hash) {
            return { selector: to.hash }
        }
        return { x: 0, y: 0 }
    },
});

router.beforeEach((to,from,next)=>{
      if( store.state.pantallaVista ){
        if( store.state.rol!='Gerente'){
          if( to.path==='/categorias' || to.path==='/bitacoras' || to.path==='/productos' ||
          to.path==='/usuarioroles' || to.path==='/compras' || to.path==='/mesas' || to.path==='/parametros'){
            confirm('Acceso Denegado');
            next('/')
          }else{
            next()
            console.log('es asi '+store.state.rol);
          }
        }else{
          next();
          console.log('es asi '+store.state.pantallaVista);
        }   
      }else {
        console.log('pasa')
        next()
      }
});



Vue.use(Meta)

// Bootstrap Analytics
// Set in .env
// https://github.com/MatteoGabriele/vue-analytics
if (process.env.GOOGLE_ANALYTICS) {
    Vue.use(VueAnalytics, {
        id: process.env.GOOGLE_ANALYTICS,
        router,
        autoTracking: {
            page: process.env.NODE_ENV !== 'development',
        },
    })
}

export default router