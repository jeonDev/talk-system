import client from "@/utils/client";

/** 로그인 */
export async function login(request) {
    return await client
        .post("/login", request,{withCredentials: true})
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err;
        })
}

export async function signup(request) {
    return await client
        .post("/login/signup", request)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err;
        })
}