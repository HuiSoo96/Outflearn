<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">

  <title>Outflearn</title>

<!-- loginform css -->
<link rel="stylesheet" type="text/css" href="resources/css/loginForm.css">

<!-- 카카오 네이버 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

<!-- 제이쿼리 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
 
</head>
<body >
	
	<!-- Header  -->
	<jsp:include page="../header/MainHeader.jsp"></jsp:include>
	<!-- Header  -->
	
	  <header id="header">
	    <div class="intro">
	      <div class="overlay">
	        <div class="container">
	          <div class="row">
	            <div class="col-md-8 col-md-offset-2 intro-text">
	            <div class="login-form">
	            <!-- 로그인 폼 영역 시작  -->
	            <div class="modal-header">
		        <a id="login-logo" style="text-align: center;" >Outflearn</a> 
	        	</div>
	            <div class="modal-body">
	            <br/><br/>
				
	              <form action="/Outflearn/login" method="post" id="loginform" name="loginform">
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
	                <input type="text" name="loginId" id="loginId" placeholder="  아이디 입력" ><br/>
	                <input type="password" name="loginPw" id="loginPw" placeholder="  비밀번호 입력" style="margin-top: 2%;"><br/><br/>
					
				
	                <input type="submit" value="Sign in" id="submitBTN" >
					<input type="checkbox" id="remember-me" name="remember-me" style="margin-left: 2%;">
					<span>Remember Me</span>
	
					
					<br/><br/>
					<c:if test="${not empty ERRORMSG }">				
					<span id="errorMSG">${ERRORMSG }</span>	
					</c:if>
	              </form>
				<br/>
	              <a href="/Outflearn/findIdForm.do">아이디 찾기</a>
	              <a href="/Outflearn/findPwForm.do" style="padding-left: 2%;">비밀번호를 잊으셨나요?</a><br/><br/>
	              
	              <!-- 카카오 로그인 시작 -->
	              <a id="kakao-login-btn"></a>
				  <a href="http://developers.kakao.com/logout"></a>
				  <!-- 카카오로그인.js -->
				  <script type="text/javascript" src="resources/js/utils/kakaologin.js"></script><br/><br/>	
				  <!-- 카카오 로그인 끝 -->
				  <!-- ============================================= -->
	              <!-- 네이버 로그인  시작-->  
	              <!-- callback.jsp와 함께 사용  -->   
			      <div id="naverIdLogin"></div>
				  <script type="text/javascript">
					var naverLogin = new naver.LoginWithNaverId(
						{
							clientId: "xsVfuuGnIVuwdJhzN_0M",
							callbackUrl: "http://localhost:8787/Outflearn/naverCallback",
							isPopup: false, /* 팝업을 통한 연동처리 여부 */
							loginButton: {color: "green", type: 3, height: 48} /* 로그인 버튼의 타입을 지정 */
						}
					);
					
					/* 설정정보를 초기화하고 연동을 준비 */
					naverLogin.init();
				</script>
				</div>

              <!-- 로그인 폼 끝 -->
			
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>


  <!-- Contact Section -->
  <div id="contact">
    <div class="container">
      <div class="col-md-8">
        <div class="row">
          <div class="section-title">
            <h2>Get In Touch</h2>
            <p>Please fill out the form below to send us an email and we will get back to you as soon as possible.</p>
          </div>
          <form name="sentMessage" id="contactForm" novalidate>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <input type="text" id="name" class="form-control" placeholder="Name" required="required">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <input type="email" id="email" class="form-control" placeholder="Email" required="required">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
            </div>
            <div class="form-group">
              <textarea name="message" id="message" class="form-control" rows="4" placeholder="Message"
                required></textarea>
              <p class="help-block text-danger"></p>
            </div>
            <div id="success"></div>
            <button type="submit" class="btn btn-custom btn-lg">Send Message</button>
          </form>
        </div>
      </div>
      <div class="col-md-3 col-md-offset-1 contact-info">
        <div class="contact-item">
          <h3>Contact Info</h3>
          <p><span><i class="fa fa-map-marker"></i> Address</span>4321 California St,<br>
            San Francisco, CA 12345</p>
        </div>
        <div class="contact-item">
          <p><span><i class="fa fa-phone"></i> Phone</span> +1 123 456 1234</p>
        </div>
        <div class="contact-item">
          <p><span><i class="fa fa-envelope-o"></i> Email</span> info@company.com</p>
        </div>
      </div>
      <div class="col-md-12">
        <div class="row">
          <div class="social">
            <ul>
              <li><a href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
              <li><a href="#"><i class="fa fa-youtube"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Footer Section -->
  <div id="footer">
    <div class="container text-center">
      <p>Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a></p>
    </div>
  </div>
  <script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
  <script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
  <script type="text/javascript" src="resources/js/template/nivo-lightbox.js"></script>
  <script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
  <!-- script type="text/javascript" src="resources/js/template/contact_me.js"></script> -->
  <script type="text/javascript" src="resources/js/template/main.js"></script>
  <script type="text/javascript" src="resources/js/utils/login.js?ver=1"></script>
</body>

</html>