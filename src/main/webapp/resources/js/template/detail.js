var count_sum = 0
var hour_sum = 0
var minite_sum = 0
var second_sum = 0


$(document).ready(function () {

    avg_rating($('#rating').val(), $('#rating-tag'))

    var ReviewStar = document.querySelectorAll(".review-star")

    var classes = Array.prototype.map.call(ReviewStar, function (element) {
        return element.value
    });

    for (var i = 0; i < classes.length; i++) {
        rating_star(classes[i], $('.show-star')[i])
    }

    var review_rating = document.querySelectorAll(".review_rating")

    var ReviewRating = Array.prototype.map.call(review_rating, function (element) {
        return element.value
    })

    for (var i = 0; i < ReviewRating.length; i++) {
        rating_star(ReviewRating[i], $('.reviews_rating')[i])
    }

    var playlist = ''
    var video_id = new Array()
    var playOne = new Array()
    var playlist_id = new Array()

    if ($('.nav-tabs > li > a').text().indexOf('대쉬보드') != -1) {
        $(".nav-tabs > li > a:contains('대쉬보드')").css({ 'border-bottom': '2px solid #6473ff' });
        $.ajax({
            type: 'GET',
            url: 'DetailDashBoard',
            success: function (play_id) {
                DashboardHeader()

                for (var i = 0; i < play_id.length; i++) {
                    video_id[i] = play_id[i];
                    if (play_id[i].indexOf("v=") != -1) {

                        play_id[i] = play_id[i].split('v=', 2)[1]
                        playOne[i] = play_id[i]
                        playlist = "https://www.googleapis.com/youtube/v3/videos?id= " + play_id[i] + "&part=contentDetails,snippet&key=AIzaSyAKpVZhMIKzF0zAD17yeVygQWNfL7MCCzc";
                        DashboardOne(playlist, playOne[i])
                    } else if (play_id[i].indexOf("list") != -1) {

                        play_id[i] = play_id[i].split('list=', 2)[1]
                        playlist_id[i] = play_id[i]
                        playlist = "https://www.googleapis.com/youtube/v3/playlistItems?playlistId=" + play_id[i] + "&part=contentDetails,snippet&maxResults=5&key=AIzaSyAKpVZhMIKzF0zAD17yeVygQWNfL7MCCzc";
                        console.log(playlist)
                        DashboardList(playlist, playlist_id[i])

                    }
                }

            },
            error: function (err) {
                console.log('실패!!')
            }
        })
    }

    $('div#page-switch > div:not(' + $('div#main nav li a.selected').attr('href') + ')').hide()

    $('.nav-tabs > li > a').on('click', function () {

        $(this).css({ 'border-bottom': '2px solid #6473ff' })
        $(this).parents().siblings().children().css({ 'border-bottom': '' })

        $('div#main nav li a').removeClass('selected')
        $(this).addClass('selected')
        $('div#page-switch > div').hide()
        $($(this).attr('href')).show()

        if ($(this).text() == '대쉬보드') {
            DashboardHeader()
            for (var i = 0; i < video_id.length; i++) {
                var play_id = new Array()
                if (video_id[i].indexOf("v=") != -1) {

                    play_id[i] = video_id[i].split('v=', 2)[1]
                    playOne[i] = play_id[i]
                    playlist = "https://www.googleapis.com/youtube/v3/videos?id= " + play_id[i] + "&part=contentDetails,snippet&key=AIzaSyAKpVZhMIKzF0zAD17yeVygQWNfL7MCCzc";
                    DashboardOne(playlist, playOne[i])

                } else if (video_id[i].indexOf("list") != -1) {
                    play_id[i] = video_id[i].split('list=', 2)[1]
                    playlist_id[i] = play_id[i]
                    playlist = "https://www.googleapis.com/youtube/v3/playlistItems?playlistId=" + play_id[i] + "&part=contentDetails,snippet&maxResults=5&key=AIzaSyAKpVZhMIKzF0zAD17yeVygQWNfL7MCCzc";
                    DashboardList(playlist, playlist_id[i])

                }
            }

            count_sum = 0
            hour_sum = 0
            minite_sum = 0
            second_sum = 0

        } else if ($(this).text() == '질문&답변') {
            ReviewAnswer()
        }
    })

    $('#main, #playlist').css({ 'width': '86%' })
    $('#box').css({ 'top': $('#menu').height() * 2 })

    $('.reply_group').hide()
    ReviewReply()

    $("#Question-btn").on('click', function () {
        $("#QuestionForm").modal({ backdrop: "static" })
    });

    $('#Review-btn').on('click', function () {
        $('#ReviewForm').modal({ backdrop: 'static' })
    })

    if ($(window).outerWidth() < 1025) {
        $('#box').css({ 'top': '0' })
        $('#box').find('.btns').css({ 'border': 'none' })
        $('#study_btn').removeClass('col-md-3')
    } else {
        $('#box').css({ 'top': $('#menu').height() * 2 })
        $('#box').find('.btns').css({ 'border': '1px solid #acacac' })
        $('#study_btn').addClass('col-md-3')
    }

    $(window).resize(function () {
        if ($(window).outerWidth() < 1025) {
            $('#box').css({ 'top': '0' })
            $('#box').find('.btns').css({ 'border': 'none' })
            $('#study_btn').removeClass('col-md-3')
        } else {
            $('#box').css({ 'top': $('#menu').height() * 2 })
            $('#box').find('.btns').css({ 'border': '1px solid #acacac' })
            $('#study_btn').addClass('col-md-3')
        }
    })

    var location = document.location.href;

    if (location.indexOf('#Question') !== -1) {
        $('.nav-tabs > li > a[href$=#Question]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#Question]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#Question]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#Question]').attr('href')).show()
    } else if (location.indexOf('#review') !== -1) {
        $('.nav-tabs > li > a[href$=#review]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#review]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#review]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#review]').attr('href')).show()
    } else if (location.indexOf('#LectureIntroduce') !== -1) {
        $('.nav-tabs > li > a[href$=#LectureIntroduce]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#LectureIntroduce]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#LectureIntroduce]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#LectureIntroduce]').attr('href')).show()
    } else if (location.indexOf('#dashboard') !== -1) {
        $('.nav-tabs > li > a[href$=#dashboard]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#dashboard]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#dashboard]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#dashboard]').attr('href')).show()
    }

    var optionValue = document.querySelectorAll(".option_value")

    var optionStar = Array.prototype.map.call(optionValue, function (element) {
        return element.value
    });

    var submitAction = function (e) {
        e.preventDefault();
        e.stopPropagation();
    };

    $('form#Review').bind('submit', submitAction)
    $('.input').on('click', function () {

        console.log($('output').text())
        $('#user_star').attr('value', $('output#review-rating').text())

        if ($('output').text() == '0') {
            $('form#Review').bind('submit', submitAction)
        } else if ($('output').text() != '0') {
            $('form#Review').unbind()
        }
    })

    var location = document.location.href;

    if (location.indexOf('#Question') !== -1) {
        $('.nav-tabs > li > a[href$=#Question]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#Question]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#Question]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#Question]').attr('href')).show()
    } else if (location.indexOf('#review') !== -1) {
        $('.nav-tabs > li > a[href$=#review]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#review]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#review]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#review]').attr('href')).show()
    } else if (location.indexOf('#LectureIntroduce') !== -1) {
        $('.nav-tabs > li > a[href$=#LectureIntroduce]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#LectureIntroduce]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#LectureIntroduce]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#LectureIntroduce]').attr('href')).show()
    } else if (location.indexOf('#dashboard') !== -1) {
        $('.nav-tabs > li > a[href$=#dashboard]').css({ 'border-bottom': '2px solid #6473ff' })
        $('.nav-tabs > li > a[href$=#dashboard]').parents().siblings().children().css({ 'border-bottom': '' })
        $('div#main nav li a').removeClass('selected')
        $('div#main nav li a[href$=#dashboard]').addClass('selected')
        $('div#page-switch > div').hide()
        $($('div#main nav li a[href$=#dashboard]').attr('href')).show()
    }

    var optionValue = document.querySelectorAll(".option_value")

    var optionStar = Array.prototype.map.call(optionValue, function (element) {
        return element.value
    });

    var submitAction = function (e) {
        e.preventDefault();
        e.stopPropagation();
    };

    $('form#Review').bind('submit', submitAction)
    $('.input').on('click', function () {
        var rating = $('output#review-rating').text().substr($('output#review-rating').text().length - 1, 1)

        $('#user_star').attr('value', rating)

        if ($('output').text() == '0') {
            alert("별점을 주세요.")
            $('form#Review').bind('submit', submitAction)
        } else if ($('output').text() != '0') {
            $('form#Review').unbind()
        }
    })

    var ReviewnumValue = document.querySelectorAll(".modal-review-num")

    var ModalReviewnum = Array.prototype.map.call(ReviewnumValue, function (element) {
        return element.value
    });

    $('.review-reply-update').on('click', function () {
        $(this).next().modal({ backdrop: "static" })
    })

    $('form.ReviewUpdate').bind('submit', submitAction)
    $('.input-update').on('click', function () {
        var rating = $('output#review-rating-update').text().substr($('output#review-rating-update').text().length - 1, 1)

        $('.user_star_update').attr('value', rating)
        console.log($('.user_star_update').val(), 'test')
        console.log(typeof $('.user_star_update').val(), 'testtest')
        if ($('output#review-rating-update').text() == '0') {
            alert("별점을 주세요.")
            $('form.ReviewUpdate').bind('submit', submitAction)
        } else if ($('output.update').text() != '0') {
            $('form.ReviewUpdate').unbind()
        }
    })

})

