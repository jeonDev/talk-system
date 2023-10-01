import client from "@/utils/client";

/** 목록 조회 */
export async function selectFriendList(request) {
    return await client
        .get('/user/friend/search?nameOrNickname=' + request)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}

export async function requestFriend(request) {
    return await client
        .post('/user/friend/request', request)
        .then((res) => {
            return res.data
        })
        .catch((err) => {
            return err.response.data;
        })
}