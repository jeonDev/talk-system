const loadingStore = {
    state: {
        isLoading : false
    },
    mutations: {
        showLoading(state) {
            state.isLoading = true;
        },
        hideLoading(state) {
            state.isLoading = false;
        }
    }
};

export default loadingStore;