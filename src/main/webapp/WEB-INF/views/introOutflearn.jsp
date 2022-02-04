<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="utf-8">

<head>
  <meta charset="UTF-8">
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <meta name="description" content=""> -->
<!--   <meta name="author" content=""> -->

  <title>Outflearn</title>

  <!-- Bootstrap -->
<!--   <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css"> -->
<!--   <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css"> -->

  <!-- Stylesheet
    ================================================== -->
<!--   <link rel="stylesheet" type="text/css" href="resources/css/style.css"> -->
<!--   <link rel="stylesheet" type="text/css" href="resources/css/nivo-lightbox/nivo-lightbox.css"> -->
<!--   <link rel="stylesheet" type="text/css" href="resources/css/nivo-lightbox/default.css"> -->
<!--   <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
<!--   <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet"> -->
<!--   <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900" rel="stylesheet"> -->

  <!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
<!--   <script src="https://kit.fontawesome.com/27cb20e940.js"></script> -->

</head>

<body >	
<!-- Header  -->
<jsp:include page="header/MainHeader.jsp"></jsp:include>
<!-- Header  -->

  <header id="header">
    <div class="intro">
      <div class="overlay">
        <div class="container">
          <div class="row">
            <div class="col-md-8 col-md-offset-2 intro-text">
              <h2>아웃프런에서 당신의 지식을 쌓으세요.</h2>
              <h2>${UserInfo.user_nickname}</h2>
              <p>배우고 싶은 지식 또는 기술을 경험하세요</p>
              <div class="input-group input-group-lg col-md-12">
                <input type="text" class="form-control" placeholder="배우고 싶은 기술을 입력하세요.">
                <span class="input-group-btn">
                  <button type="button" class="btn btn-default btn-lg"><i class="fas fa-search"
                      style="color: #3366FF"></i></button>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
  </header>


  <jsp:include page="footer/Footer.jsp"></jsp:include>

</body>

</html>