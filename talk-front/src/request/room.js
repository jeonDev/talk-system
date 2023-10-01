import client from "@/utils/client";

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