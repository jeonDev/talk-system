import client from "@/utils/client";

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