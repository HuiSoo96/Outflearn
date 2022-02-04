<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Outflearn</title>

  <!-- reference your copy Font Awesome here (from our Kits or by hosting yourself) -->
  <script src="https://kit.fontawesome.com/27cb20e940.js"></script>

  <!-- css -->
  <link rel="stylesheet" href="resources/css/myPage.css">

  <!-- Bootstrap -->
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css">

  <!-- Stylesheet ================================================== -->
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">

  <!-- Sweet Alert2 -->
  <link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
  <script src='resources/js/sweetalert/sweetalert2.min.js'></script>

</head>

<body>

 	<jsp:include page="../header/MainHeader.jsp"></jsp:include>

  <div class="container">

    <div class="row">

      <aside class="col-sm-2">

        <div class="side_category">
          <ul class="nav flex-column text-center">

            <li class="nav-item side_menu select">
              <a href="adminPage">대시보드</a>
            </li>

            <li class="nav-item side_menu select">
              <a href="adminUserList">회원 목록</a>
            </li>

            <li class="nav-item side_menu">
              <a href="adminReqLecturer" class="selectMenu">'강사' 신청</a>
            </li>

          </ul>
        </div>

      </aside>

      <div class="col-sm-10">
        <div class="page-header">
          <h1 id="page-header-content">'강사' 신청</h1>
        </div>
        <article>
          <div class="page-body">

            <div class="row">
              <div class="col-sm-12">
                <div class="boardBox">
                  <h4 class="boxTitle">'강사' 신청 목록</h4>
                  <c:choose>
                    <c:when test="${empty reqList }">
                      <h5>신청이 없다뇨!</h5>
                    </c:when>
                    <c:otherwise>
                      <table class="table table-hover table-condensed">
                        <thead>
                          <tr>
                            <th>번호</th>
                            <th>닉네임</th>
                            <th>이메일</th>
                            <th>전화번호</th>
                            <th>간단 소개</th>
                            <th>강좌 정보</th>
                            <th>승인</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${reqList }" var="item">
                            <tr>
                              <td>${item.USER_NUM}</td>
                              <td>${item.USER_NICKNAME}</td>
                              <td>${item.USER_EMAIL}</td>
                              <td>${item.REQ_PHONE}</td>
                              <td>${item.REQ_PR}</td>
                              <td>${item.REQ_CLASS}</td>
                              <td>
                                <input type="button" onclick="acceptReq(${item.USER_NUM})" class="updateBtn" value="Y">
                                <input type="button" onclick="deniReq(${item.USER_NUM})" class="deleteBtn" value="N">
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
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

  <script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
  <script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
  <script type="text/javascript" src="resources/js/template/jqBootstrapValidation.js"></script>
  <script type="text/javascript" src="resources/js/utils/myPage.js"></script>

</body>

</html>