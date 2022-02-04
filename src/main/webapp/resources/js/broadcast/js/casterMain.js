// var
var localStream
var remoteStream

var pcArr = []

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

var sdpConstraints = {
    offerToReceiveAudio: true,
    offerToReceiveVideo: true
};

// socket

var name = $('#userInfo').attr("name")
var room = $('#userInfo').attr("room")

//var socket = io.connect('https://192.168.10.139:3000', { rejectUnauthorized: false });
// var socket = io.connect('https://localhost:3000');
// var socket = io.connect('http://192.168.10.139:3000');

if (room !== "") {
    socket.emit('casterJoin', room, name)
    console.log('casterJoin 메세지 서버에 전송', room);
}

socket.on('createdRoom', function (room, liveTime) {
    console.log(`방 생성 ${room}`);
    $('#liveStartTime').text(liveTime)
    startCast()
    appendMsg('server', `방이 생성되었습니다.`)
})

socket.on('joinedUser', function (id) {
    console.log('쪼인!!!');
    casterPeerCreate(id)
})

socket.on('message', function (msg, id) {
    if (msg.type === 'answer') {
        console.log(`받은 메세지 answer`);
        commitPc(findPc(id).setRemoteDescription(new RTCSessionDescription(msg)), id);
    } else if (msg.type === 'candidate') {
        var candidate = new RTCIceCandidate({
            sdpMLineIndex: msg.label,
            candidate: msg.candidate
        });

        var empPc = findPc(id)

        commitPc(empPc.addIceCandidate(candidate), id)

    } else if (msg === 'bye') {
        handleRemoteHangup(id)
    } else {
        console.log('잘못 배달된 메세지', msg);
    }
})

// what?

var constraints = {
    video: true
};

console.log('Getting user media with constraints', constraints);

if (location.hostname !== 'localhost') {
    requestTurn(
        'https://computeengineondemand.appspot.com/turn?username=41784574&key=4080218913'
    );
}

window.onbeforeunload = function () {
    sendMessage('bye');
};

// funtions

function findPc(id) {
    for (let i = 0; i < pcArr.length; i++) {
        if (pcArr[i].id === id) {
            return pcArr[i].pc
        }
    }
}

function commitPc(pc, id) {
    for (let i = 0; i < pcArr.length; i++) {
        if (pcArr[i].id === id) {
            pcArr[i].pc === pc
        }
    }
}

function sendMessage(msg) {
    socket.emit('message', msg)
}

function casterSendMessage(msg, id) {
    socket.emit('casterMsg', msg, id)
}

var localVideo = document.querySelector('#localVideo')
var remoteVideo = document.querySelector('#remoteVideo')

function startCast() {
    navigator.mediaDevices.getUserMedia({
        audio: true,
        video: { width: { ideal: 1280 }, height: { ideal: 720 } }
    })
        .then(gotStream)
        .catch(function (e) {
            alert(`getUserMedia 에러!! ${e.name}`)
        })
}

function gotStream(stream) {
    console.log('로컬 스트림 추가');
    localStream = stream
    localVideo.srcObject = stream
}

function casterPeerCreate(id) {
    console.log('피어 커넥션 생성');

    pcArr.push({ 'id': id, pc: createPeerConnectionCaster(id) })

    commitPc(findPc(id).addStream(localStream), id)

    sendOffer(id)
}


function sendOffer(id) {
    console.log('오퍼 보내지');
    findPc(id).createOffer()
        .then(function (sessionDescription) {
            setLocalAndSendMessageCaster(sessionDescription, id)
        })
        .catch(function (err) {
            handleCreateOfferError(err)
        })
}

function handleCreateOfferError(event) {
    console.log('createOffer() error: ', event);
}


function setLocalAndSendMessageCaster(sessionDescription, id) {
    commitPc(findPc(id).setLocalDescription(sessionDescription), id);
    console.log('setLocalAndSendMessage sending message', sessionDescription);
    casterSendMessage(sessionDescription, id)
}

function onCreateSessionDescriptionError(error) {
    trace('Failed to create session description: ' + error.toString());
}

// createPeectConnection

function createPeerConnectionCaster(id) {
    var empPc
    try {
        empPc = new RTCPeerConnection(null);
        empPc.onicecandidate = function (event) {
            handleIceCandidateCaster(event, id)
        }
        empPc.onaddstream = handleRemoteStreamAdded
        empPc.onremovestream = handleRemoteStreamRemoved
        console.log('피어 커넥션 (Lec) 생성 완료');
        return empPc
    } catch (e) {
        console.log('피어 커넥션 (Lec) 생성 오류', e.message);
        alert('Cannot create RTCPeerConnection object.');
        return;
    }
}


function handleIceCandidateCaster(event, id) {
    console.log('icecandidate event: ', event);
    if (event.candidate) {
        casterSendMessage({
            type: 'candidate',
            label: event.candidate.sdpMLineIndex,
            id: event.candidate.sdpMid,
            candidate: event.candidate.candidate
        }, id);
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

function handleRemoteHangup(id) {
    console.log('Session terminated.');
    stop(id);
}

function stop(id) {
    commitPc(findPc(id).close(), id)
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

    socket.on('chatMsg', function (data) {
        if (data.type === 'msg') {
            appendMsg('other', data.chatMessage, data.name)
        }
    });

    socket.on('joinedRoom', function (name, numClients) {
        appendMsg('server', `${name}님이 입장하셨습니다.`)
        $('#numView').text(`${numClients}`)
    })

    //FUNCTION

    function noEvent() {
        if ((event.keyCode == 116) || (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82))) {
            Swal.fire({
                type: 'warning',
                title: '새로고침시 방이 터져요... ㅠㅠ',
                showCancelButton: true,
                confirmButtonText: '그래도 새로고침!',
                cancleButtonText: '취소'
            })
                .then((result) => {
                    result.value ? location.reload() : false
                })
            return false;
        } else if ((event.keyCode == 8)) {
            Swal.fire({
                type: 'warning',
                title: '뒤로가기시 방이 터져요... ㅠㅠ',
                showCancelButton: true,
                confirmButtonText: '그래도 뒤로가기!',
                cancleButtonText: '취소'
            })
                .then((result) => {
                    result.value ? history.back() : false
                })
            return false;
        }
    }

    document.onkeydown = noEvent;

})