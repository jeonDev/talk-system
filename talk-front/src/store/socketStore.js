const socketStore = {
    state: {
        message: {},
        socket: null,
    },
    mutations: {
        SOCKET_CONNECT(state) {
            state.socket = new WebSocket("ws://localhost:8000/ws");
            state.socket.onopen = () => {
                console.log("연결완료");
                state.socket.onmessage = ({data}) => {
                    console.log(data);
                    state.message = Object.assign({}, data);
                }
            }
        },
        SOCKET_SEND_MESSAGE(state, message) {
            state.socket.send(message);
        }
    }
};

export default socketStore;