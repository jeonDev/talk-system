import Vuex from 'vuex';
import modalStore from "@/store/modalStore";
import socketStore from "@/store/socketStore";
import loadingStore from "@/store/loadingStore";

export default new Vuex.Store({
    modules: {
        modalStore,
        socketStore,
        loadingStore
    }
})