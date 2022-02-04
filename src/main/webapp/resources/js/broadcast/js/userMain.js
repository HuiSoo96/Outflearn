// var

var localStream
var remoteStream
var pc;

var pcConfig = {
    'iceServers': [
        {
            'urls': 'stun:stun.l.google.com:19302'
        }
        ,
        {
            urls: 'turn:numb.viagenie.ca',
            credential: 'muazkh',
            username: 'webrtc@live.com'
        }
    ]
};

// var sdpConstraints = {
//     offerToReceiveAudio: true,
//     offerToReceiveVideo: true
// };

// socket

var name = $('#userInfo').attr("name")
var room = $('#userInfo').attr("room")
// var room = 2
// var socket = io.connect('http://192.168.10.139:3000');
//var socket = io.connect('https://192.168.10.139:3000', { rejectUnauthorized: false });
// var socket = io.connect('https://localhost:3000');

if (room !== "") {
    socket.emit('userJoin', room, name)
    console.log('userJoin 메세지 서버에 전송', room);
}

socket.on('message', function (msg, id) {
    if (msg.type === 'offer') {
        console.log(`받은 메세지 offer`);
        createPeerConnectionUser()
        pc.setRemoteDescription(new RTCSessionDescription(msg));
        sendAnswer()
    } else if (msg.type === 'candidate') {
        var candidate = new RTCIceCandidate({
            sdpMLineIndex: msg.label,
            candidate: msg.candidate
        });

        pc.addIceCandidate(candidate)
    } else if (msg === 'bye') {
        handleRemoteHangup()
    } else {
        console.log('잘못 배달된 메세지', msg);
    }
})

socket.on('chatMsg', function (data) {
    if (data.type === 'msg') {
        appendMsg('other', data.chatMessage, data.name)
    } else if (data.type === 'leaveCaster') {
        Swal.fire({
            type: 'error',
            title: '라이브 끝!',
            text: '라이브 방송이 종료되었습니다.',
            footer: data.class_num
        })
            .then((result) => {
                location.href = 'Livepage'
            })
    }
});

socket.on('joinedRoom', function (name, numClients) {
    appendMsg('server', `${name}님이 입장하셨습니다.`)
    $('#numView').text(`${numClients}`)
})

socket.on('roomSetting', function (liveTime) {
    $('#liveStartTime').text(liveTime)
})

// what?

// var constraints = {
//     video: true
// };

// console.log('Getting user media with constraints', constraints);

if (location.hostname !== 'localhost') {
    requestTurn(
        'https://computeengineondemand.appspot.com/turn?username=41784574&key=4080218913'
    );
}

window.onbeforeunload = function () {
    userSendMessage('bye');
};

// funtions

function sendMessage(msg) {
    socket.emit('message', msg)
}

function userSendMessage(msg) {
    console.log(`클라이언트가 메세지 보냄 ${msg}`);
    socket.emit('userMsg', msg, room)
}

var localVideo = document.querySelector('#localVideo')
var remoteVideo = document.querySelector('#remoteVideo')

// function startCast() {
//     navigator.mediaDevices.getUserMedia({
//         audio: true,
//         video: { width: { ideal: 1280 }, height: { ideal: 720 } }
//     })
//         .then(gotStream)
//         .catch(function (e) {
//             alert(`getUserMedia 에러!! ${e.name}`)
//         })
// }

// function gotStream(stream) {
//     console.log('로컬 스트림 추가');
//     localStream = stream
//     localVideo.srcObject = stream
// }

// function handleCreateOfferError(event) {
//     console.log('createOffer() error: ', event);
// }

function sendAnswer() {
    console.log('Sending answer to peer.');
    pc.createAnswer().then(
        setLocalAndSendMessageUser,
        onCreateSessionDescriptionError
    );
}

function setLocalAndSendMessageUser(sessionDescription) {
    pc.setLocalDescription(sessionDescription);
    console.log('setLocalAndSendMessage sending message', sessionDescription);
    userSendMessage(sessionDescription);
}


function onCreateSessionDescriptionError(error) {
    trace('Failed to create session description: ' + error.toString());
}

function createPeerConnectionUser() {
    try {
        pc = new RTCPeerConnection(null);
        pc.onicecandidate = handleIceCandidateUser
        pc.onaddstream = handleRemoteStreamAdded
        pc.onremovestream = handleRemoteStreamRemoved
        console.log('피어 커넥션 (View) 생성 완료');

    } catch (e) {
        console.log('피어 커넥션 (View) 생성 오류', e.message);
        alert('Cannot create RTCPeerConnection object.');
        return;
    }
}


function handleIceCandidateUser(event) {
    console.log('icecandidate event: ', event);
    if (event.candidate) {
        userSendMessage({
            type: 'candidate',
            label: event.candidate.sdpMLineIndex,
            id: event.candidate.sdpMid,
            candidate: event.candidate.candidate
        });
    } else {
        console.log('End of candidates.');
    }
}

function handleRemoteStreamAdded(event) {
    console.log('Remote stream added.');
    remoteStream = event.stream;
    remoteVideo.srcObject = remoteStream;
}

function handleRemoteStreamRemoved(event) {
    console.log('Remote stream removed. Event: ', event);
}

function hangup() {
    console.log('Hanging up.');
    stop();
    sendMessage('bye');
}

function handleRemoteHangup() {
    console.log('Session terminated.');
    stop();
}

function stop() {
    pc = null;
}

// turn & ice server

var turnReady;


function requestTurn(turnURL) {
    var turnExists = false;
    for (var i in pcConfig.iceServers) {
        if (pcConfig.iceServers[i].urls.substr(0, 5) === 'turn:') {
            turnExists = true;
            turnReady = true;
            break;
        }
    }
    if (!turnExists) {
        console.log('Getting TURN server from ', turnURL);
        // No TURN server. Get one from computeengineondemand.appspot.com:
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var turnServer = JSON.parse(xhr.responseText);
                console.log('Got TURN server: ', turnServer);
                pcConfig.iceServers.push({
                    'urls': 'turn:' + turnServer.username + '@' + turnServer.turn,
                    'credential': turnServer.password
                });
                turnReady = true;
            }
        };
        xhr.open('GET', turnURL, true);
        xhr.send();
    }
}

// Chat

function ScrollToBottom() {
    var objDiv = document.getElementById("chat");
    objDiv.scrollTop = objDiv.scrollHeight;
}

function appendMsg(_class, _msg, _name) {
    var text;
    if (_name) {
        text = `<p class="nameSpace">${_name}</p><p>${_msg}</p>`
    } else {
        text = `<p>${_msg}</p>`
    }
    $('#messages').append($(`<li class=${_class}>`).html(text));
    ScrollToBottom()
}

$(function () {

    $('form').submit(function (e) {
        e.preventDefault();
        var msg = $('#m').val().trim();
        if (msg != "" && msg != null) {
            socket.emit('chatMsg', msg, room);
            appendMsg('me', msg)
        }
        $('#m').val('');
        return false;
    });



    //FUNCTION

    $('.card').on('click', function (e) {
        e.preventDefault()
        Swal.fire({
            type: 'warning',
            title: '현재 화면이 종료 됩니다.',
            text: `${$(this).find('.card-title').text()}로 이동하시겠습니까?`,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '이동',
            cancelButtonText: '취소'
        })
            .then((result) => {
                if (result.value) {
                    location.href = `${$(this).find('a').attr('href')}`
                } else {
                    return false;
                }
            })

    })

})