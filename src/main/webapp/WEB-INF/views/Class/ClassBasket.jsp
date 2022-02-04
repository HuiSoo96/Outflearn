<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Outflearn</title>

	<!-- css -->
	<link rel="stylesheet" href="resources/css/myPage.css">

	<!-- css -->
	<link rel="stylesheet" href="resources/css/myPage.css">

	<!-- Stylesheet ================================================== -->
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">

	<!-- Sweet Alert2 -->
	<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
	<script src='resources/js/sweetalert/sweetalert2.min.js'></script>

	<!-- Sweet Alert2 -->
	<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
	<script src='resources/js/sweetalert/sweetalert2.min.js'></script>

</head>

<body>

	<jsp:include page="../header/LectureListHeader.jsp"></jsp:include>

	<div class="container">

		<div class="row">

			<aside class="col-sm-2">

				<div class="side_category">
					<ul class="nav flex-column text-center">

						<li class="nav-item side_menu select"><a href="myPage">대시보드</a>
						</li>

						<li class="nav-item side_menu"><a href="void:0">내 강좌</a>
							<ul class="inner_menu">
								<sec:authorize access="hasRole('ROLE_TUTOR')">
									<li><a href="myClass">게시한 강좌</a></li>
								</sec:authorize>
								<li><a href="listenClass">수강중인 강좌</a></li>
								<li><a href="basketSelect" class="selectMenu">장바구니</a></li>
							</ul>
						</li>

						<li class="nav-item side_menu"><a href="void:0">로드맵</a>
							<ul class="inner_menu">
								<li><a href="myRoadmap">게시한 로드맵</a></li>
								<li><a href="subRoadmap">참여중인 로드맵</a></li>
							</ul>
						</li>

						<li class="nav-item side_menu"><a href="myQuestion">내 질문</a>
						</li>

						<li class="nav-item side_menu"><a href="void:0">설정</a>
							<ul class="inner_menu">
								<li><a href="configProfile">프로필 설정</a></li>
								<li><a href="configAlarm">알림 설정</a></li>
							</ul>
						</li>

					</ul>
				</div>

				<sec:authorize access="hasRole('ROLE_TUTOR')">
					<div class="side_category live">
						<ul class="nav flex-column text-center">
							<li class="nav-item side_menu"><a href="void:0">LIVE</a>
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
					<h1 id="page-header-content">내 강좌</h1>
				</div>

				<article>
					<div class="page-body">
						<div class="row">
							<div class="col-sm-12">
								<div class="boardBox">
									<h4 class="boxTitle">장바구니</h4>

									<c:choose>
										<c:when test="${empty classInfoUser }">
											<div>
												<p>담겨진 강의가 없습니다.</p>
											</div>
										</c:when>

										<c:otherwise>
											<div>

												<table id="cart" class="table table-hover table-condensed">
													<thead>
														<tr>
															<th style="width: 50%">강좌</th>
															<th style="width: 40%">가격</th>
															<th style="width: 20%">총합</th>
														</tr>
													</thead>

													<c:set var="sum" value="0" />
													<c:forEach items="${classInfoUser }" var="dto">
														<tbody>
															<tr>
																<td data-th="Product">
																	<div class="row">
																		<div class="col-sm-2 hidden-xs">
																			<img src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }"
																				alt="..." class="img-responsive" />
																		</div>
																		<div class="col-sm-10">
																			<h4 class="nomargin">${dto.class_title }
																			</h4>
																			<p>(수강기한:무제한)</p>
																		</div>
																	</div>
																</td>
																<td data-th="Price">₩${dto.class_price }</td>
																<td data-th="Price">₩${dto.class_price }</td>
																<td class="actions"><input type="hidden"
																		name="class_num" value="${dto.class_num }"
																		id="class_num"> <input type="button"
																		class="btn btn-info btn-sm" value="강의 삭제"
																		onclick="BasketDeleteOne();"></td>
															</tr>
														</tbody>
														<c:set var="sum" value="${sum + dto.class_price }" />
													</c:forEach>
													<tfoot>
														<tr>
															<td colspan="3" class="hidden-xs text-right"><strong>총합
																	<fmt:formatNumber value="${sum}" />원
																</strong></td>
															<td>
																<form:form method="post" action="oauth">
																	<c:forEach items="${classInfoUser }" var="dto">
																		<input type="hidden" name="class_title"
																			value="${dto.class_title }" />
																		<input type="hidden" name="class_num"
																			value="${dto.class_num }">
																	</c:forEach>
																	<input type="hidden" name="class_price"
																		value="${sum}">
																	<input type="submit" value="결제하기"
																		class="btn btn-success btn-block">
																</form:form>
															</td>
														</tr>
													</tfoot>
												</table>

											</div>
										</c:otherwise>
									</c:choose>

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

	<script type="text/javascript">
		var class_num = $("#class_num").val();

		function BasketDeleteOne() {

			$.ajax({
				url: 'basketDeleteOne?class_num=' + class_num,
				method: 'get',
				success: function (data) {
					window.location.reload();
				},
				error: function () {
					alert('에러 발생~~ \n')
				}

			})

		}
	</script>

	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/utils/myPage.js"></script>
</body>

</html>