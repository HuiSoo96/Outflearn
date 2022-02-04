function selectOptionBuild(data) {

    var html = `<p>강좌 선택: <select id="configRoom">`;

    data.forEach(item => {
        html += `<option value="${item.class_num}">${item.class_title}</option>`
    })
    html += '</select></p>'
    return html
}

function inputHtmlBuild(data) {

    // 강좌 번호 option tag
    var html = `<p>강좌 선택: <select id="setClass">`;

    data.forEach(item => {
        html += `<option value="${item.class_num}">${item.class_title}</option>`
    })
    html += '</select></p>'

    // 제목 input

    html += '<p>제목 : <input type="text" id="setTitle"></p>'

    // 시간 input + select

    html += ` <p>날짜 : <input type="date" id="setDate"></p>`

    return html
}

function resultRes(bool) {
    if (bool) {
        location.reload(true)
    } else {
        Swal.fire({
            type: 'error',
            title: '에러가 발생했습니다.',
            text: 'Q class로 문의 해주세요',
            confirmButtonText: '확인'
        })
            .then((result) => {
                location.reload(true)
            })
    }
}

function acceptReq(user_num) {
    $.ajax({
        url: `acceptReq?user_num=${user_num}`,
        success: function (bool) {
            resultRes(bool)
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function deniReq(user_num) {
    $.ajax({
        url: `deniReq?user_num=${user_num}`,
        success: function (bool) {
            resultRes(bool)
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function userEnabled(user_num) {
    Swal.fire({
        type: 'warning',
        title: '해당 유저를 비활성화 시키시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancleButtonText: '취소'
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `userDisabled?user_num=${user_num}`,
                    success: function (bool) {
                        resultRes(bool)
                    },
                    error: function (err) {
                        console.log(err);
                    }
                })
            }
        })
}

function userDisabled(user_num) {
    Swal.fire({
        type: 'warning',
        title: '해당 유저를 활성화 시키시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancleButtonText: '취소'
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `userEnabled?user_num=${user_num}`,
                    success: function (bool) {
                        resultRes(bool)
                    },
                    error: function (err) {
                        console.log(err);
                    }
                })
            }
        })
}

function handleError() {
    Swal.fire({
        type: 'error',
        title: 'Ooops...',
        text: '혹시 만드신 강의가 없으신가요?',
        confirmButtonText: '강의 만들기'
    })
        .then((result) => {
            if (result.value) {
                location.href = 'ClassInfoInsertForm'
            }
        })
}

function handleCriticalError() {
    Swal.fire({
        type: 'error',
        title: '심각한 오류 발생!',
        text: '관리자에게 문의 주세요.'
    })
}

function handleSuccess(bool) {
    if (bool) {
        Swal.fire({
            type: 'success',
            title: '정상 처리 되셨습니다.'
        })
            .then((result) => {
                location.reload()
            })
    } else {
        Swal.fire({
            type: 'error',
            title: '처리 중에 오류가 발생했습니다.',
            text: '다시 한번 시도해주세요.',
            footer: '혹은 관리자에게 문의 바랍니다.'
        })
    }
}

function deleteClass(class_num, class_title) {
    Swal.fire({
        type: 'warning',
        title: `${class_title}을 정말 삭제하시겠습니까?`,
        text: '복구는 불가능합니다.',
        confirmButtonText: '삭제',
        showCancelButton: true,
        showCloseButton: true
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `deleteClass?class_num=${class_num}`,
                    success: function (bool) {
                        handleSuccess(bool)
                    },
                    error: function (err) {
                        handleCriticalError()
                    }
                })
            }
        })
}

function deleteRoadmap(roadmap_num, roadmap_title) {
    Swal.fire({
        type: 'warning',
        title: `${roadmap_title}을 정말 삭제하시겠습니까?`,
        text: '복구는 불가능합니다.',
        confirmButtonText: '삭제',
        showCancelButton: true,
        showCloseButton: true
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `deleteRoadmap?roadmap_num=${roadmap_num}`,
                    success: function (bool) {
                        handleSuccess(bool)
                    },
                    error: function (err) {
                        handleCriticalError()
                    }
                })
            }
        })
}

