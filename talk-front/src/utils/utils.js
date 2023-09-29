/** 휴대전화 번호 정규식 체크 */
const checkPhoneNumber = (value) => {
    const regex = /^d{3}-d{3,4}-d{4}$/;
    if (regex.test(value)) return true;
    return false;
}

/** 이메일 정규식 체크 */
const checkEmail = (value) => {
    const regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regex.test(value) === true) return true;
    return false;
    // const format = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$/;
    // return format.test(email);
}

/**
 * 비밀번호 정규식 체크
 * 숫자와 문자 포함 형태의 8~20자리 이내의 암호 정규식 *
 * */
const checkPassword = (value) => {
    const regex = /^[A-Za-z0-9]{8,20}$/;
    if (regex.test(value) === true) return true;
    return false;
}
/** 아이디 정규식 체크 */
const checkId = (value) => {
    const reg = /^[a-zA-Z0-9]{8,16}$/;
    if(value.match(reg)) return true;
    return false;
}

const MessageEnum = {
    ID_CHECK : "아이디는 영문 & 숫자 8~16자 사이로 입력 해 주세요.",
    PASSWORD_CHECK : "비밀번호는 영문 & 숫자 8~20자 사이로 입력 해 주세요.",
    NAME_CHECK : "이름을 입력해주세요.",
    NICKNAME_CHECK : "닉네임을 입력해주세요.",
    PHONE_CHECK : "핸드폰번호를 입력해주세요.",
    EMAIL_CHECK : "이메일을 입력해주세요."
}

export {
    checkPassword,
    checkEmail,
    checkPhoneNumber,
    checkId,
    MessageEnum
}