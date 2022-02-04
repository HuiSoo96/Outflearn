<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
	<title>Outflearn</title>

	<!-- Favicons
              ================================================== -->
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
	<link rel="apple-touch-icon" href="img/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png">

	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.css">

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

	<!-- Sweet Alert2 -->
	<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
	<script src='resources/js/sweetalert/sweetalert2.min.js'></script>
	<!-- fullCalendar -->


	<link href='resources/js/fullcalendar/core/main.css' rel='stylesheet' />
	<link href='resources/js/fullcalendar/daygrid/main.css' rel='stylesheet' />
	<link href='resources/js/fullcalendar/timegrid/main.css' rel='stylesheet' />

	<script src='resources/js/fullcalendar/core/main.js'></script>
	<script src='resources/js/fullcalendar/daygrid/main.js'></script>
	<script src='resources/js/fullcalendar/interaction/main.js'></script>
	<script src='resources/js/fullcalendar/timegrid/main.js'></script>

	<script type="text/javascript" src="resources/js/fullcalendar/liveCalendar.js"></script>
	<link rel="stylesheet" href="resources/js/broadcast/css/main.css" />
	<link rel="stylesheet" href="resources/css/myPage.css">

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<jsp:include page="../header/MainHeader.jsp"></jsp:include>

	<!--Slide Show-->
	<div class="container">
		<div class="page-header">
			<h1 class="float-left">방송</h1>
		</div>

		<h2><a class="reloadRoom" href="void:0">새로고침</a></h2>
		<div class="roomTest">

		</div>

		<!-- <div id="myCarousel" class="carousel slide" data-ride="carousel">
			Indicators
			<ol class="carousel-indicators">
			</ol>

			Wrapper for slides
			<div class="carousel-inner">
			</div>

			Left and right controls
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span class="sr-only">Next</span>
			</a>
		</div> -->

		<!--End off row-->
	</div>

	<div class="container">
		<div class="page-header">
			<h1>방송 스케줄</h1>
		</div>

		<div class="table-responsive-lg">
			<div id='calendar'></div>
		</div>
	</div>


	<jsp:include page="../footer/Footer.jsp"></jsp:include>

	<!-- Script -->
	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/template/nivo-lightbox.js"></script>
	<script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="resources/js/template/main.js"></script>

	<!--<script src="https://192.168.10.139:3000/socket.io/socket.io.js"></script> -->
	<script src="https://localhost:3000/socket.io/socket.io.js"></script> 
	<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
	<script src="resources/js/broadcast/js/ioConnect.js"></script>
	<script src="resources/js/broadcast/js/roomList.js"></script>

</body>

</html>