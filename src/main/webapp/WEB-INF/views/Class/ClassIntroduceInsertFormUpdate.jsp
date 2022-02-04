<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- Favicons ================================================== --> 
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
	<link rel="apple-touch-icon" href="resources/img/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/img/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/img/apple-touch-icon-114x114.png">

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
	<link href="/your-path-to-fontawesome/css/fontawesome.css" rel="stylesheet">
	<link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
	<link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
	<script src="https://kit.fontawesome.com/27cb20e940.js"></script>

	<!-- 여기서부터는 써머노트  라이브러리 구간입니다.-->
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

	<!-- include summernote css/js -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
	
	<!-- Sweet Alert2 -->
	<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
	<script src='resources/js/sweetalert/sweetalert2.min.js'></script>
	
	<script type="text/javascript">
	(function($){
		   $(document).ready(function() {
		      $('#summernote').summernote({
		              minHeight: 370,
		              maxHeight: null,
		              focus: true, 
		              lang : 'ko-KR'
		        });
		      });
		   
		})(jQuery);
	</script>

	<!-- 여기까지가 써머노트  라이브러리 구간입니다.-->
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	
		<jsp:include page="../header/MainHeader.jsp"></jsp:include>
	
	<div class="row">
			<ul id="side_border" class="nav flex-column col-sm-2 col-el-2 text-center">
				<li class="nav-link active">
					<h3>강의 소개</h3>
				</li>
				<li class="list-group-item-info">
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
			<form:form name="ClassIntroduceMyform" action="ClassIntroduceUpdate" method="post" enctype="multipart/form-data">
			
			<div class="input-group">
				<h1>강의 내용 </h1>
			</div>
			
			<div class="form-group">
				<textarea id="summernote" class="class_content" name="class_content" placeholder="${class_content.class_content }"></textarea>
			</div>
	
			<div class="form-group">
				<input type="hidden" class="class_num" value="${class_num }">
				<input type="button" class="btn btn-primary" value="수정" onclick="Control()"> 
				<input type="button" value="가이드 라인" onclick="ClassIntroduceGuideLine();" class="btn btn-primary">
			</div>
	</form:form>
			</div>

			</div>
	
	
	
			
<!-- Footer Section -->
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	function Control(){
		var class_content = $(".class_content").val();
		alert(class_content)
		ClassIntroduceMyform = document.ClassIntroduceMyform;
		if (ClassIntroduceMyform.class_content.value == "") {
			Swal.fire({
				type : 'error',
				title : '실패...',
				text : '강의 내용을 모두 입력해주세요.',
			})
		} 
		
		else {
		
		
		var class_num = $(".class_num").val();
		
		$.ajax({
			url : 'ClassIntroduceUpdate?class_num=' + class_num + "&class_content=" + class_content,
			method : 'post',
			success : function(data) {
				location.href= 'mypage';
			},
			error : function() {
				alert('에러 발생~~ \n')
			}

		})
		
		}
	}

	
	
		function ClassIntroduceGuideLine(){
			 var url = "ClassIntroduceGuideLine";
	         var name = "ClassIntroduceGuideLine";
	         var option = "width = 800, height = 800, top = 100, left = 200, location = no"
	         window.open(url, name, option);
		}
		
		function noEvent() {
			if (event.keyCode == 116) {
			event.keyCode= 2;
			return false;
			}
			else if(event.ctrlKey && (event.keyCode==78 || event.keyCode == 82))
			{
			return false;
			}
			}
			document.onkeydown = noEvent;
	</script>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>
</body>
</html>