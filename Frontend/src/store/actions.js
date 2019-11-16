import Axios from "axios";

// https://vuex.vuejs.org/en/actions.html

export default {
  detroyToken(context){
    axios.defaults.headers,common['Authorization']='Bearer'+context.state.token
    
    if(context.getters.loggedIn){
      return new Promise((resolve,reject)=> { 
        Axios.post('/logout')
      .then(response=>{
        localStorage.removeItem('access_token')
        context.commit('destroyToken')
        resolve(response)
      })
      .catch(e=>{
        console.log(e)
        localStorage.removeItem('access_token')
        context.commit('destroyToken')
        reject(e)
      })
    })

    }
  },
  retrieveToken(context,credentials){
    return new Promise((resolve,reject)=> { 
      Axios.post('/login',{
      username:credentials.username,
      password: credentials.password,
    })
    .then(response=>{
      const token=response.data.access_token
      localStorage.setItem('access_token',token)
      context.commit('retrieveToken',token)
      resolve(response)
    })
    .catch(e=>{
      console.log(e)
      reject(e)
    })
  })
  }
}
