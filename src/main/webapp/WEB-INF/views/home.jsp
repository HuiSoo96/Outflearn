<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="utf-8">

<head>
	<meta charset="UTF-8">
	<title>Outflearn</title>

	<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
	<script src="https://kit.fontawesome.com/27cb20e940.js"></script>

	<!-- css -->
	<link rel="stylesheet" href="resources/css/LectureList.css">

	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css">

	<!-- Stylesheet ================================================== -->
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">

	<script type="text/javascript">
		function findLecture(txt_search) {
			location.href = "LectureList?txt_search=" + $('input#txt_search').val() +
				"&searchOption=all&sub_num=0"
		}
	</script>

</head>

<body>

	<!-- Header -->
	<jsp:include page="header/MainHeader.jsp"></jsp:include>

	<header id="header">
		<div class="intro">
			<div class="overlay">
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 intro-text">
							<h2>아웃프런에서 당신의 지식을 쌓으세요.</h2>
							<sec:authorize access="isAuthenticated()">
								<h2>
									<sec:authentication property="principal.user_nickname" /> 님 </h2>
							</sec:authorize>
							<p>배우고 싶은 지식 또는 기술을 경험하세요</p>
							<div class="input-group input-group-lg col-md-12">

								<input type="text" class="form-control" placeholder="배우고 싶은 기술을 입력하세요." id="txt_search"
									value="${txt_search }">
								<span class="input-group-btn">
									<button type="button" class="btn btn-default btn-lg"><i class="fas fa-search"
											style="color: #3366FF"
											onclick="javascript:findLecture(${txt_search});"></i></button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- 좋은 평가를 받고 있는 강좌 -->
	<div id="best_review">
		<div class="container">
			<h2 class="page-header" style="text-align: center">좋은 평가를 받고있는 강좌</h2>
			<div id="BestReview" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<c:choose>
						<c:when test="${empty SubCount }">
							<h1>아직 강좌 개설이 안됐습니다.</h1>
						</c:when>
						<c:otherwise>
							<c:forEach var="i" begin="3" step="3" end="6">
								<div class="item href-div">
									<c:choose>
										<c:when test="${i eq '3' }">
											<c:forEach items="${SubCount }" var="dto" begin="0" end="${i - 1}">
												<img src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }"
													onclick="location.href='LectureDetail?class_num=${dto.class_num }'">
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${SubCount }" var="dto" begin="3" end="${i }">
												<img src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }"
													onclick="location.href='LectureDetail?class_num=${dto.class_num }'">
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<a class="left carousel-control" href="#BestReview" data-slide="prev">
					&#10094;
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#BestReview" data-slide="next">
					&#10095;
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>

	<!-- 인기 강좌 -->
	<div id="popular">
		<div class="container">
			<h2 class="page-header" style="text-align: center">인기있는 강좌</h2>
			<div id="Popular_" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<c:choose>
						<c:when test="${empty SubCount }">
							<h1>아직 강좌 개설이 안됐습니다.</h1>
						</c:when>
						<c:otherwise>
							<c:forEach var="i" begin="3" step="3" end="6">
								<div class="item">
									<c:choose>
										<c:when test="${i eq '3' }">
											<c:forEach items="${SubCount }" var="dto" begin="0" end="${i - 1}">
												<img
													src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }">
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${SubCount }" var="dto" begin="3" end="${i }">
												<img
													src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }">
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				<a class="left carousel-control" href="#Popular_" data-slide="prev">
					&#10094;
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#Popular_" data-slide="next">
					&#10095;
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>

	<!--footer  -->
	<jsp:include page="footer/Footer.jsp"></jsp:include>

	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/LectureList.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>
</body>

</html>