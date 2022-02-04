<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Favicons ================================================== -->
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

<!-- Stylesheet ================================================== -->
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

<!-- Sweet Alert2 -->
<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
<script src='resources/js/sweetalert/sweetalert2.min.js'></script>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<jsp:include page="../header/MainHeader.jsp"></jsp:include>


	<div class="row">
		<ul id="side_border" class="list-group nav flex-column col-sm-2 col-el-2 text-center">
				<li class="list-group-item-info">
					<h3>강의 소개</h3>
				</li>
				<li class="nav-link active">
					<h3>강의 내용</h3>
				</li>
				<li class="nav-link active">
					<h3>영상 소개</h3>
				</li>
				<li class="nav-link active">
					<h3>영상 추가</h3>
				</li>
		</ul>
		<div class="col-sm-6 col-el-8">
			<form:form name="ClassInfoMyform" action="ClassIntroduceInsertForm"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="class_author" value="${user_nickname }">
				<input type="hidden" name="user_num" value="${user_num }">

				<div class="input-group">
					<h1>강의 소개</h1>
				</div>

				<div class="form-group">
					<h3>강의 제목</h3>
					<input type="text" class="form-control" name="class_title"
						placeholder="강의 제목을 입력해주세요.">
				</div>


				<div class="form-group">
					<h3>썸네일 이미지</h3>
					<input type="file" name="file" style="color: #6372ff;">
				</div>


				<div class="form-group">
					<h3>강사명</h3>
					${user_nickname }
				</div>

				<div class="form-group">
					<h3>강의 소개</h3>
					<textarea name="class_intro" class="form-control" rows="5"
						cols="10" placeholder="강좌에 대해서 설명해주세요."></textarea>
				</div>

				<div class="input-group">
					<h3>실시간 여부</h3>
					<input type="text" class="form-control" name="class_live"
						placeholder="Y or N 입력해주세요.">
				</div>

				<div class="form-group">
					<h3>가격</h3>
					<input type="text" class="form-control" name="class_price"
						placeholder="숫자만 입력해주세요.">
				</div>

				<div class="input-group col-xs-4">
					<h3>수강 대상</h3>
					<select name="class_studentlevel" style="color: #6372ff; width: 120px; height: 50px; font-size: 10px;">
							<option value="초급자" style="font-size: 10px;">초급자</option>
							<option value="중급자" style="font-size: 10px;">중급자</option>
							<option value="상급자" style="font-size: 10px;">상급자</option>
					</select>
				</div>

				<div class="input-group col-xs-4">
					<h3>주류</h3>
					<select name="main_num" style="color: #6372ff; width: 120px; height: 50px; font-size: 10px;" id="select1"
						onchange="itemChange(this.value)">
						<optgroup>
							<option value="1" style="font-size: 10px;">서버</option>
							<option value="2" style="font-size: 10px;">웹 개발</option>
							<option value="3" style="font-size: 10px;">데이터 베이스</option>
						</optgroup>
					</select>
				</div>

				<div class="input-group col-xs-4">
					<h3>부류</h3>
					<select id="select2"
						style="color: #6372ff; width: 120px; height: 50px; font-size: 10px;" name="sub_num">
						<optgroup>
							<option value="1">Back End</option>
							<option value="2">Linux</option>
							<option value="3">Node.js</option>
							<option value="4">Express.js</option>
							<option value="5">C#</option>
						</optgroup>
					</select>
				</div>


				<div class="form-group">
					<input type="button" class="btn btn-primary" value="다음" onclick="Control();"> 
					<input type="button" class="btn btn-primary" value="취소" onclick="location.href='LectureList'">
				</div>
			</form:form>
		</div>




		<!-- Footer Section -->
		<jsp:include page="../footer/Footer.jsp"></jsp:include>
		<script type="text/javascript">
			function Control() {
				ClassInfoMyform = document.ClassInfoMyform;
				if (ClassInfoMyform.class_title.value == ""
						|| ClassInfoMyform.file.value == ""
						|| ClassInfoMyform.class_intro.value == ""
						|| ClassInfoMyform.class_live.value == ""
						|| ClassInfoMyform.class_price.value == "") {
					Swal.fire({
						type : 'error',
						title : '실패...',
						text : '강의 내용을 모두 입력해주세요.',
					})
				} else{
				ClassInfoMyform.submit();
				}
			}

			function itemChange(main_num) {

				$.ajax({
							url : 'ClassCategory?main_num=' + main_num,
							method : 'get',
							success : function(data) {

								var option = ''
								for (var i = 0; i < data.length; i++) {
									option += ("<option value= "+ data[i].sub_num + " 	> "
											+ data[i].sub_name + "</option>");
									$('#select2').html(option);
								}

							},
							error : function() {
								alert('에러 발생~~ \n')
							}

						});

			}
		</script>
		<script type="text/javascript"
			src="https://code.jquery.com/jquery-3.4.1.js"></script>
		<script type="text/javascript"
			src="resources/js/template/bootstrap.js"></script>
		<script type="text/javascript"
			src="resources/js/template/nivo-lightbox.js"></script>
		<script type="text/javascript"
			src="resources/js/template/jqBootstrapValidation.js"></script>
		<script type="text/javascript" src="resources/js/template/main.js"></script>
</body>
  
</html>