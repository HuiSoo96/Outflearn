<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">

		function PageMove(page, data) {
			location.href = "LectureList?page=" + page +
				"&txt_search=" + $('input#txt_search').val() +
				"&searchOption=" + $('#searchOption').val() +
				"&sub_num=" + data;
		} 
	</script>


</head>

<body>

	<jsp:include page="../header/LectureListHeader.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<aside class="col-sm-2">
				<div id="side_category">
					<ul class="nav flex-column text-center">
						<li class="nav-item side_menu"><a href="void:0" class="nav-link active"
								data-toggle="dropdown">카테고리</a></li>

						<c:forEach items="${mainList }" var="mainDto">
							<li class="nav-item all_category"><a href="void:0" id="side_${mainDto.main_num }"
									class="nav-link active">${mainDto.main_name }&nbsp;<i
										class="fas fa-chevron-right"></i></a>
								<ul id="side_${mainDto.main_num }_menu" class="nav flex-column collapse">
									<li>
										<c:forEach items="${subList }" var="subDto">
											<c:if test="${mainDto.main_num eq subDto.main_num }">
									<li><a href="LectureList?txt_search=${txt_search }&searchOption=all&sub_num=${subDto.sub_num}" class="nav-link active sub_category">${subDto.sub_name}</a></li>
									<input type="hidden" class="nav-link active sub_category" name="sub_category" id="sub_category" value="${subDto.sub_num }">
									</c:if>
						</c:forEach>
						</li>
					</ul>
					</li>
					</c:forEach>
					</ul>
				</div>


				<div id="side_checkbox">
					<ul class="nav flex-column text-center" style="clear: both">
						<li class="lg_category nav-item text-left"><a href="void:0" id="level_btn"
								class="nav-link active text-left">난이도&nbsp;<i class="fas fa-chevron-down"></i></a>
							<div id="level" class="collapse">
								<div class="sm_category">
									<input id="begin" type="checkbox"><label for="begin">초급</label>
								</div>
								<div class="sm_category">
									<input id="intermediate" type="checkbox"><label for="intermediate">중급</label>
								</div>
								<div class="sm_category">
									<input id="advanced" type="checkbox"><label for="advanced">고급</label>
								</div>
							</div>
						</li>
						<li class="lg_category nav-item text-left"><a href="void:0" id="charged_btn"
								class="nav-link active">유료/무료&nbsp;<i class="fas fa-chevron-down"></i></a>
							<div id="charged" class="collapse">
								<div class="sm_category">
									<input id="pay" type="checkbox"><label for="pay">유료</label>
								</div>
								<div class="sm_category">
									<input id="free" type="checkbox"><label for="free">무료</label>
								</div>
							</div>
						</li>
					</ul>
				</div>


			</aside>
			<div class="col-sm-10">
				<div class="page-header">
					<h1 id="page-header-content">전체 카테고리</h1>
					<div id="search" class="form-group row justify-content-center col-sm-10 col-sm-push-9">
						<div class="w100" style="padding-right:0px">
							<select class="form-control form-control-sm" name="searchOption" id="searchOption">
								<option value="all">전체</option>
								<option value="class_title">제목</option>
								<option value="class_author">작성자</option>
							</select>
						</div>
						<div class="w300" style="padding-right:0px">
							<input type="text" class="form-control form-control-sm" name="txt_search" id="txt_search"
								 placeholder="검색하기" value="${txt_search }">
							<input type="hidden" id="txt_search" value="${txt_search }">
						</div>
						<div>
							<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch"
								onclick="javascript:PageMove(${pagination.pageNo}, '${sub_num }');">검색</button>
						</div>
					</div>
					<p class="input-group col-sm-4 pull-right"></p>
				</div>

				<article>
					<div class="page-body">
						<span>
							<h2>
								지식 카테고리
								<div class="list-group pull-right">
									<select>
										<option>추천순</option>
										<option>평점순</option>
										<option>학생수순</option>
									</select>
								</div>
							</h2>
						</span>
						<c:choose>
							<c:when test="${empty classinfo }">
								<h3>강좌 정보가 없습니다...!!</h3>
							</c:when>
							<c:otherwise>
								<c:forEach items="${classinfo }" var="dto">
									<div class="card">
										<div>
											<a href="LectureDetail?class_num=${dto.class_num }"> <img
													class="card-img-top"
													src="${pageContext.request.contextPath }/resources/uploadImage/${dto.class_img }"
													alt="Card image cap">
												<div class="card-body">
													<h5 class="card-title">${dto.class_title }</h5>
													<p class="card-text">${dto.class_intro }</p>
												</div>
											</a>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</article>
			</div>
		</div>
	</div>
	<!-- Pagination -->
	
	<ul id="paging" class="pagination text-center text-inline" >
						<li><a href="javascript:PageMove(${pagination.firstPageNo},'${sub_num }')" class="button previous">&laquo;</a></li>
						<li><a	href="javascript:PageMove(${pagination.prevPageNo},'${sub_num }'" class="button previous">&lt;</a></li>
						<li class="pagination">
							<c:forEach var="i" begin="${pagination.startPageNo}" end="${pagination.endPageNo}" step="1">
								<c:choose>
									<c:when test="${i eq pagination.pageNo}">
										<li class="active"><a href="javascript:PageMove(${i}, '${sub_num}')" class="active">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:PageMove(${i}, '${sub_num}')" >${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</li>
						<li><a href="javascript:PageMove(${pagination.nextPageNo}, '${sub_num}')" class="button_next">&gt;</a></li>
						<li><a href="javascript:PageMove(${pagination.finalPageNo}, '${sub_num}')" class="button_next">&raquo;</a></li>
					</ul>
		<!-- ==================== FOOTER ==================== -->

	<jsp:include page="../footer/Footer.jsp"></jsp:include>

	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/LectureList.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>

</body>

</html>