function DashboardHeader() {
    var sub_head = $('#subhead').val();


    $('#playlist').html(
        `<div class='panel panel-default table-div'>
    <h1> ` + sub_head + `<h1>
    <div class='table-responsive-lg'>
    <table class='table youtube'>
    </div>`
    )
}

function DashboardOne(video_list, playlist_id) {
    var data = $('#data').val();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: video_list,
        success: function (vi_list) {

            var count = 0;
            var timer = 0;
            var hour = 0;
            var min = 0;
            var sec = 0;

            var video_id = vi_list.items[0].id;
            var video_title = vi_list.items[0].snippet.title
            var duration = getVideos(video_id);

            $('.youtube').append(
                `<tr class='youtube_data'>
                   <td><i class=\"far fa-clock\"></i></td>
                     <td><a href='LectureDetailView?DATA_DATA=${video_id}'>${video_title}</a></td>
                      <td>${duration}</td>
                      <td>${duration}</td>`
            );

            min += parseInt(duration.split(' : ')[0]);
            sec += parseInt(duration.split(' : ')[1]);

            hour_sum = hour_sum + hour
            minite_sum = minite_sum + min
            second_sum = second_sum + sec

            count_sum = count_sum + count

            $('#count').html(`${count_sum} 개 수업`)
            $('#timer').html(`${timer}`)
            $('#playlist').append("</table></div>")

            count_sum++

        },
        error: function (err) {
            alert('callback hell!!!!!');
        }
    })
}