function updateClass(class_num, class_title) {
    Swal.fire({
        type: 'info',
        title: `${class_title} 수정 영역 선택`,
        html: `<button onclick="location.href='ClassIntroduceUpdateForm?class_num=${class_num}'" class="updateDetailBtn">강의 소개</button>
        	   <button onclick="location.href='ClassDataInsertPlus?class_num=${class_num}'" class="updateDetailBtn">영상 추가</button>
        	   <button onclick="location.href='ClassDataUpdateForm?class_num=${class_num}'" class="updateDetailBtn">영상 수정</button>`,
        showCancelButton: false,
        showCloseButton: true,
        showConfirmButton: false
    })
}

function updateRoadmap(roadmap_num, roadmap_title) {
    Swal.fire({
        type: 'info',
        title: `${roadmap_title} 수정 영역 선택`,
        html: `<button onclick="location.href='Modify_P1?roadNum=${roadmap_num}'" class="updateDetailBtn">로드맵 1p수정</button>
        	   <button onclick="location.href='Modify_P2?roadNum=${roadmap_num}'" class="updateDetailBtn">로드맵 2p수정</button>`,
        showCancelButton: false,
        showCloseButton: true,
        showConfirmButton: false
    })
}

function handleProfileSuccess(bool) {
    if (bool) {
        Swal.fire({
            type: 'success',
            title: '정상 처리 되셨습니다.'
        })
            .then((result) => {
                location.href = 'logout'
            })
    } else {
        Swal.fire({
            type: 'error',
            title: '처리 중에 오류가 발생했습니다.',
            text: '다시 한번 시도해주세요.',
            footer: '혹은 관리자에게 문의 바랍니다.'
        })
    }
}

function nickSubmit(form) {
    Swal.fire({
        type: 'info',
        title: '설정 변경 시 로그아웃 처리가 됩니다.',
        text: '재로그인 해주시길 바랍니다.',
        confirmButtonText: '변경',
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: '취소'
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `updateNickname?nickname=${form.nickname.value}`,
                    success: function (bool) {
                        handleProfileSuccess(bool)
                    },
                    error: function (err) {
                        handleCriticalError()
                    }
                })
            }
        })
    return false
}

function emailSubmit(form) {
    Swal.fire({
        type: 'info',
        title: '설정 변경 시 로그아웃 처리가 됩니다.',
        text: '재로그인 해주시길 바랍니다.',
        confirmButtonText: '변경',
        showCancelButton: true,
        showCloseButton: true,
        cancelButtonText: '취소'
    })
        .then((result) => {
            if (result.value) {
                $.ajax({
                    url: `updateEmail?emailBefore=${form.emailBefore.value}&emailAter=${form.emailAfter.value}`,
                    method: 'post',
                    success: function (bool) {
                        handleProfileSuccess(bool)
                    },
                    error: function (err) {
                        handleCriticalError()
                    }
                })
            }
        })
    return false
}

function firstChk(beforePw, afterPw, afterPwChk) {
    $.ajax({
        url: `beforePwChk?beforePw=${beforePw}`,
        method: 'post',
        success: function (bool) {
            if (bool) {
                secondChk(afterPw, afterPwChk)
            } else {
                Swal.fire({
                    type: 'warning',
                    title: '현재 비밀번호와 다릅니다.',
                    text: '다시 한번 확인해 주세요.'
                })
            }
        },
        error: function (err) {
            handleCriticalError()
        }
    })
}

function secondChk(afterPw, afterPwChk) {
    if (afterPw.length > 7) {
        lastChk(afterPw, afterPwChk)
    } else {
        Swal.fire({
            type: 'warning',
            title: ' 비밀번호는 8자리 이상 입력해주세요.'
        })
    }
}

