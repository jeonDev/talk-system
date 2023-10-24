import client from "@/utils/client";

export default async function fileUpload(request) {
    return await client
        .post('/user/file/upload', request, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}