import client from "@/utils/client";

/** 채팅방 초대(개인) */
export async function createPrivateRoom(request) {
    return await client
        .post('/user/room/private/invite',request)
        .then((res) => {
            return res.data
        })
        .catch((err) => {
            return err.response.data;
        })
}

/** 채팅방 목록 조회 */
export async function selectRoomList() {
    return await client
        .get('/user/room/list')
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}