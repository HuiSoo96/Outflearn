function handleCriticalError() {
    Swal.fire({
        type: 'error',
        title: '심각한 오류 발생!',
        text: '관리자에게 문의 주세요.'
    })
}

document.addEventListener('DOMContentLoaded', function () {

    var calendarEl = document.getElementById('calendar');

    /*
    Array(1)
    0: { live_num: 1, class_num: 1, live_title: "TEST페이지", live_author: "TEST강사", live_time: "2019-08-15" }
    length: 1
    __proto__: Array(0)
    {
        id: 'test',
            title: 'All Day Event',
                description: 'asdf',
                    start: '2019-07-01',
                        color: 'yellow',
            },
    {
        title: 'Long Event',
            start: '2019-07-07',
                end: '2019-07-10',
                    description: 'asdf',
            },
    {
        title: 'Click for Google',
            url: 'http://google.com/',
                start: '2019-07-28'
    }

*/
    var _events = []

    $.ajax({
        url: `liveCalendar`,
        method: 'get',
        success: function (data) {
            data.forEach(item => {
                _events.push({
                    id: item.live_num,
                    title: item.live_title,
                    start: item.live_time
                })
            });

            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['interaction', 'dayGrid', 'timeGrid'],
                defaultView: 'dayGridMonth',
                eventLimit: true,
                views: {
                    timeGrid: {
                        eventLimit: 5
                    }
                },
                eventClick: function (info) {
                    $.ajax({
                        url: `livePopup?live_num=${info.event.id}`,
                        method: 'get',
                        success: function (data) {
                            if (data) {
                                Swal.fire({
                                    title: data.class_title,
                                    html:
                                        `
                                    <img class="card-img" src="/Outflearn/resources/uploadImage/${data.class_img}" alt="이미지가 없어!">
                                    <p>강좌소개 : ${data.class_intro}</p>
                                    <p>가격 : ${data.class_price}</p>
                                    <button onclick="inBasket(${data.class_num},'${data.class_title}')" class="addBtn">장바구니</button>
                                    `
                                })
                            } else {
                                Swal.fire({
                                    title: '<strong>Live Only</strong>',
                                    type: 'info'
                                })
                            }
                        },
                        error: function (err) {
                            console.log(err)
                        }
                    })
                },
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                events: _events
            });

            calendar.render();

        },
        error: function (err) {
            console.log(err);
        }
    })
});

function inBasket(class_num, class_title) {
    $.ajax({
        url: `basket?class_num=${class_num}`,
        method: 'get',
        success: function (res) {
            if (res > 0) {
                Swal.fire({
                    type: 'info',
                    title: `${class_title}이 장바구니에 담겼습니다.`
                })
            } else {
                Swal.fire({
                    type: 'warning',
                    title: `오류가 발생하였습니다.`,
                    text: `${class_title}을 장바구니에 담는 도중 오류가 발생했습니다. 다시 한번 시도해주세요.`,
                    footer: '혹은 관리자에게 문의바랍니다.'
                })
            }
        },
        error: function (err) {
            handleCriticalError()
        }
    })
}
