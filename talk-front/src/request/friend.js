import client from "@/utils/client";

/** 목록 조회 */
export async function selectRecommendFriendList(request) {
    const parameter = 'nameOrNickname=' +request.nameOrNickname +
        '&currentPage=' + request.currentPage +
        '&perPage=' + request.perPage
    return await client
        .get('/user/friend/search?' + parameter)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}

/** 친구 목록 조회 */
export async function selectFriendList() {
    return await client
        .get('/user/friend/list')
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}

/** 친구 요청 */
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