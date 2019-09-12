/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
  {
    path: '',
    // Relative to /src/views
    view: 'Dashboard',
  },
  {
    path: '/estadisticas',
    name: 'Estadisticas',
    view: 'Estadisticas',
  },
  {
    path: '/inventarios',
    name: 'Inventarios',
    view: 'Inventarios',
  },
  {
    path: '/contabilidad',
    name: 'Contabilidad',
    view: 'Contabilidad',
  },
  {
    path: '/user-profile',
    name: 'User Profile',
    view: 'UserProfile',
  },
  {
    path: '/table-list',
    name: 'Table List',
    view: 'TableList',
  },
  {
    path: '/typography',
    view: 'Typography',
  },
  {
    path: '/icons',
    view: 'Icons',
  },
  {
    path: '/maps',
    view: 'Maps',
  },
  {
    path: '/notifications',
    view: 'Notifications',
  },
  {
    path: '/upgrade',
    name: 'Upgrade to PRO',
    view: 'Upgrade',
  },
  {
    path: '/categorias',
    name: 'Categorias',
    view: 'Categorias',
  },
  {
    path: '/usuarioroles',
    name: 'Usuario y Roles',
    view: 'UsuarioRoles',
  },
  {
    path: '/productos',
    name: 'Productos',
    view: 'Productos',
  },
  {
    path: '/parametros',
    name: 'Parametros',
    view: 'Parametros',
  },
  {
    path: '/compras',
    name: 'Compras',
    view: 'Compras',
  },
  {
    path: '/mesas',
    name: 'Mesas',
    view: 'Mesas',
  },
  {
    path: '/bitacoras',
    name: 'Bitacora',
    view: 'Bitacora'
  }
]
