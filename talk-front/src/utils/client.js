import axios from "axios";
import {router} from "@/router/router";
import {tokenReIssue} from "@/request/login";
import store from "@/store/index";

const unAuthorizationResponseObject = {
    code: 'UNAUTHORIZED',
    message: '로그인 후 이용 해 주세요.',
    callback: () => {
        router.push({name: "Login"})
    }
}

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
    async function (error) {

        const originRequest = error.config;

        if(error.response.status === 401 && !originRequest._retry) {
            const accessToken = sessionStorage.getItem("Authorization");
            if(!accessToken) {
                store.commit('showModal', unAuthorizationResponseObject);
                return Promise.resolve(error.response)
            }

            const res = await tokenReIssue();

            if(res.status === 'SUCCESS') {
                const token = res.data.token;
                sessionStorage.setItem('Authorization', token);
                originRequest._retry = true;

                originRequest.headers = {
                    ...originRequest.headers
                }

                return instance(originRequest);
            } else {
                sessionStorage.removeItem('userSeq');
                sessionStorage.removeItem('name');
                sessionStorage.removeItem('nickname');
                sessionStorage.removeItem("Authorization");
            }
        } else if (error.response.status === 401) {
            store.commit('showModal', unAuthorizationResponseObject);
            return new Promise(() => {});
        }
        return Promise.reject(error);
    }
)

export default instance;