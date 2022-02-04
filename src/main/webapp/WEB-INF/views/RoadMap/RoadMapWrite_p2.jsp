<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#classInfoTable, #BTNarea{
text-align: center;
}
.forP{
margin-right: 2%;
}

.row{
margin-bottom: 5%;

}
input[name=class_title]{
text-align: center;
width: 100%;
}
</style>
</head>


<body>
<!-- Header  -->
<jsp:include page="../header/MainHeader.jsp"></jsp:include>
<!-- Header  -->

<div class="container">
	<div class="row" style="margin-top: 2%;">
		<div class="col-md-12">
			<h3>
			<span style="color: #777; padding-right: 2%;">로드맵 소개하기</span> <i class="fas fa-check" style="color:#6372ff; "></i><span style="color: #6372ff;">강의 등록하기</span>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
		<!-- 세션추가 -->			 
			<button title="강의 추가" class="btn btn-success" type="button" id="targer" onclick="addSession(this);">
				<i class="far fa-plus-square fa-1x"></i>
			</button>
			<form action="roadNclass" method="post" id="classInfoForm">
			<input type="hidden" name="seq" value="${roadSeq }">			
			<table id="classInfoTable" class="table" >
			
				<tbody id="addtr">
					<tr>
						<td>
						<input type="text"  name="class_title" readonly="readonly" id="real1" >
						<input type="hidden" name="class_num"  readonly="readonly" id="test1" >
						</td>
						<td>
						<button title="강의 찾기" type="button" class="btn btn-success forP" onclick="searchClass(this.id)" id="1"><i class="fas fa-search-plus"></i></button>
						<button title="맨 위로" type="button" class="btn btn-success forP" onclick="moveTop(this)" ><i class="fas fa-angle-double-up"></i></button>
						<button title="위로" type="button" class="btn btn-success forP" onclick="moveUp(this)"><i class="fas fa-angle-up"></i></button>
						<button title="아래로" type="button" class="btn btn-success forP" onclick="moveDown(this)"><i class="fas fa-angle-down"></i></button>
						<button title="맨 아래로" type="button" class="btn btn-success forP" onclick="moveBottom(this)"><i class="fas fa-angle-double-down"></i></button>
						<button title="삭제"  type="button" class="btn btn-success forP" onclick="deleteLine(this)"><i class="far fa-trash-alt"></i></button>
						</td>
												
					</tr>			
				</tbody>				
			</table>
		</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" id="BTNarea">
			 
			<button class="btn btn-success" type="button" onclick="location.href='RoadMapUpdate_p1?roadNum=${roadSeq }'">
				<i class="fas fa-arrow-left"></i><span>로드맵1p수정</span> 
			</button> 
			<button class="btn btn-success"  id="submitBtn">
			<span>제출하기</span><i class="fas fa-arrow-right"></i>
			</button>
		</div>
	</div>
</div>

<script type="text/javascript">

var checkUnload = true;

$(window).on("beforeunload", function(){
	if(checkUnload) return "페이지를 벗어나면 작성된 내용은 저장되지않습니다."
});


$("#submitBtn").click(function(){
	
	var length = $("input[name='class_title']").length;
	var roadNum = $("input[name='seq']").val();

	
	if(roadNum =="" || roadNum ==null){
		Swal.fire({
			  type: 'error',
			  title: '로드맵 소개하기를 <br/>먼저 작성해주세요'				   
			})	
		return false;	
	}
	
	if(length<4){
		Swal.fire({
			  type: 'error',
			  title: '강의 영상을 4개이상 등록해주세요!'				   
			})		
	}
	
	var beforeChk = EmptyChk();
	
	if(beforeChk == true && length>=4 && roadNum !="" && roadNum !=null ){
		checkUnload = false;
		document.getElementById('classInfoForm').submit();
	}
});

</script>
<script type="text/javascript" >

var Cnt = 2;

function addSession(){
//	alert("cnt="+Cnt);
	
	var chk = EmptyChk();
	if(chk==true){
	
	var tr = document.createElement("tr");
		
	var str = "<td>" +
	  "<input type='text'  name='class_title' readonly='readonly' id='real"+Cnt+"'>"+
	  "<input type='hidden' name='class_num'  readonly='readonly' id='test"+Cnt+"'>"+
	  "</td>"+
	  "<td>" +
	  "<button title='강의 찾기' type='button' class='btn btn-success forP' onclick='searchClass(this.id)' id="+Cnt+"><i class='fas fa-search-plus'></i></button>"+
	  "<button title='맨 위로' type='button' class='btn btn-success forP' onclick='moveTop(this)'' ><i class='fas fa-angle-double-up'></i></button>"+
	  "<button title='위로' type='button' class='btn btn-success forP' onclick='moveUp(this)'' ><i class='fas fa-angle-up'></i></button>"+
	  "<button title='아래로' type='button' class='btn btn-success forP' onclick='moveDown(this)'' ><i class='fas fa-angle-down'></i></button>"+
	  "<button title='맨 아래로' type='button' class='btn btn-success forP' onclick='moveBottom(this)'' ><i class='fas fa-angle-double-down'></i></button>"+
	  "<button title='삭제' type='button' class='btn btn-success forP' onclick='deleteLine(this)'' ><i class='far fa-trash-alt'></i></button>"+
	  "</td>"+
	  "</tr>";
			  
	tr.innerHTML=str;	
	document.getElementById("addtr").appendChild(tr);
	Cnt +=1;
	}
}
</script>

<script type="text/javascript">

function EmptyChk(){
	
	var length = $("input[name='class_title']").length;
	
	for(var i=0; i<length; i++){
		if($("input[name='class_title']")[i].value =="" || $("input[name='class_title']")[i].value ==null){
			Swal.fire({
				  type: 'error',
				  title: '항목을 모두 작성해주세요!'				   
				})				
				return false;
		}
	}	
	
	return true;
}
</script>

<script type="text/javascript">

function searchClass(btnId){
	
	var numArray = [];
	var numLenth = $("input[name='class_num']").length;	
	
	for(var i=0; i<numLenth;i++){
		numArray[i]=$("input[name='class_num']")[i].value;
	}
	
		
//	alert(btnId);
	var url="searchWingogo";
	
	window.name = "parentForm";//부모창 이름
	//window.open("open할 윈도우","자식창 이름","팝업창 옵션");
	window.open(url+"?btnIdVal="+btnId+"&numArray="+numArray,"searchWindow" ,"width=400,height=400,left=600");
	
}

function deleteLine(el){

	var trCnt = $('tbody>tr').size()
	
	if(trCnt == 1){
		Swal.fire({
			  type: 'error',
			  title: '더이상 삭제할 수 없습니다!'				   
			})				
	}else{
		var tr = $(el).closest('tr');
		tr.remove();
	}
	
	
}

function moveTop(el){
	var tr = $(el).closest('tr');
	tr.closest('table').find('tr:first').before(tr);	
}
function moveUp(el){
	var tr = $(el).closest('tr');
	tr.prev().before(tr);	
}
function moveDown(el){
	var tr = $(el).closest('tr');
	tr.next().after(tr);	
}
function moveBottom(el){
	var tr = $(el).closest('tr');
	tr.closest('table').find('tr:last').after(tr);	
}


</script>

<!--footer  --> 
<jsp:include page="../footer/Footer.jsp"></jsp:include>
<!--footer  --> 
</body>
</html>