function DashboardList(video_list, playlist_id) {
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: video_list,
        success: function (vi_list) {

            var count = 0;
            var timer = 0;
            var hour = 0;
            var min = 0;
            var sec = 0;

            for (var i = 0; i < vi_list.items.length; i++) {

                count++;

                var video_title = vi_list.items[i].snippet.title;
                var video_id = vi_list.items[i].snippet.resourceId.videoId;
                var duration = getVideos(video_id);

                $('.youtube').append(
                    `<tr class='youtube_data'>
                       <td><i class=\"far fa-clock\"></i></td>
                       <td><a href='LectureDetailView?DATA_DATA=${video_id}'>${video_title}</a></td>
                       <td>${duration}</td>
                       <td>${duration}</td>`
                );

                min += parseInt(duration.split(' : ')[0]);
                sec += parseInt(duration.split(' : ')[1]);

                //                hour_sum = hour_sum + hour
                //                minite_sum = minite_sum + min
                //                second_sum = second_sum + sec

                if (min > 59) {
                    hour_sum++;
                    min -= 60;
                }

                if (sec > 59) {
                    minite_sum++;
                    sec = sec - 60;
                }
            }

            if (isNaN(sec) && isNaN(minite_sum)) {
                timer = "총 " + hour + "시간 ";
            } else if (isNaN(sec) || isNaN(minite_sum)) {
                if (isNaN(sec)) {
                    timer = "총 " + hour_sum + "시간 " + minite_sum + "분 ";
                } else {
                    timer = "총 " + hour_sum + "시간 " + sec + "초";
                }
            } else {
                timer = "총 " + hour_sum + "시간 " + minite_sum + "분 " + sec + "초";
            }

            count_sum = count_sum + count

            $('#count').html(`${count_sum} 개 수업`)
            $('#timer').html(`${timer}`)
            $('#playlist').append("</table></div>")

        },
        error: function (err) {
            alert('callback hell!!!!!');
        }
    })
}

