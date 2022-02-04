<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<script type="text/javascript" src="resources/js/utils/register.js"></script>
<title>Outflearn</title>

<!-- Favicons
    ================================================== -->
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon">
<link rel="apple-touch-icon" href="resources/img/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="resources/img/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="resources/img/apple-touch-icon-114x114.png">

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome/css/font-awesome.css">

<!-- Stylesheet
    ================================================== -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/nivo-lightbox/nivo-lightbox.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/nivo-lightbox/default.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900"
	rel="stylesheet">

<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<link href="/your-path-to-fontawesome/css/fontawesome.css"
	rel="stylesheet">
<link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
<link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/27cb20e940.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/member.css">
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<!-- Header  -->
   <jsp:include page="../header/MainHeader.jsp"></jsp:include>
   <!-- Header  -->
	<!-- ==========================================Navigation==========================================-->
	<nav id="menu" class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top"
					style="color: #6372ff">Outflearn</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-center">
					<li><a href="LectureList.html">강좌 둘러보기</a></li>
					<li><a href="void:0">로드맵 학습</a></li>
					<li><a href="void:0">아웃프런 소개</a></li>
					<li><a href="void:0">라이브</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="void:0" class="dropdown-toggle"
						data-toggle="dropdown"><i class="far fa-user"
							style="color: #6372ff"></i>
							<div class="dropdown-menu" role="menu"
								aria-expanded="navbarDropdown">
								<a href="void:0">로그아웃</a>
							</div> </a></li>
					<li><a href="/Outflearn/loginform">로그인</a></li>
					<li><a href="/Outflearn/registerform.do">회원가입</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="intro">
	<h1 style="color:#6372ff;"class="text-center">비밀번호 찾기</h1>
	<div class="row">
		<div class="col-xs-3 col-sm-3"></div>
		<div class="col-xs-6 col-sm-6">
			
			<form method="post" action="${pageContext.request.contextPath}/findPw.do">
					
					<div class="form-group">
                        <label class="memlabel" for="inputName">아이디</label>
                        <input type="text" class="form-control"  name="user_id" id="" placeholder="아이디를 입력해 주세요" required />
								<div id="id_confirm"></div>
								<div class="form-group text-center" >
									<button type="button" id="btn_idcheck" class="btn-member" onclick="idChkPw()" >아이디 확인</button>
									<p class="result">
									<span class="msg"></span>
								</p>
								</div>
					</div>
					
						<div class="form-group">
                        <label class="memlabel" for="inputEmail">이메일체크</label>
                        <input type="text" class="form-control"  name="user_email" id="" placeholder="이메일을 입력해 주세요" required />
							<div class="form-group text-center" >
							<button type="button" id="btn_sendemail" class="btn-member" onclick="emailChkPw()" >이메일 체크</button>
							<button type="button" id="btn_sendPwEmail" class="btn-member" onclick="sendEmailPw()" >임시비밀번호발송</button>
							</div>
								<div id="emailDupChk"></div>

					</div>
					
					<br>
					<div class="form-group text-center">
						<button type="submit" id="findPwBtn" class="btn-member">로그인</button>
						<button type="submit" id="findPwBtn" class="btn-member" onclick="history.go(-1);">취소</button>
					</div>
							
				</form>

		</div>
	</div>
	</div>
	


	<!--footer  -->
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	<!--footer  -->
	
	<script type="text/javascript"
		src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript"
		src="resources/js/template/jqBootstrapValidation.js"></script>
	<!--  
	<script type="text/javascript"
		src="resources/js/template/contact_me.js"></script>
	-->
	<script type="text/javascript" src="resources/js/template/main.js"></script>
	<script type="text/javascript" src="resources/js/utils/login.js?ver=1"></script>
</body>

</html>