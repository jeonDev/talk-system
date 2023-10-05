import {io} from "socket.io-client";

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
            console.log('SOCKET_CONNECT')
            state.socket = io('http://localhost:8000', {
                path: '/ws',
                withCredentials: true
            });

            state.socket.on('connect', () => {
                console.log('connect Event !!')
                state.isConnected = true;
            })

            state.socket.on('message', (message) => {
                console.log("message : " , message)
            })

            console.log(state.socket)
        },
        SOCKET_DISCONNECT(state) {
            if(state.socket) {
                state.socket.disconnect();
            }
            state.isConnected = false;
        },
        SOCKET_SEND_MESSAGE(state, message) {
            console.log(message);
            state.socket.emit('message', message);
        }
    }
};

export default socketStore;