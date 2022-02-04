<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="resources/js/utils/register.js"></script>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<title>Outflearn</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome/css/font-awesome.css">

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

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<jsp:include page="../header/LectureListHeader.jsp"></jsp:include>
	
	<h1 class="text-center">카카오페이 결제 성공</h1>
	<div class="row">
		<div class="col-xs-3 col-sm-3"></div>
		<div class="col-xs-6 col-sm-6">
			
			<table>
			
				<tr>
					<th>결제일시</th>
					<td>${info.approved_at}</td>
				</tr>
				<tr>
					<th>주문번호</th>
					<td>${info.partner_order_id }</td>					
				</tr>
				<tr>
					<del>상품명</del>
					<td>${info.item_name }</td>
				</tr>
				<tr>
					<th>상품수량</th>
					<td>${info.quantity }</td>
				</tr>
				<tr>
					<th>결제금액</th>
					<td>${info.amount.total }</td>
				</tr>
				<tr>
					<th>결제방법</th>
					<td>${info.payment_method_type }</td>
				</tr>
			</table>
					
		</div>
	</div>
	
	<jsp:include page="../footer/Footer.jsp"></jsp:include>
	
	<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/utils/login.js?ver=1"></script>
</body>

</html>