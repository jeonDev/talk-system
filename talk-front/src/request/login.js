import client from "@/utils/client";

/** 로그인 */
export async function login(request) {
    return await client
        .post("/login", request)
        .then((res) => {
            return res;
        })
        .catch((err) => {
            return err;
        })
}

export async function signup(request) {
    return await client
        .post("/signup", request)
        .then((res) => {
            return res;
        })
        .catch((err) => {
            return err;
        })
}