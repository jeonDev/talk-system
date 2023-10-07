const socketStore = {
    state: {
        chattingList : [],
        socket: null,
    },
    mutations: {
        SOCKET_CONNECT(state) {
            state.socket = new WebSocket("ws://localhost:8000/ws");
            state.socket.onopen = () => {
                console.log("연결완료");
                state.socket.onmessage = ({data}) => {
                    const jsonData = JSON.parse(data);
                    state.chattingList.push(jsonData);
                    console.log(state.chattingList)
                }
            }
        },
        SOCKET_SEND_MESSAGE(state, message) {
            state.socket.send(message);
        }
    }
};

export default socketStore;