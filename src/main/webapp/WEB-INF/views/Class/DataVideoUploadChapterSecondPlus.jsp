<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<!-- ======================================================jQuery -->
<script type="text/javascript"
	src="resources/js/template/jquery.1.11.1.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Sweet Alert2 -->
<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
<script src='resources/js/sweetalert/sweetalert2.min.js'></script>


</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<jsp:include page="../header/MainHeader.jsp"></jsp:include>
		
				
	<div class="row">
		<ul id="side_border"
			class="nav flex-column col-sm-2 col-el-2 text-center">
			<li class="nav-link active"><h3>?????? ??????</h3></li>
			<li class="nav-link active"><h3>?????? ??????</h3></li>
			<li class="nav-link active"><h3>?????? ??????</h3></li>
			<li class="list-group-item-info"><h3>?????? ??????</h3></li>
		</ul>
		<div class="col-sm-6 col-el-8">

			<form:form name="DataVideoMyform" action="DataVideoUploadSecondPlus"  method="post" enctype="multipart/form-data">
				<input type="hidden" name="class_num" value="${class_num }" />
				
				<div class="input-group">
					<h1>??????  ???????????????</h1>
				</div>
				
				<div class="form-group">
					<h3>?????? ??????</h3>
					<input type="text" name="data_title" class="form-control" placeholder="????????? ??????????????????">
				</div>

				<div class="form-group">
					<h3>?????? ??????</h3>
					<input type="text" name="data_sq" class="form-control" placeholder="?????? ????????? ??????????????????.">
				</div>

				<div class="form-group">
					<h3>?????? ?????????(????????? ?????? ?????? ?????? ?????? ????????? ????????? ??????)</h3>
					<div class="youTube">
						<input type="text" name="data_data" class="form-control" placeholder="????????? ????????? ??????????????????." /> 
						<input type="button" value="??????" style="color: #6372ff;" class="success1" />
					</div>
				</div>
				<div class="form-group">
					<div class="data">
						<input type="file" id="file" name="file" style="color: #6372ff;" /> 
						<input type="button" value="??????" style="color: #6372ff;" class="success2" />
					</div>
				</div>


				<div class="form-group">
					<input type="button" class="btn btn-primary" value="?????? ??? ??????" onclick="Control();" />
					<input type="button" value="????????? ??????" onclick="DataVideoGuideLine();" class="btn btn-primary"> 
					<input type="button" class="btn btn-primary" value="?????? " onclick="location.href='myPage'">
				</div>

			</form:form>
		</div>
	</div>


	<!-- Footer Section -->
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	

	
	<script type="text/javascript">
		
	$(document).ready(function () {
		$(".success1").click(function () {
			$(".data").remove();
		});
		$(".success2").click(function () {
			$(".youTube").remove();
		});
	});

	function Control() {
		DataVideoMyform = document.DataVideoMyform;

		
		// ?????? ???????????? ?????? ????????? ?????? ?????????
		if (DataVideoMyform.data_sq.value == "" ||
			DataVideoMyform.data_title.value == "") {
			
			Swal.fire
				({
					type: 'error',
					title: '??????...',
					text: '?????? ????????? ?????? ??????????????????.',
				})
		}
		
		// ?????? ??????????????? ??????
		else if (DataVideoMyform.data_sq.value == DataVideoMyform.data_sq.value &&
				 DataVideoMyform.data_title.value == DataVideoMyform.data_title.value) {
			
				Swal.fire({
					type: 'success',
					title: '??????',
					text: '????????? ?????????????????????.',
					
				})
				.then((result)=>{
					if(result.value){
						DataVideoMyform.submit();
					}
				})
			
			
		}
		else if (DataVideoMyform.data_sq.value == DataVideoMyform.data_sq.value &&
			DataVideoMyform.data_title.value == DataVideoMyform.data_title.value && document.getElementsByName("file").value == document.getElementsByName("file").value) {
			if (DataVideoMyform.data_data.value == "") {
				Swal.fire({
					type: 'error',
					title: '??????...',
					text: '????????? ?????? ?????? ????????? ??????????????????.3333',
				})
			}
		} else {
			DataVideoMyform.submit();
		}
	}
	
		function DataVideoGuideLine() {
			var url = "DataVideoGuideLine";
			var name = "DataVideoGuideLine";
			var option = "width = 800, height = 800, top = 100, left = 200, location = no"
			window.open(url, name, option);
		}
	</script>

	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript"
		src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>

</body>

</html>