import client from "@/utils/client";

export async function selectRoomChattingList(request) {
    return await client
        .get(`/user/chat/list/${request}`)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data
        })
}