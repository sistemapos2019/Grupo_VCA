import Axios from 'axios'

export default class RestMethods {
    constructor() {
        this.baseUri = 'http://localhost:8080/POSis/ws/'
        this.json = null
    }

    getJson(resource) {
        if (resource != null) {
            return Axios.get(this.baseUri + resource);
        }
    }

    postJson(resource, registro) {
        if (resource != null && registro != null) {
            Axios.post(this.baseUri + resource, registro)
                .then(response => {
                    console.log(response);
                })
        }
    }

    putJson(resource, registro) {
        if (resource != null && registro != null, { headers: { "Content-Type": "application/json" } }) {
            Axios.put(this.baseUri + resource, registro)
                .then(response => {
                    console.log(response);
                })
        }
    }

    deleteJson(resource, id) {
        if (resource != null && id != null) {
            Axios.delete(this.baseUri + resource + id, {
                    headers: {
                        "Origin": "http://localhost:8081/",
                        "Access-Control-Request-Method": "DELETE",
                        "Access-Control-Request-Headers": "Content-Type"
                    }
                })
                .then(response => {
                    console.log(response);
                })
        }
    }
}