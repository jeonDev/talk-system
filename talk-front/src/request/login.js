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

/** 로그아웃 */
export async function logout() {
    return await client
        .post("/logout",{},{withCredentials:true})
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response;
        })
}

/** 사용자 정보 조회 */
export async function selectUserInfo() {
    return await client
        .get("/user/getUserInfo")
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response;
        })
}

/** 사용자 정보 조회 */
export async function updateUserInfo(request) {
    return await client
        .post("/user/info/update", request)
        .then((res) => {
            return res.data;
        })
        .catch((err) => {
            return err.response.data;
        })
}