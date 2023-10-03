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
            this.socket = io('http://localhost:8000', {
                path: '/ws',
                withCredentials: true
            });

            this.socket.on('connect', () => {
                console.log('connect Event !!')
            })

            this.socket.on('message', (message) => {
                console.log("message : " , message)
            })
            state.isConnected = true;
            console.log(this.socket)

        },
        SOCKET_DISCONNECT(state) {
            state.isConnected = false;
        },
        SOCKET_SEND_MESSAGE(state, message) {
            state.message = message
        }
    },
    action: {
        connect({commit}) {
            console.log("WebSocket Connected");
            this.socket.on('connect', () => {
                console.log("WebSocket connect");

                commit('SET_CONNECTED', true);
                // 연결이 성공한 후에 원하는 작업 수행
            });
        },
        disconnect({commit}) {
            this.socket.on('disconnect', () => {
                console.log("WebSocket disconnected");

                commit('SET_CONNECTED', false);
                // 연결이 끊길 때 필요한 작업을 수행
            })
        }
        // sendMessage(message) {
        //     socket.emit('message', message);
        // },
        // setupSocketListeners({ commit }) {
        //     socket.on('message', (message) => {
        //         commit('ADD_MESSAGE', message)
        //     })
        // }
    }
};

export default socketStore;