function lastChk(afterPw, afterPwChk) {
    if (afterPw === afterPwChk) {
        $.ajax({
            url: `updatePw?afterPw=${afterPw}`,
            method: 'post',
            success: function (bool) {
                handleProfileSuccess(bool)
            },
            error: function (err) {
                handleCriticalError()
            }
        })
    } else {
        Swal.fire({
            type: 'warning',
            title: '새 비밀번호를 확인해 주세요',
            text: '비밀번호가 같지 않습니다.'
        })
    }
}

function handleUpdatePw(beforePw, afterPw, afterPwChk) {
    firstChk(beforePw, afterPw, afterPwChk)
}

function pwSubmit(form) {
    var beforePw = form.beforePw.value
    var afterPw = form.afterPw.value
    var afterPwChk = form.afterPwChk.value

    handleUpdatePw(beforePw, afterPw, afterPwChk)

    return false
}

$(function () {
    $('.configLiveRoom').on('click', function () {
        $.ajax({
            url: 'getMyClass',
            success: function (data) {
                Swal.fire({
                    title: '간단하게 방 설정하기',
                    html: selectOptionBuild(data),
                    allowOutsideClick: false,
                    showCancelButton: true,
                    showCloseButton: true
                })
                    .then((result) => {
                        if (result.value) {
                            var room = $('#configRoom').val()
                            location.href = `casterRoom?room=${room}`
                        }
                    })
            },
            error: function (err) {
                handleError()
            }
        })
    })

    $('#setLiveSchedule').on('click', function () {
        $.ajax({
            url: `getMyClass`,
            success: function (data) {
                Swal.fire({
                    title: '라이브 스케쥴 세팅',
                    html: inputHtmlBuild(data),
                    allowOutsideClick: false,
                    showCancelButton: true,
                    showCloseButton: true
                })
                    .then((result) => {
                        if (result.value) {
                            var setClass = $('#setClass').val()
                            var setTitle = $('#setTitle').val()
                            var setDate = $('#setDate').val()
                            var repeatDate = $('#repeatDate').val()
                            $.ajax({
                                url: `setLiveSchedule?setClass=${setClass}&setTitle=${setTitle}&setDate=${setDate}&repeatDate=${repeatDate}`,
                                success: function (bool) {
                                    if (bool) {
                                        Swal.fire({
                                            type: 'info',
                                            title: '정상적으로 처리 되셨습니다.'
                                        })
                                    } else {
                                        Swal.fire({
                                            type: 'error',
                                            title: '죄송합니다. 데이터 처리 중 오류가 발생했습니다.',
                                            text: '다시 한번 실행해주세요.',
                                            footer: '혹은 관리자에게 문의바랍니다.'
                                        })
                                    }
                                },
                                error: function (err) {
                                    Swal.fire({
                                        type: 'warning',
                                        title: '죄송합니다. 심각한 오류가 발생했습니다.',
                                        text: '관리자에게 문의 바랍니다.'
                                    })
                                }
                            })
                        }
                    })
            },
            error: function (err) {
                handleError()
            }
        })
    })

    $('#setNickname').keyup(function () {
        if ($(this).val() != $(this).attr('default')) {
            $('#updateNickname').prop('disabled', false)
        } else {
            $('#updateNickname').prop('disabled', true)
        }
    })

    $('.setEmail').keyup(function () {
        var EBefore = $('#setEmailBefore')
        var EAfter = $('#setEmailAfter')

        if ((EBefore.val() == EBefore.attr('default')) && (EAfter.val() == EAfter.attr('default'))) {
            $('#updateEmail').prop('disabled', true)
        } else {
            $('#updateEmail').prop('disabled', false)
        }
    })

    $('#emailSelect').on('change', function () {
        $('#setEmailAfter').prop('value', $(this).val())
    })

})