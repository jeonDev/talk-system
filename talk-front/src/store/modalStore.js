const modalStore = {
    state: {
        code: '',
        message: '',
        isShow : false,
        callback: () => {

        }
    },
    getters: {

    },
    mutations: {
        showModal(state, modalInfo) {
            state.code = modalInfo.code;
            state.message = modalInfo.message;
            state.isShow = true;
            state.callback = modalInfo.callback;
        },
        hideModal(state) {
            state.code = '';
            state.message = '';
            state.isShow = false;
            state.callback = () => {};
        }
    }
};

export default modalStore;