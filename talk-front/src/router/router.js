import Login from "@/components/login/Login.vue";
import Signup from "@/components/login/Signup.vue";
import {createRouter, createWebHistory} from "vue-router";
import Main from "@/components/room/Main.vue";
import ChattingList from "@/components/chat/ChattingList.vue";
import Logout from "@/components/login/Logout.vue";
import SearchFriend from "@/components/room/SearchFriend.vue";

const routes = [
    { path: '/login', name: 'Login', component: Login },
    { path: '/signup', name: 'Signup', component: Signup },
    { path: '/main', name: 'Main', component: Main },
    { path: '/friend/search', name: 'SearchFriend', component: SearchFriend },
    { path: '/chatting/list', name: 'ChattingList', component: ChattingList },
    { path: '/logout', name: 'Logout', component: Logout }
];

const router = createRouter({
    history : createWebHistory(),
    routes
})

export {router}