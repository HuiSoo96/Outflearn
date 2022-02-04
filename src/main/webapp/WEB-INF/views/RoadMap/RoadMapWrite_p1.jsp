<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>

<script>
(function($){
   $(document).ready(function() {
      $('#summernote').summernote({
    	placeholder: '로드맵에 대해 설명해주세요',
      	height: 300,
      	width:910,
    	lang: 'ko-KR' // 언어 세팅
        });
      });
   
})(jQuery);
</script>

<style type="text/css">

.container-fluid{
margin-top: 5%;
margin-bottom: 1px;
background-color: #fff;
}

.form-control{
float: left;
}

.form-group{
content: "";
display: block;
clear: both;
padding-bottom: 5%;
}

.row{
margin-bottom: 5%;

}
.cen{
text-align: center;
}


</style>


</head>
<body>
<!-- Header  -->
<jsp:include page="../header/MainHeader.jsp"></jsp:include>
<!-- Header  -->

<div class="container">
	<div class="row" style="margin-top: 2%;">
		<div class="col-md-12" id="top_top">
			<h3>
			<i class="fas fa-check" style="color:#6372ff; "></i><span style="color: #6372ff; padding-right: 2%;">로드맵 소개하기</span> <span style="color: #777;">강의 등록하기</span>
			
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">	
			<div id="mid_right">	
			  	 <form action="roadInsert" method="post" id="roadMapForm">
					 <!-- hidden : 사용자 번호 -->
					 <input type="hidden" name="user_num" value='<sec:authentication property="principal.user_num"/>'>
					 <div class="form-group cen">
					  <input type="text" name="roadmap_title" id="roadmap_title" class="form-control" style="width: 60%;" placeholder="로드맵 제목을 입력해주세요">  
					  <select name="main_num" class="form-control" id="main_num" style="width: 20%;">
					   	<option value="1">서버</option>
					  	<option value="2">웹 개발</option>
					  	<option value="3">데이터베이스</option> 	  	
					  </select>
					  </div>			
					  <div id="heyhey">
					    <textarea id="summernote" name="roadmap_content"></textarea>    
					  </div>
				 </form>

				</div> 
		</div>
	</div>
	<div class="row cen">
		<div class="col-md-12" >
				<button class="btn btn-success" type="button" id="submitBtn">
			<span>다음 페이지</span><i class="fas fa-arrow-right" style="margin-left: 5%;"></i>
			</button>	
			</div>
	</div>
</div>


<script type="text/javascript">
var checkUnload = true;

$(window).on("beforeunload", function(){
	if(checkUnload) return "페이지를 벗어나면 작성된 내용은 저장되지않습니다."
});

$(function(){
	$("#submitBtn").click(function(){
		var title = $("input[name=roadmap_title]").val();
		var content = $("#summernote").val();
		
		if(title=="" || title==null){
			Swal.fire({
				  type: 'error',
				  title: '제목을 입력해주세요'
				   
				})
			
			
		}else if(content=="" || content==null){
			Swal.fire({
				  type: 'error',
				  title: '내용을 입력해주세요'
				   
				})
			
			
		}else{
			checkUnload = false;
			document.getElementById('roadMapForm').submit();
		}
	})
})



</script>
	
	
<!--footer  --> 
<jsp:include page="../footer/Footer.jsp"></jsp:include>
<!--footer  --> 
</body>
</html>