function ReviewAnswer() {
    $('#playlist').append(
        `<div class="modal fade" id="myModal3" role="dialog">
          <div class="modal-dialog">
          
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4 class="modal-title">Static Backdrop</h4>
              </div>
              <div class="modal-body">
                <p>You cannot click outside of this modal to close it.</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
            
          </div>
        </div>`
    )
}

function getVideos(video_id) {
    var video = "https://www.googleapis.com/youtube/v3/videos?id= " + video_id + "&part=contentDetails&key=AIzaSyAKpVZhMIKzF0zAD17yeVygQWNfL7MCCzc";
    var duration = '';
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        async: false,
        url: video,
        success: function (video_data) {
            var duration_split = video_data.items[0].contentDetails.duration.split('PT', 2)[1];
            duration = splitTime(duration_split)
        },
        error: function (err) {
            alert("callback hell!!!!!")
        }
    })
    return duration;
}

function splitTime(duration) {
    var splitDu = duration;

    if (splitDu.indexOf('H') != -1) {
        splitDu = splitDu.replace('H', ' : ')
        splitDu = splitDu.replace('M', ' : ')
        splitDu = splitDu.replace('S', '')
        return splitDu;
    } else if (splitDu.indexOf('H') == -1 && splitDu.indexOf('M') != -1) {
        splitDu = splitDu.replace('M', ' : ')
        splitDu = splitDu.replace('S', '')
        return splitDu;
    } else if (splitDu.indexOf('H') == -1 && splitDu.indexOf('M') == -1 && splitDu.indexOf('S') != -1) {
        splitDu = splitDu.replace('S', '')
        return splitDu;
    }
}

function rating_star(rating, rating_tag) {

    var star = parseFloat(rating)
    if (star > 0 && star <= 0.5) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 0.5 && star <= 1) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 1 && star <= 1.5) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 1.5 && star <= 2) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 2 && star <= 2.5) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 2.5 && star <= 3) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 3 && star <= 3.5) {
        return rating_taginnerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i></span>`
    } else if (star > 3.5 && star <= 4) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i></span>`
    } else if (star > 4 && star <= 4.5) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></span>`
    } else if (star > 4.5 && star <= 5) {
        return rating_tag.innerHTML = `<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></span>`
    } else {
        return rating_tag.innerHTML = `<span><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`
    }
}

function avg_rating(rating, rating_tag) {

    var star = parseFloat(rating)
    if (star > 0 && star <= 0.5) {
        return rating_tag.prepend(`<span><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 0.5 && star <= 1) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 1 && star <= 1.5) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 1.5 && star <= 2) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 2 && star <= 2.5) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 2.5 && star <= 3) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 3 && star <= 3.5) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i><i class="far fa-star"></i></span>`)
    } else if (star > 3.5 && star <= 4) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i></span>`)
    } else if (star > 4 && star <= 4.5) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star-half-alt"></i></span>`)
    } else if (star > 4.5 && star <= 5) {
        return rating_tag.prepend(`<span><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></span>`)
    } else {
        return rating_tag.prepend(`<span><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></span>`)
    }
}

function ReviewReply() {

    $('.ReviewReply').on('click', function () {
        $(this).parents('tr').next().toggle();
    })

}