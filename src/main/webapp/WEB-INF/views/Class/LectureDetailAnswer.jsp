<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>	
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Outflearn</title>

<!-- css -->
<link rel="stylesheet" href="resources/css/detail.css">

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">

<!-- Stylesheet
    ================================================== -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<jsp:include page="../header/LectureListHeader.jsp"></jsp:include>

	<div class="jumbotron">
		<div id="jumbo_row" class="row">
			<img src="resources/img/cardpx.svg" alt="썸네일" class="col-md-3">
			<div class="col-md-6">
				<h3>${classinfo.class_title }</h3>
				<div>
					<p>평점<input type="hidden" value="${classinfo.class_rating }"></p>
					<p>수강생</p>
					<div class="progress">
						<div class="progress-bar" role="progressbar" aria-valuenow="2"
							aria-valuemin="0" aria-valuemax="100"
							style="min-width: 2em; width: 2%;">2%</div>
					</div>
				</div>
			</div>
			<div id="study_btn" class="col-md-3">
				<p class="text-center" align="center">
					<a href="void:0" class="btn btn-success btn-lg" role="button">학습하기</a>
				</p>
				<div id="box">
					<div id="course">
						<h4>${classinfo.class_price }원</h4>
						<button type="button" class="btn-group btn-group-vertical btns">바로 수강신청</button>
						<button type="button" class="btn-group btn-group-vertical btns">장바구니 담기</button>
					</div>
					<div id="course_info">
						<div id="instructor_profile" class="text-center">${classinfo.class_author }</div>
						<div id="course_metas">
							<p class="profile-border">평생
								<span class="pull-right"><i class="far fa-calendar-alt"></i></span>
							</p>
							<p class="profile-border">${classinfo.class_studentlevel }
								<span><i class="fab fa-leanpub pull-right"></i></span>
							</p>
							<p class="profile-border">
								<span id="count"></span>개 수업
								<span><i class="fas fa-book-open pull-right"></i></span>
							</p>
							<p id="timer" class="profile-border"><i class="fas fa-hourglass-half pull-right"></i></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="main" class="container pull-left">
		<nav class="nav">
			<ul class="nav nav-tabs">
				<li class="nav-item "><a class="nav-link" href="void:0">대쉬보드</a></li>
				<li class="nav-item "><a class="nav-link" href="void:0">강좌소개</a></li>
				<li class="nav-item "><a class="nav-link" href="void:0">수강후기</a></li>
				<li class="nav-item "><a class="nav-link" href="void:0">질문&답변</a></li>
				<li class="nav-item "><a class="nav-link" href="void:0">새소식</a>
				</li>
			</ul>
		</nav>
	</div>
	<hr/>
	<!-- BODY -->

	  <div class="panel-group">
	  	   <div class="panel panel-default">
		<h1>댓글 쓰기</h1>

		<form:form action="LectureDetailAnswer" method="post">
		<input type="hidden" name="user_nickname" value="${user_nickname }">
		<input type="hidden" name="user_num" value="${user_num }">
		<input type="hidden" name="class_num" value="${class_num }">

		<table border="1">
			<tr>
				<td>
					<select name="user_star" class="alert alert-success ">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
				<td><strong>${user_nickname }</strong></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="review_content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<input type="submit" value="작성">
			</tr>
		</table>
		</form:form>
		</div>
	</div>	
	

	<jsp:include page="../footer/Footer.jsp"></jsp:include>

	<script type="text/javascript"
		src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript"
		src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>
	<script type="text/javascript" src="resources/js/template/detail.js"></script>
</body>

</html>