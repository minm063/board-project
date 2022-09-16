// id, idMsg, pw, pwMsg, pwCheck, pwCheckMsg
const idCheck = document.querySelector('#id');
const pw = document.querySelector('#pw');
const pwCheck = document.querySelector('#pwCheck');
const name = document.querySelector('#name');
const birth = document.querySelector('#birth');
let flag1, flag2, flag3, flag4;
// const submit = document.querySelector('#submit');
// submit.setAttribute('disabled', '');
// if : all true ->
// submit.removeAttribute('disabled'); -> submit
// }function checkId() {
//     const id = $('#id').val();
//     $.ajax({
//         url: './idCheck',
//         type: 'post',
//         data: {id: id},
//         success:function (cnt) {
//             if (cnt !== 0) {
//                 // id 사용불가능
//                 idMsg.textContent('사용가능한 아이디입니다.');
//             }
//             else {
//                 idMsg.textContent('이미 사용중인 아이디입니다.')
//             }
//         },
//         error:function () {
//             alert('error');
//         }
//     });
// }
// function pwCheck() {
// }


pw.addEventListener('blur', (event) => {
    const pwMsg = document.querySelector('#pwMsg');
    const pwValue = pw.value;
    const rePw = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

    if(pwValue.length == null || pwValue.length === 0) {
        // 빈칸
        pwMsg.textContent = '필수 정보입니다';
        flag1 = false;
    }
    else if (rePw.test(pwValue)) {
        // 조건 o
        pwMsg.textContent = '';
        flag1 = true;

    }
    else {
        // 조건 x
        pwMsg.textContent = '8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.';
        flag1 = false;

    }
});
pwCheck.addEventListener('blur', (event) => {
    const pwCheckMsg = document.querySelector('#pwCheckMsg');
    if (pw.value === pwCheck.value) {
        pwCheckMsg.textContent = '';
        flag2 = true;
    }
    else {
        pwCheckMsg.textContent = '비밀번호가 일치하지 않습니다.';
        flag2 = false;
    }
});
idCheck.addEventListener('blur', (event) => {
    let idMsg = document.querySelector('#idMsg');
    let idValue = idCheck.value;
    let reId = /[a-z0-9-_]{4,19}$/g;

    const id = $('#id').val();
    $.ajax({
        url: './idCheck',
        type: 'post',
        data: {id: id},
        dataType: 'json',
        success: function (cnt) {
            if(idValue.length == null || idValue.length === 0) {
                // 빈칸
                idMsg.textContent = '필수 정보입니다';
                flag3 = false;
            }
            else if (!reId.test(idValue)) {
                // 정규표현식 조건 x
                idMsg.textContent = '5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.';
                flag3 = false;
            }
            else if (cnt !== 0) {
                // id 중복
                idMsg.textContent = '이미 사용중인 아이디입니다.';
                flag3 = false;
            }
            else {
                // 조건 o
                idMsg.textContent = '사용가능한 아이디입니다.';
                flag3 = true;
            }
        },
        error: function () {
            alert('id check error');
        }
    });
});
birth.addEventListener('blur', (event) => {
    const birthMsg = document.querySelector('#birthMsg');
    if(birth.value.length == null || birth.value.length === 0) {
        birthMsg.textContent = '태어난 년도 4자리를 정확하게 입력하세요.';
        flag4 = false;
    }
    else {
        birthMsg.textContent = '';
        flag4 = true;
    }
});
function submitCheck() {
    if (flag1 && flag2 && flag3 && flag4) {
        return true;
    }
    else {
        alert('필수 항목을 확인해주세요.');
        return false;
    }
}