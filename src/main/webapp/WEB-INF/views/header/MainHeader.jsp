<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Outflearn</title>
  <!-- Bootstrap -->
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css">

  <!-- Stylesheet
    ================================================== -->
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/css/nivo-lightbox/nivo-lightbox.css">
  <link rel="stylesheet" type="text/css" href="resources/css/nivo-lightbox/default.css">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900" rel="stylesheet">

  <!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
  <script src="https://kit.fontawesome.com/27cb20e940.js"></script>
  
  <!-- Sweet Alert2 -->
  <link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
  <script src='resources/js/sweetalert/sweetalert2.min.js'></script>

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<!-- ==========================================Navigation==========================================-->
  <nav id="menu" class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
          data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span
            class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <a class="navbar-brand page-scroll" href="/Outflearn/" style="color: #6372ff">Outflearn</a> </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-center">
          <li><a href="LectureList?txt_search=&searchOption=all&sub_num=0">강좌 둘러보기</a></li>
          <li><a href="RoadMap?txt_search=&searchOption=all&main_num=0">로드맵 학습</a></li>
          <li><a href="Livepage">라이브</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

          <sec:authorize access="isAnonymous()">
          <li><a href="/Outflearn/loginform">로그인</a></li>
          <li><a href="/Outflearn/registerform.do">회원가입</a></li>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
          	<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_TUTOR')">
	            <li><a href="/Outflearn/myPage">마이페이지</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
	            <li><a href="/Outflearn/adminPage">관리자페이지</a></li>
			</sec:authorize>
          <li><a href="/Outflearn/logout">로그아웃</a></li>  
          </sec:authorize>
        </ul>
      </div>
    </div>
  </nav>
  
  <script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
  <script type="text/javascript" src="resources/js/template/header.js"></script>
  
</body>
</html>