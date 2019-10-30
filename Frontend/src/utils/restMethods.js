import Axios from 'axios'

export default class RestMethods {
    constructor() {
        this.baseUri = 'http://localhost:8080/Backend/ws/'
        this.json = null
    }

    getJson(resource) {
        if (resource != null) {
            return Axios.get(this.baseUri + resource);
        }
    }

    postJson(resource, registro) {
        if (resource != null && registro != null) {
            return Axios.post(this.baseUri + resource, registro);
        }
    }

    putJson(resource, registro) {
        if (resource != null && registro != null, { headers: { 'Content-Type': 'application/json' } }) {
            return Axios.put(this.baseUri + resource, registro);
        }
    }

    deleteJson(resource, id) {
        if (resource != null && id != null) {
            Axios.delete(this.baseUri + resource + id, {
                headers: {
                    'Origin': 'http://localhost:8081/',
                    'Access-Control-Request-Method': 'DELETE',
                    'Access-Control-Request-Headers': 'Content-Type',
                },
            })
                .then(response => {
                    console.log(response)
                })
        }
    }
}
