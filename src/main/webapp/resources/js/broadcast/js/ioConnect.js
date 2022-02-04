try {
    //    var socket = io.connect('https://192.168.10.139:3000', { rejectUnauthorized: false });
    var socket = io.connect('https://localhost:3000', { rejectUnauthorized: false });
} catch (error) {
    Swal.fire({
        type: 'error',
        title: '인증된 인증서가 유료에요... ㅠㅠ',
        text: '제성합니다... ㅠㅠ',
        showCancelButton: true,
        confirmButtonText: '이동',
        cancelButtonText: '취소',
    })
        .then((result) => {
            if (result.value) {
                Swal.fire({
                    type: 'info',
                    title: 'IMG 파일 업로드 예정',
                    confirmButtonText: '이동',
                    allowOutsideClick: false
                })
                    .then((result) => {
                        if (result.value) {
                            //location.href = 'https://192.168.10.139:3000/certification'
                            location.href = 'https://localhost:3000/certification'
                        }
                    })
            } else {
                Swal.fire({
                    type: 'warning',
                    title: '전 페이지로 돌아갑니다',
                    confirmButtonText: '이동',
                    allowOutsideClick: false
                })
                    .then((result) => {
                        if (result.value) {
                            history.back()
                        }
                    })
            }
        })
}