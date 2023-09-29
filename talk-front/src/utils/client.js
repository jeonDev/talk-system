import axios from "axios";

const instance = axios.create({
    baseURL : 'http://localhost:8000'
});

instance.interceptors.request.use(
    function (config) {

        const token = sessionStorage.getItem("Authorization");
        if( token != null && token !== '')
            config.headers.Authorization = 'Bearer ' + token;

        return config;
    },
    function (error) {
        return Promise.reject(error);
    }
)

instance.interceptors.response.use(
    function (response) {
        return response;
    },
    function (error) {
        return Promise.reject(error);

    }
)

export default instance;