import Login from "@/components/Login.vue";
import Signup from "@/components/Signup.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    { path: '/login', component: Login },
    { path: '/singup', component: Signup }
];

const router = createRouter({
    history : createWebHistory(),
    routes
})

export {router}