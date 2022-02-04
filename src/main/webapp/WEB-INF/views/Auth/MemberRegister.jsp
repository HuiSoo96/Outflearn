<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <meta name="description" content=""> -->
<!-- <meta name="author" content=""> -->
<script type="text/javascript" src="resources/js/utils/register.js"></script>
<script
	src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
	async defer></script>
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
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/bootstrap.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/fonts/font-awesome/css/font-awesome.css"> -->

<!-- Stylesheet
    ================================================== -->
<!-- <link rel="stylesheet" type="text/css" href="resources/css/style.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/nivo-lightbox/nivo-lightbox.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/nivo-lightbox/default.css"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" -->
<!-- 	rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Lato:400,700" -->
<!-- 	rel="stylesheet"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900" -->
<!-- 	rel="stylesheet"> -->

<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<link href="/your-path-to-fontawesome/css/fontawesome.css"
	rel="stylesheet">
<link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
<link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
<!-- <script src="https://kit.fontawesome.com/27cb20e940.js"></script> -->

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/member.css">
</head>

<body>

	<!-- Header  -->
	<jsp:include page="../header/MainHeader.jsp"></jsp:include>
	<!-- Header  -->

	<div class="intro">

		<h1 style="color: #6372ff;" class="text-center">회원 가입</h1>
		<div class="row">
			<div class="col-xs-3 col-sm-3"></div>
			<div class="col-xs-6 col-sm-6">
				<div class="col-sm-6 col-md-offset-3">

					<form method="post"
						action="${pageContext.request.contextPath}/register.do"
						onsubmit="return formsubmit();">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token }">

						<div class="form-group">
							<div class="form-group">
								<div>
									<label class="memlabel" for="inputName">아이디</label>
								</div>
								<div>
									<input type="text" class="form-control" name="user_id"
										id="inputName" placeholder="아이디를 입력해 주세요" required />
									<div id="id_confirm"></div>
								</div>
							</div>
							<div class="form-group text-center">
								<button type="button" id="btn_idcheck" class="btn-member"
									onclick="id_check()">중복확인</button>
								<p class="result">
									<span class="msg"></span>
								</p>
							</div>

						</div>
						<div class="form-group">
							<label class="memlabel" for="inputPassword">비밀번호</label> <input
								type="password" class="form-control" name="user_pw"
								id="inputPassword" placeholder="1차 입력" onchange="pass_check()"
								required />
							<div id="pass_check"></div>
							<input type="password" class="form-control" name="user_pw02"
								id="inputPassword" placeholder="2차 입력" onchange="pass_confirm()"
								required />
							<div id="pass_confirm"></div>
							<br>

						</div>

						<div class="form-group">
							<label class="memlabel" for="InputNickname">닉네임</label> <input
								type="text" class="form-control" name="user_nickname"
								placeholder="닉네임" required />
						</div>

						<div class="form-group">
							<label class="memlabel" for="InputEmail">이메일 주소</label> <input
								type="email" class="form-control" name="user_email"
								id="InputEmail" placeholder="이메일 주소를 입력해주세요" required />
							<div class="form-group text-center">
								<button type="button" id="user_email" name="btn_emailChk"
									class="btn-member" onclick="email_Chk()">이메일 중복확인</button>
							</div>
							<div class="form-group text-center">
								<div id="emailDupChk"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="memlabel" for="InputEmail">이메일 인증 번호</label> <input
								type="text" class="form-control" name="ranNum"
								placeholder="인증번호발송 클릭" required />
							<div class="form-group text-center">
								<input type="button" class="btn-member" id="btn_sendemail"
									value="인증번호발송" onclick="sendEmail()" /> <input type="button"
									class="btn-member" id="ranChk" value="인증번호확인"
									onclick="ranNumChk()" />
								<div id="confirm"></div>
								<div id="emailChkConfirm"></div>
							</div>
						</div>
						<div class="form-group text-center">
							<div id="example1"></div>

						</div>
						<div class="form-group text-center">
							<button type="submit" id="join-submit" class="btn-member">회원가입</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

	<!--footer  -->
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	<!--footer  -->

</body>

</html>