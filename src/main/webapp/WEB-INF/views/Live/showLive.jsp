<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<!-- <head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Outflearn</title> -->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>Outflearn</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/js/broadcast/css/main.css" />

    <!-- css -->
    <link rel="stylesheet" href="resources/css/LectureList.css">

    <!-- Sweet Alert2 -->
    <link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />

</head>

<body>
    <div id="content">
        <div id="videos">
            <video id="remoteVideo" autoplay muted controls></video>
        </div>
        <div id="chatArea">
            <div id="chat">
                <ul id="messages"></ul>
            </div>
            <div class="div-button-send">
                <form id="typing" action="">
                    <input id="m" autocomplete="off" />
                </form>
            </div>
        </div>

        <div id="info">
            <div id="thisClassInfo">
                <img id="classThumb" src="/Outflearn/resources/uploadImage/${room.class_img}" alt="No image">
                <h1 id="roomTitle">${room.class_title}</h1>
                <div>
                    <p id="casterName">${room.class_author} </p>
                    시청자 : <span id="numView">1</span>
                    방송시작시간 : <span id="liveStartTime">0000-00-00 00:00:00</span>
                    <p id="classIntro">강좌소개 : ${room.class_intro}</p>
                </div>
            </div>
            <div id="otherClassInfo">
                <h2>${room.class_author }의 다른 강좌 보기</h2>
                <c:forEach items="${casterClassList }" var="item">
                    <div class="card">
                        <div>
                            <a href="LectureDetail?class_num=${item.class_num }">
                                <img class="card-img-top"
                                    src="${pageContext.request.contextPath }/resources/uploadImage/${item.class_img }"
                                    alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title">${item.class_title }</h5>
                                    <p class="card-text">${item.class_intro }</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- This file is automatically added/served when running "node index.js". -->
    <script src="https://localhost:3000/socket.io/socket.io.js"></script>
    <!--  <script src="https://192.168.10.139:3000/socket.io/socket.io.js"></script> -->
    <script src="https://code.jquery.com/jquery-1.11.1.js"></script>
    <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>


    <!-- Sweet Alert2 -->
    <script src='resources/js/sweetalert/sweetalert2.min.js'></script>

    <script src="resources/js/broadcast/js/ioConnect.js"></script>
    <script id="userInfo" src="resources/js/broadcast/js/userMain.js" name="${userInfo.user_nickname}"
        room="${room.class_num }"></script>



</body>

</html>