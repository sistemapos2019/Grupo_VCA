// https://vuex.vuejs.org/en/getters.html

export default {
  //
  loggedIn(state){
    return state.token!= null
  }
}
