<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">

<!-- Stylesheet
    ================================================== -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/QuestionSelectOne.css">

<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<!-- include libraries(jQuery, bootstrap) -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">


</head>
<body>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication var="num" property="principal.user_num"/>
	</sec:authorize>
	

	<jsp:include page="../header/LectureListHeader.jsp"></jsp:include>
	
	<div id="Question" class="container">
		<div class="row">
			<div class="page-header col-sm-12 col-md-12"><h3>${classQuestion.qa_title}</h3></div>
		</div>
		<div class="row">
			<div class="col-sm-9 col-md-9">
				<c:choose>
					<c:when test="${empty classQuestionReply }">
						<div></div>
					</c:when>
				<c:otherwise>
					<div class="form-group">
						<c:forEach items="${classQuestionReply }" var="dto">
							<div class="panel student_question_panel">
								<div class="form-group question_content">
									<p class="question_title">작성자 : ${dto.user_nickname }</p>
									<p class="question-content">${dto.qa_content }</p>
									<c:if test="${dto.user_num == num }">
										<div>
											<div class="update-btn-div">
												<form:form cssClass="update-complete-btn" action="QAReplyUpdate">
													<textarea name="qa_content" class="reply-update summernote">${dto.qa_content }</textarea>
													<input type="hidden" name="qa_num" value="${dto.qa_num }">
													<input type="hidden" name="qa_group_sq" value="${dto.qa_group_sq }">
													<input type="hidden" name="qa_group_no" value="${dto.qa_group_no }">
													<button type="submit" class="btn btn-default qa-btn">수정 완료</button>
													<button type="button" class="btn btn-default cancel-btn qa-btn">수정 취소</button>
												</form:form>
												<button type="button" class="btn btn-default update-btn qa-btn">수정</button>
											</div>
											<form:form action="QAReplyDelete">
												<input type="hidden" name="qa_num" value="${dto.qa_num }">
												<input type="hidden" name="qa_group_no" value="${dto.qa_group_no }">
												<input type="hidden" name="qa_group_sq" value="${dto.qa_group_sq }">
												<button type="submit" class="btn btn-default qa-btn">삭제</button>
											</form:form>
										</div>
									</c:if>
								</div>
							</div>
							<div class="qa-line"></div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
			<div>
				<form:form id="QAReply-insert-form" action="QAReplyInsert">
					<input type="hidden" name="user_num" value='<sec:authentication property="principal.user_num"/>'>
					<input type="hidden" name="user_nickname" value='<sec:authentication property="principal.user_nickname"/>'>
					<input type="hidden" name="class_num" value="${classQuestion.class_num }">
					<input type="hidden" name="qa_group_no" value="${classQuestion.qa_group_no }">
					<input type="hidden" name="qa_group_sq" value="${classQuestion.qa_group_sq }">
					<input type="hidden" name="qa_num" value="${classQuestion.qa_num }">
					<textarea cols="10" name="qa_content" class="summernote reply-insert"></textarea>
					<button type="submit" class="btn btn-default">답글 입력</button>
				</form:form>
			</div>
			</div>
			<div class="col-sm-3 col-md-3">
				<a type="button" class="btn btn-default" href="LectureDetail?class_num=${classQuestion.class_num}">이전 페이지로 가기</a>
			</div>
		</div>
	</div>
	
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	
	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/QuestionSelectOne.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
</body>
</html>
