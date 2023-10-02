import Vuex from 'vuex';
import modalStore from "@/store/modalStore";
import socketStore from "@/store/socketStore";

export default new Vuex.Store({
    modules: {
        modalStore,
        socketStore
    }
})