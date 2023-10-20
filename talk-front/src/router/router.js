import Login from "@/components/login/Login.vue";
import Signup from "@/components/login/Signup.vue";
import {createRouter, createWebHistory} from "vue-router";
import Main from "@/components/room/Main.vue";
import ChattingList from "@/components/chat/ChattingList.vue";
import Logout from "@/components/login/Logout.vue";
import SearchFriend from "@/components/room/SearchFriend.vue";
import ChatRoom from "@/components/chat/ChatRoom.vue";
import UserInfoChange from "@/components/login/UserInfoChange.vue";

const routes = [
    { path: '/', name: 'Main', component: Main },
    { path: '/login', name: 'Login', component: Login },
    { path: '/logout', name: 'Logout', component: Logout },
    { path: '/signup', name: 'Signup', component: Signup },
    { path: '/friend/search', name: 'SearchFriend', component: SearchFriend },
    { path: '/chatting/list', name: 'ChattingList', component: ChattingList },
    { path: '/chatting/room/:roomSeq', name: 'ChattingRoom', component: ChatRoom, props: true },
    { path: '/user/change', name: 'UserInfoChange', component: UserInfoChange},
];

const router = createRouter({
    history : createWebHistory(),
    routes
})

export {router}