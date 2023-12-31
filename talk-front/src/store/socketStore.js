const socketStore = {
    state: {
        chattingList : [],
        roomSeq: null,
        socket: null,
    },
    mutations: {
        SOCKET_CONNECT(state) {
            state.socket = new WebSocket(process.env.VUE_APP_WS_URL);
            state.socket.onopen = () => {
                console.log("연결완료");
                state.socket.onmessage = ({data}) => {
                    const jsonData = JSON.parse(data);
                    if(state.roomSeq && jsonData.roomSeq == state.roomSeq) {
                        state.chattingList.push(jsonData);
                    }
                }
            }
        },
        CHATTING_LIST_RESET(state, chattingList) {
            state.chattingList = chattingList;
        },
        SET_ROOM_ENTRANCE(state, roomSeq) {
            state.roomSeq = roomSeq
        },
        SOCKET_SEND_MESSAGE(state, message) {
            state.socket.send(message);
        }
    }
};

export default socketStore;