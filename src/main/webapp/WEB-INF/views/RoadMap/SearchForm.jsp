<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Outflearn</title>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">

<!-- slimSelect -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.23.0/slimselect.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.23.0/slimselect.min.css" rel="stylesheet"></link>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!-- Sweet Alert2 -->
<link href='resources/js/sweetalert/sweetalert2.min.css' rel='stylesheet' />
<script src='resources/js/sweetalert/sweetalert2.min.js'></script>
<style type="text/css">

.basket_item{
float: left;
margin-right: 15%;
}

#top{
background-color:  #6372ff;
color: #fff;
font-weight: 800;
}

.row{
margin-bottom: 3%;

}

table{
text-align: center;
}

</style>
</head>
<body>

<div class="container-fluid">
	<div class="row" id="top">
		<div class="col-md-12">
			<h3>
				강의 검색하기
			</h3>
			
		</div>		
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<form action="searchFilter" method="get" id="filterForm">
				<input type="hidden" id="array" name="array" value="${array }">
				<input type="hidden" id="please" name="please" value="${btnIdVal }">
				<select name="subFilter" id="multiple" multiple>
					<c:forEach items="${mainList }" var="mainDto">
						<optgroup label="${mainDto.main_name }">
							<c:forEach items="${subList }" var="subDto">
								<c:if test="${mainDto.main_num eq subDto.main_num }">
									<option value="${subDto.sub_num }">${subDto.sub_name }</option>
								</c:if>
							</c:forEach>
						</optgroup>
					</c:forEach>	
				</select>
				<input type="submit" value="검색"class="btn btn-secondary" style="margin-top: 2%;" >
			</form>				
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
		<c:choose>
			<c:when test="${empty resList }">
				<table class="table" style="margin-top: 2%;">
					<tr><td>등록된 강의가 없습니다</td></tr>
				</table>
			</c:when>
			<c:otherwise>
				<div style="margin-top: 1%;">
				<input type="text" name=""> <input type="button" value="결과 내 검색하기" class="btn btn-secondary">
				</div>				
				<table class="table" style="margin-top: 2%;">
					<c:forEach items="${resList }" var="resDto">
						<tr>
							<td>
							<div>
							<div class="basket_item">
								<img width="100" height="100" src="${pageContext.request.contextPath }/resources/uploadImage/${ resDto.class_img }" alt="Card image cap">
							</div>
							<div class="basket_item" id="classname">
								<p>${resDto.class_title }</p>
							</div>					
							</div>						
							<input type="hidden" name="resTitle" value="${resDto.class_title }" id="title${resDto.class_num }">					
							<input type="hidden" name="resNum" value="${resDto.class_num }" id="num${resDto.class_num }">
							</td>
							<td><input type="button"  value="선택" id="${resDto.class_num }" name="resBtn" class="btn btn-secondary" ></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
		</div>
	</div>
</div>


<script type="text/javascript">

$("#filterForm").submit(function(){
	
	var option= $("select[name=subFilter]").val();
	if(option=="" || option==null){	
		Swal.fire({
			  type: 'error',
			  title: '값을 한개 이상 체크해주세요!'				   
			})	
		return false;
	}	
		
});

</script>

<script type="text/javascript">
	
	$(function() {
		new SlimSelect({		
			 select: '#multiple'
		})
		
	});	
		
</script>


<script type="text/javascript">

$(function(){

	$("input[name=resBtn]").click(function(){
		
		var Array = document.getElementById("array").value;
		var afterStr = Array.split(',');
		
		//alert("afterstr=="+afterStr.length);
			
		var resBtnId = $(this).attr('id');
		
		for(var i=0; i<afterStr.length-1; i++){
			if(afterStr[i]==resBtnId){
				Swal.fire({
					  type: 'error',
					  title: '이미 선택된 강좌입니다'				   
					})	
				return false;
			}
		}


		var returnVal = document.getElementById("please").value;
		
		opener.document.getElementById("real"+returnVal).value = document.getElementById("title"+resBtnId).value;
		opener.document.getElementById("test"+returnVal).value = document.getElementById("num"+resBtnId).value;		
		window.close();
		
		
	});	

	
	
	
});




</script>

<script type="text/javascript" src="resources/js/template/jquery.1.11.1.js"></script>
<script type="text/javascript" src="resources/js/template/bootstrap.js"></script>
</body>
</html>