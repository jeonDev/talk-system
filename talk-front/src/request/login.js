import client from "@/utils/client";

/** 로그인 */
export async function login(request) {
    return await client
        .post("/login", request,{withCredentials: true})
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}

/** 회원가입 */
export async function signup(request) {
    return await client
        .post("/login/signup", request)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}

/** 토큰 재 요청 */
export async function tokenReIssue() {
    return await client
        .post("/token/reIssue", {}, {withCredentials: true})
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response;
        })
}