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
	<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
	<title>Outflearn</title>

	<!-- Favicons
    ================================================== -->
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
	<link rel="apple-touch-icon" href="resources/img/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/img/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/img/apple-touch-icon-114x114.png">

	<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
	<link href="/your-path-to-fontawesome/css/fontawesome.css" rel="stylesheet">
	<link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
	<link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
	<!-- <script src="https://kit.fontawesome.com/27cb20e940.js"></script> -->

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/css/member.css">
</head>

<body>

	<!-- Header  -->
	<jsp:include page="../header/MainHeader.jsp"></jsp:include>

	<div class="intro">

		<h1 style="color: #6372ff;" class="text-center">강사하기</h1>
		<div class="row">
			<div class="col-xs-3 col-sm-3"></div>
			<div class="col-xs-6 col-sm-6">
				<div class="col-sm-6 col-md-offset-3">

					<form method="post" action="${pageContext.request.contextPath}/sendLecturerEmail">
						<input type="hidden" name="lecturerNum" value="${userInfo.user_num}">

						<div class="form-group">
							<div class="form-group">
								<div>
									<label class="memlabel" for="inputName">${userInfo.user_nickname}님의 연락처를
										알려주세요</label>
								</div>
								<div>
									<input type="text" class="form-control" name="lecturerPhone" required />
									<div id="id_confirm"></div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="memlabel" for="inputPassword">간단한 소개를 부탁드려요. 어떤 분인지 알고 싶어요!</label>
							<textarea name="lecturerIntro" id="" cols="30" rows="10"></textarea>
						</div>

						<div class="form-group">
							<label class="memlabel" for="InputNickname">아웃프런에서 강의하고 싶은 강좌</label>
							<textarea name="lecturerClass" id="" cols="30" rows="10"
								placeholder="공유 해주실 지식의 분야와 소개를 적어주세요. 참고할만한 URL이 있다면 그것도!"></textarea>
						</div>

						<div class="form-group text-center">
							<button type="submit" id="join-submit" class="btn-member">신청하기</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

	<!--footer  -->
	<jsp:include page="../footer/Footer.jsp"></jsp:include>

</body>

</html>