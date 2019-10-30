// https://vuex.vuejs.org/en/mutations.html

export default {
  //
  retrieveToken(state,token){
    state.token=token
  },
  destroyToken(state){
    state.token=null
  }

}
