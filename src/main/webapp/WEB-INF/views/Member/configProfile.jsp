<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Outflearn</title>

  <!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
  <script src="https://kit.fontawesome.com/27cb20e940.js"></script>

  <!-- css -->
  <link rel="stylesheet" href="resources/css/myPage.css">

  <!-- Bootstrap -->
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css">

  <!-- Stylesheet ================================================== -->
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">

  <!-- Sweet Alert2 -->
  <link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
  <script src='resources/js/sweetalert/sweetalert2.min.js'></script>

</head>

<body>

  <jsp:include page="../header/MainHeader.jsp"></jsp:include>

  <div class="container">

    <div class="row">

      <aside class="col-sm-2">

        <div class="side_category">
          <ul class="nav flex-column text-center">

            <li class="nav-item side_menu select">
              <a href="myPage">대시보드</a>
            </li>

            <li class="nav-item side_menu">
              <a href="void:0">내 강좌</a>
              <ul class="inner_menu">
                <sec:authorize access="hasRole('ROLE_TUTOR')">
                  <li><a href="myClass">게시한 강좌</a></li>
                </sec:authorize>
                <li><a href="listenClass">수강중인 강좌</a></li>
                <li><a href="basketSelect">장바구니</a></li>
              </ul>
            </li>

            <li class="nav-item side_menu">
              <a href="void:0">로드맵</a>
              <ul class="inner_menu">
                <li><a href="myRoadmap">게시한 로드맵</a></li>
                <li><a href="subRoadmap">참여중인 로드맵</a></li>
              </ul>
            </li>

            <li class="nav-item side_menu">
              <a href="myQuestion">내 질문</a>
            </li>

            <li class="nav-item side_menu">
              <a href="void:0">설정</a>
              <ul class="inner_menu">
                <li><a href="configProfile" class="selectMenu">프로필 설정</a></li>
              </ul>
            </li>

          </ul>
        </div>

        <sec:authorize access="hasRole('ROLE_TUTOR')">
          <div class="side_category live">
            <ul class="nav flex-column text-center">
              <li class="nav-item side_menu">
                <a href="void:0">LIVE</a>
                <ul class="inner_menu">
                  <li><a class="configLiveRoom" href="void:0">방송하기</a></li>
                  <li><a id="setLiveSchedule" href="void:0">방송 스케줄</a></li>
                </ul>
              </li>
            </ul>
          </div>
        </sec:authorize>

      </aside>

      <div class="col-sm-10">
        <div class="page-header">
          <h1 id="page-header-content">설정</h1>
        </div>
        <article>
          <div class="page-body">
            <div class="row">
              <div class="col-sm-12">
                <div class="boardBox">
                  <h4 class="boxTitle">프로필 설정</h4>

                  <div class="row">
                    <div class="col-sm-12">
                      <div class="boardBox">
                        <h4 class="boxTitle">닉네임</h4>
                        <form action="updateNickname" onsubmit="return nickSubmit(this)">
                          <h5><input type="text" name="nickname" id="setNickname" value="${userInfo.user_nickname}"
                              default="${userInfo.user_nickname}"></h5>
                          <input type="submit" id="updateNickname" class="configBtn" value="저장하기" disabled>
                        </form>
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-sm-12">
                      <div class="boardBox">
                        <h4 class="boxTitle">이메일</h4>
                        <form action="updateEmail" method="post" onsubmit="return emailSubmit(this)">
                          <h5>
                            <input type="text" name="emailBefore" id="setEmailBefore" class="setEmail"
                              value="${fn:substringBefore(userInfo.user_email,'@')}"
                              default="${fn:substringBefore(userInfo.user_email,'@')}">
                            @
                            <input type="text" name="emailAfter" id="setEmailAfter" class="setEmail"
                              value="${fn:substringAfter(userInfo.user_email,'@')}"
                              default="${fn:substringAfter(userInfo.user_email,'@')}">
                            <select name="" id="emailSelect">
                              <option value="naver.com">naver.com</option>
                              <option value="gmail.com">gmail.com</option>
                              <option value="daum.net">daum.net</option>
                            </select>
                          </h5>
                          <input type="submit" id="updateEmail" class="configBtn" value="저장하기" disabled>
                        </form>
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-sm-12">
                      <div class="boardBox">
                        <h4 class="boxTitle">등급</h4>
                        <sec:authentication property="principal.Authorities" var="grade" />
                        <c:if test="${grade eq '[ROLE_USER]'}">
                          <h5>유저에요</h5>
                        </c:if>
                        <c:if test="${grade eq '[ROLE_ADMIN]'}">
                          <h5>어드민이에요</h5>
                        </c:if>
                        <c:if test="${grade eq '[ROLE_TUTOR]'}">
                          <h5>강사에요</h5>
                        </c:if>
                        <c:if test="${grade eq '[ROLE_USER]'}">
                          <button onclick="location.href='reqLecturer'" class="configBtn">'강사'하기</button>
                        </c:if>
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-sm-12">
                      <div class="boardBox">
                        <h4 class="boxTitle">비밀번호</h4>
                        <form action="updatePw" method="post" onsubmit="return pwSubmit(this)">
                          <input type="password" name="beforePw" placeholder="현재 비밀번호">
                          <input type="password" name="afterPw" placeholder="새 비밀번호">
                          <input type="password" name="afterPwChk" placeholder="새 비밀번호 확인">
                          <input type="submit" id="updatePw" class="configBtn" value="저장하기">
                        </form>
                      </div>
                    </div>
                  </div>

                </div>
              </div>
            </div>

          </div>
        </article>
      </div>
    </div>
  </div>
  <!-- ==================== FOOTER ==================== -->

  <jsp:include page="../footer/Footer.jsp"></jsp:include>

  <script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
  <script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
  <script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
  <script type="text/javascript" src="resources/js/utils/myPage.js"></script>
</body>

</html>