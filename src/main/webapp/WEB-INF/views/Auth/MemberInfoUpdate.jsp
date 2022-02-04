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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="resources/js/utils/memberInfo.js"></script>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<title>Outflearn</title>
<!-- Favicons
    ================================================== -->
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon">
<link rel="apple-touch-icon" href="resources/img/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="resources/img/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="resources/img/apple-touch-icon-114x114.png">

<!-- Bootstrap -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/bootstrap.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/fonts/font-awesome/css/font-awesome.css"> -->

<!-- Stylesheet
    ================================================== -->
<!-- <link rel="stylesheet" type="text/css" href="resources/css/style.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/nivo-lightbox/nivo-lightbox.css"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/nivo-lightbox/default.css"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" -->
<!-- 	rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Lato:400,700" -->
<!-- 	rel="stylesheet"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900" -->
<!-- 	rel="stylesheet"> -->

<!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<link href="/your-path-to-fontawesome/css/fontawesome.css"
	rel="stylesheet">
<link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
<link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
<!-- <script src="https://kit.fontawesome.com/27cb20e940.js"></script> -->
<link rel="stylesheet" type="text/css" href="resources/css/member.css">
<sec:csrfMetaTags/>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
   $(function () {
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");
      $(document).ajaxSend(function(e, xhr, options) {
         xhr.setRequestHeader(header, token);
      });
      });
   
</script>
</head>

<body>
	<!-- Header  -->
	<jsp:include page="../header/MainHeader.jsp"></jsp:include>
	<!-- Header  -->

	
	<h1 class="text-center">회원 정보 설정</h1>

		<div class="row">
		<div class="col-xs-3 col-sm-3"></div>
		<div class="col-xs-6 col-sm-6">
			
			<div id="content">
		<div class="inner">

			<!-- Post -->
			<section class="box search">
				<form method="post" action="${pageContext.request.contextPath}/memberInfoUpdate.do" onsubmit="return formsubmit();">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">	
					<header>
						<h3>회원정보 설정</h3>
					</header>
					
					<table id="user_info_table">
						
						<tr>
							<th>아이디</th>
							<td>
							<input type="text" name="user_id" value='<sec:authentication property="principal.username"/>'></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td>
							<input type="text" name="user_nickname" value='<sec:authentication property="principal.user_nickname"/>'>
							</td>
						</tr>
						<tr>
							<th style="vertical-align: middle;">비밀번호</th>
							<td>
								<ul>
									<li>
										<label for="now_pw">
											<span>현재 비밀번호 : </span><input type="password" id="now_pw" name="now_pw" placeholder="현재 비밀번호를 입력해주세요."  required >
											<span id="currentPwChk"></span>
										</label>
									</li>
									<li>
										<label for="new_pw">
											<span>새 비밀번호 : </span><input type="password" id="new_pw" name="new_pw" placeholder="변경할 비밀번호를 입력해주세요." onchange="pw_change()" required >
<!-- 											<input type="button" id="pw_change_btn" value="비밀번호확인" onclick="pw_change()"> -->
										<div id="pass_check"></div>
											
										</label>
									</li>
									<li>
										<label for="new_pw_chk">
											<span>새 비밀번호 확인 : </span><input type="password" id="new_pw_chk" name="new_pw_chk" placeholder="다시 한 번 입력해주세요." >
										</label>
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td>
								<div id="pw_success" style="color: blue;">비밀번호가 일치합니다.</div>
								<div id="pw_fail" style="color: red;">비밀번호가 일치하지 않습니다.</div>
							</td>
						</tr>
						<tr>
							<th style="vertical-align: middle;">이메일</th>
							<td class="email_inline">
								<input type="text" id="email1" name="email1" value="${email1}">
								  @
								<input type="text" id="email2" name="email2" value="${email2 }" >
								<select id="select_email" name="select_email">
									<option value="1" selected>직접 입력</option>
									<option value="naver.com">naver.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="gmail.com">gmail.com</option>
									<option value="daum.net">daum.net</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="nate.com">nate.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="paran.com">paran.com</option>
									<option value="empas.com">empas.com</option>
									<option value="dreamwiz.com">dreamwiz.com</option>
									<option value="freechal.com">freechal.com</option>
									
								</select>
								
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input type="submit" class="MemberInfoUpdate_btn" value="수정하기" >
								<input type="button" class="MemberInfoCancel_btn" value="취소" onclick="location.href='loginform?user_id=<sec:authentication property="principal.user_id"/>'"> 
								<input type="button" class="member_info_bottom" value="회원탈퇴" onclick="withdraw()">
							</td>
						</tr>
					</table>
				</form>
			</section>

		</div>
	</div>

		</div>
	</div>

   <!--footer  -->
   <jsp:include page="../footer/Footer.jsp"></jsp:include>    
   <!--footer  -->

</body>

</html>