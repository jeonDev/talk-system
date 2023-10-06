const socketStore = {
    state: {
        message: '',
        socket: null,
        isConnected: false
    },
    getters: {
    },
    mutations: {
        SOCKET_CONNECT(state) {
            state.socket = new WebSocket("ws://localhost:8000/ws");
            state.socket.onopen = () => {
                state.isConnected = true;
                console.log("연결완료");
                state.socket.onmessage = ({data}) => {
                    state.message = data;
                }
            }
        },
        SOCKET_SEND_MESSAGE(state, message) {
            state.socket.send(message);
        }
    }
};

export default socketStore;