import Login from "@/components/Login.vue";
import Signup from "@/components/Signup.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    { path: '/login', name: 'Login', component: Login },
    { path: '/signup', name: 'Signup', component: Signup }
];

const router = createRouter({
    history : createWebHistory(),
    routes
})

export {router}