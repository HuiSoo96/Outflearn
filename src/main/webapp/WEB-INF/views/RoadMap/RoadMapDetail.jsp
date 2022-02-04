<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<meta name="description" content="">
<meta name="author" content="">
<title>Outflearn</title>
<!-- 카톡공유 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- 제이쿼리 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


<style type="text/css">
.container-fluid{
margin-top: 1px;

}

header{
background-color:#2c2e37;
text-align: center;
padding-top: 2%;
height: 100%;
color: #e4e5ed;

}



.inner_Wrapper, .social_cnts{
padding-bottom: 2%;

}


.floating_card{ 
position: fixed; 
top: 33%;
bottom: 40%;
text-align:center; 
width: 20%;
}

.link_share, .kakao_share{
float: left;
width:40px;
height:40px;
border-radius:75px;
text-align:center;
margin-left:  8%;
}
.kakao_share{
border-color: #ffc903;
background-color: #ffc903;
color: #220e01;
}
.link_share{

border-color: #6372ff;
background-color: #fff;
color: #6372ff;
}



.enroll{
width: 100%;
height: 95px;
margin-top: 1rem;
border-radius: 4px;
border: 1px solid;
background-color: #fff;
font-weight: 800;
font-size: 18px;
align-items: center;
cursor: pointer;

}
.provider_card{
width: 100%;
height: 95px;
margin-top: 1rem;
border: 1px solid;
text-align: center;
/* margin-top: 5%; */
font-size: 18px;
text-align: center;
background-color: #fff;
border-radius: 4px;
align-items: center;

}

.social_buttons{
margin-top: 5%;
}

#classTable{
/*width: 60%;*/
height: 100%;
margin: auto;
text-align: center;
}

.icon{
cursor: pointer;
}
#logo{
    font-family: 'Raleway', sans-serif;
    font-size: 20px;
    font-weight: 800;
    color: #6372ff;
    text-transform: uppercase;
    text-align: center;

}
.ysk{
width: 90%;

}
</style>




</head>
<body>	
<!-- Header  -->
<jsp:include page="../header/MainHeader.jsp"></jsp:include>
<!-- Header  -->
<sec:authorize access="isAuthenticated()">
<input type="hidden" id="userNum" value='<sec:authentication property="principal.user_num"/>'>
<sec:authentication var="num" property="principal.user_num"/>
</sec:authorize>
<input type="hidden" id="roamdMapNum" value="${roadMap.roadmap_num }">
<div class="container-fluid">
	<div class="row">
		<header class="roadmap_detail_header">
			<div class="inner_Wrapper">
				<h1>
				${roadMap.roadmap_title }
				</h1>
			</div>
			<div class="social_cnts">
				<span class="cnt" title="구독자 수" style="cursor: pointer;">
				<i class="fas fa-user" style="margin-right:5px;"></i>:<span class="social_cnt" style="margin-left: 5px; margin-right:1%; ">${roadMap.roadmap_subscribe }</span>			
				</span>
				<span title="포함된 강좌 수 " style="cursor: pointer;">
				<i class="fas fa-book" style="margin-right:5px;"></i>:<span style="margin-left: 5px; margin-right:1%;"><c:out value="${fn:length(resList) }"/></span>		
				</span>
			</div>
		</header>
	</div>
	<div class="row" style="margin: 1%;">
		<div class="col-md-8 ysk" style="padding-left: 10%;">
			<div class="roadMapInfo">
			${roadMap.roadmap_content }	
			</div>
		</div>
		<aside class="col-md-4">
			<div class="floating_card"> 			
			<div style="margin-bottom: 12%;">
				<!-- 구독취소/구독하기 -->
				<c:choose>
					<c:when test="${roadChk eq true }">
						<button class="btn btn-outline-secondary" title="구독취소버튼" class="roadCancle" style="float: right;" onclick="roadmap_unsubscribe();">
						<span>구독취소하기</span><i class="fas fa-sign-out-alt" style="margin-left: 3%;"></i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="enroll" onclick="roadmap_subscribe();">
						<i class="fas fa-plus "></i>
						<span style="padding-left: 1%;"> 로드맵 참여하기</span>
						</button>					
					</c:otherwise>
				</c:choose>
				</div>
				<!-- 로드맵 만든사람 -->
				<div class="provider_card">
					<div class="author">
						<div class="author" style="margin-top: 5%;">
							<span class="author_label">만든사람</span>
							<span class="author_name"><b>${roadMap.user_nickname }</b></span>
						</div>
					</div>
					<div class="another" style="margin-top: 1%;">
					<a href="void:0" class="other_roadmap">
					<span style="padding-right: 1%;">다른 로드맵</span> <i class="fas fa-angle-double-right"></i>
					</a>
					</div>
			     </div>
			     <!-- 장바구니 -->
			     <div class="basket_card" >
				 <c:if test="${!empty basketList }">
					 	<table id="basketTable" class="table" style="text-align: center;">					 	
					 		<c:forEach items="${basketList }" var="basketDto">					 			
					 			<tr>
					 				<td><input type="checkbox" name="class_num" value="${basketDto.class_num }"></td>
					 				<td>
									<img  width="100" height="100" src="${pageContext.request.contextPath }/resources/uploadImage/${ basketDto.class_img }" alt="Card image cap">
					 				</td>
					 				<td>
					 				<p>${basketDto.class_title }</p><p>${basketDto.class_price }</p>
					 				</td>					 				
					 			</tr>					 							 		
					 		</c:forEach>
					 		<tr>
					 		<td colspan="3"><button class="btn btn-secondary" id="basketBtn"><span>장바구니 담기</span><i class="fas fa-shopping-cart" style="margin-left: 3%;"></i></button></td>
					 		</tr>	
					 	</table>					 					 
				 </c:if>
				 </div>
		   		<!-- 주소복사 카톡공유 -->
				<div class="social_buttons" >
					<div class="shares">
						<button type="button" class="link_share" data-clipboard-text="${URL }">
						<i class="fas fa-share-alt"></i>
						</button>
						<!-- 카카오링크 공 -->
						<a id="kakao-link-btn" href="javascript:sendLink()">
						<button type="button" class="kakao_share">
						<i class="fas fa-comment"></i>
						</button>
						</a>
							
				</div>
				</div>
			</div>
		</aside>
	</div>

	<div class="row" >
	<div class="ysk" style="padding-left: 10%;">
		<c:if test="${!empty resList }">
		<article  >
		<div class="col-md-8" id="classDetail">
		<table class="table" id="classTable">	
	
			<c:forEach items="${resList }" var="resDto" varStatus="status">
			<tr id="roadmap_card">
			<td>			
				<div class="number_wrap">

					<span class="num">${status.count}</span>

				</div>
			</td>							
			<td>
				<div class="card_block">
					<div class="card">
						<a href="LectureDetail?class_num=${resDto.class_num }"> 
						<img   src="${pageContext.request.contextPath }/resources/uploadImage/${ resDto.class_img }" alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${resDto.class_title }</h5>
							
						</div>
						</a>
					</div>
				</div>
			</td>
			<td>			
				<div class="card_detail">
					<div class="desc">
						<p class="card-text">
						${resDto.class_intro }
						</p>
	
					</div>
					</div>
				</td>	
			</tr>
			</c:forEach>	
		</table>
		</div>
		</article>
		</c:if>			
	</div>
	</div>
	
	<div class="row" style="margin-bottom: 1%;">
	<div style="padding-left: 10%;" class="ysk">
	<sec:authorize access="isAuthenticated()">
		<div class="col-md-8">
			  <div class="form-group">
			      <label for="exampleTextarea">로드맵에대한 피드백을 제시해주세요</label>
			      <textarea class="form-control" id="myComment" rows="3" ></textarea>
			      <button class="btn btn-secondary" type="button" id="btnSave">등록</button>
			   </div>	
		</div>
	</sec:authorize>	
		<div class="col-md-8">
			<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
				
				<div id="commentList" ></div>

			</div> 	
		</div>

	</div>
	</div>
	
<!-- 댓글의 답댓글 모달창 -->	
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close rn" data-dismiss="modal">×</button>
          <h4 class="modal-title"><span id="logo">Outflearn</span></h4>
        </div>
        <div class="modal-body">
         <input type="hidden" id="numArea" value="">
         <textarea rows="5" cols="78" id="modal_content"></textarea>
        </div>
        <div class="modal-footer">        
          <button type="button" class="btn btn-secondary rn" data-dismiss="modal" >취소하기</button>
          <button type="button" class="btn btn-primary" id="AddBtn" >댓글저장</button>          
          
        </div>
      </div>
      
    </div>
  </div>
</div>	

<script type="text/javascript">

$(function(){
	
	$("#AddBtn").click(function(){
		
		var content = $("#modal_content").val();
		var comNum = $("#numArea").val();
		var roadNum = $("#roamdMapNum").val();
		var userNum = $("#userNum").val();
		
		var param = {"user_num":userNum , "comment_content":content, "roadmap_num":roadNum, "comment_num":comNum}
		
		if(content=="" || content == null){
			Swal.fire({
				  type: 'error',
				  title: '내용을 작성해주세요!'
				})
				return false;
		}
				
		$.ajax({
			url:"addReComment",
			type:"POST",
			data:param,
			success: function(data){
	//			alert(data);
				if(data == true){
					$(".modal-body #modal_content").val("");	
					$(".modal-body #numArea").val("");
					$("#myModal").modal('hide');
					comment_List();
				}
				
			}
		
		})
		
	});
	
	//모달 취소눌렀을 때 내용지우기	
	$(".rn").click(function(){		
		$(".modal-body #modal_content").val("");	
		$(".modal-body #numArea").val("");		
	});
	
});


</script>



<script type="text/javascript">

$(function() {
	//댓글리스트 갱신 함수
	comment_List();
	
	$("#btnSave").click(function(){
		comment_add();
	});	
});

function comment_add(){

var roadNum = $("#roamdMapNum").val();
var Content = $("#myComment").val();
var userNum = $("#userNum").val();
	
var param = {"roadmap_num":roadNum, "comment_content":Content, "user_num":userNum}


if(Content =="" || Content ==null){
	Swal.fire({
		  type: 'error',
		  title: '내용을 작성해주세요!'
		})
		
		return false;
}


$.ajax({
	url: "addComment",
	type: "get",
	data: param,
	success: function(data){
//		alert(data);
//		alert(data.resChk);
		if(data.resChk==true){
//			alert("성공!");
			$("#myComment").val("");//저장 후 돌아오면 입력된 내용 지우기	
			comment_List(); //댓글 목록 새로고침
		}
	}
});
	
}

function comment_List(){

	var roadNum = $("#roamdMapNum").val();
	var userNum = $("#userNum").val();
	
	$.ajax({
		url: "commentList",
		type: "POST",
		data: "roadnum="+roadNum,
		dataType: "json",
		success: function(msg){	
			
			var htmls = "";
			
			if(msg.length < 1){
				htmls += '<div class="media text-muted pt-3 commentdiv" >';
				htmls += '<h3>===등록된 댓글이 없습니다===</h3>';
				htmls += '</div>';
				
			}else{
				
                $(msg).each(function(){    
                             	               	
                    htmls += '<div class="media text-muted pt-3 commentdiv" id="rid' + this.comment_num + '">';
                    htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                    htmls += '<span class="d-block">';
                    htmls += '<strong class="text-gray-dark">' + this.user_nickname + '</strong>';
                    htmls += '<span style="padding-left: 7px; font-size: 9pt">';

					if(userNum != null && this.user_num == userNum && this.comment_content != '=====삭제 된 댓글입니다 ====='  ) {
                    htmls += '<a onclick="editComment(' + this.comment_num + ', \'' + this.user_nickname + '\', \'' + this.comment_content + '\' )" style="padding-right:5px" class="icon">수정</a>';
                    htmls += '<a onclick="deleteComment(' + this.comment_num + ')" class="icon" >삭제</a>';
					}
					
                    htmls += '</span>';
                    htmls += '</span>';
                    htmls += '</p>';
                    if(this.comment_titletab>0){
                    htmls += '<p><i class="far fa-hand-point-right"></i>';
                    htmls += this.comment_content;
                    htmls += '</p>';                    	
                    	
                    }else{
                    htmls += '<p>';
                    htmls += this.comment_content;
                    htmls += '</p>';                    	
                    	
                    }

                    if(this.comment_content != '=====삭제 된 댓글입니다 =====' ){
                    htmls += '<p>';
                    htmls += '<sec:authorize access="isAuthenticated()">';
                    htmls += '<a class="icon" onclick="showModal('+this.comment_num+')" >답글달기</a>';
                    htmls += '</sec:authorize>';
                    htmls += '</p>';
                    }
                    htmls += '</div>';

               });
			}			
			$("#commentList").html(htmls);
		}
	});
	
}

function editComment(comNum,nickName,content){
	var	htmls = "<div class='media text-muted pt-3' id='rid"+comNum+"'>"+
	"<p class='media-body pb-3 mb-0 small lh-125 border-bottom horder-gray'>"+
	"<span class='d-block'>"+
	"<strong class='text-gray-dark'>"+nickName+"</strong>"+
	"<span style='padding-left: 7px; font-size: 9pt'>"+
	"<span style='padding-right: 5px;'><c:out value='${today }'></c:out></span>"+
	"<a onclick='updateComment("+comNum+");' style='padding-right: 5px;' class='icon' >저장</a>"+
	"<a onclick='back()' class='icon'>취소</a>"+
	"</span>"+
	"</span>"+
	"</p>"+
	"<p>"+
	"<textarea class='form-control' id='editContent' rows='3' >"+
	 content +
	"</textarea>"+
	"</p>"+
	"</div>";

$('#rid'+comNum).replaceWith(htmls);
$('#rid'+comNum+'#editContent').focus();
}

function updateComment(comNum){
	
	var Content = $("#editContent").val();
	var param = {"content":Content,"ComNum":comNum}
	
	$.ajax({
		url: "commentUpdate",
		type: "POST",
		data: param,
		success: function(data){
//			alert(data);
//			alert(data.res);
			if(data.res == true){
				comment_List();
			}
		}	
	});
		
}

function deleteComment(comnum){
	
	
	$.ajax({
		url:"deleteComment",
		type:"POST",
		data:"comNum="+comnum,
		success: function(data){
			
			if(data.Chk == true){
			
				comment_List();
			}
		}
	});
}

function back(){
	comment_List();
}

function showModal(comNum){

	$(".modal-body #numArea").val(comNum);
	$("#myModal").modal('show');
	
}


</script>



<script type="text/javascript">
$("#basketBtn").click(function(){
	if($("input[name=class_num]:checked").length==0){
		Swal.fire({
			  type: 'error',
			  title: '하나 이상 체크해주세요!'
			})
			
			return false;
	}
	
	var checkedarray = [];
	$("input[name=class_num]:checked").each(function(i){
		checkedarray.push($(this).val());
	});
	
	var userNum = $("#userNum").val();
	
	var param = {"array" : checkedarray, "usernum": userNum}
	
//	alert(checkedarray);
	$.ajax({
		url:"AddToCart",
		type:"POST",
		data:param,
		success:function(data){
//			alert(data);
//			alert(data.cartRes);
			if(data.cartRes==true){
	 			Swal.fire({
	 				  type: 'success',
	 				  title: '추가완료',
 					  showCancelButton: false,
 					  confirmButtonColor: '#3085d6',
 					  confirmButtonText: '확인!'
	 				}).then((result)=>{
	 					if(result.value){
	 						window.location.reload();
	 					}
	 				})				

			}
			
		},
		error:function(){
			alert("장바구니 추가 실패!");
		}
	});
	
	
});


</script>
<script type='text/javascript'>
//카톡 링크공유 
  //<![CDATA[
    // // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('85e61dfa4e850de4478ab8c6df1a737a');
    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    function sendLink() {
      Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: '${roadMap.roadmap_title }',
          description: 'by ${roadMap.user_nickname }',
          imageUrl: 'https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60',
          link: {
            mobileWebUrl: '${URL}',
            webUrl: '${URL}'
          }
        },
        
        buttons: [
          {
            title: '웹으로 보기',
            link: {
              mobileWebUrl: '${URL}',
              webUrl: '${URL}'  
            }
          }
        ],
        serverCallbackArgs: '{"key":"value"}' // 콜백 파라미터 설정
      });
      
      kakaoTest();
    }
  //]]>
</script>
	
<script type="text/javascript"> 

//게시판 URL복사
 $(function(){
	
 	$('.link_share').click(function(){
	
 		var URL = window.location.href;		
 		var clip = new ClipboardJS('.link_share');
 		
 		clip.on('success', function(e){
 			Swal.fire({
 				  type: 'success',
 				  title: '주소가 복사되었습니다'
 				})
 			
 		});
 		clip.on('error',function(e){
 			Swal.fire({
				  type: 'warning',
				  title: '주소 복사 실패!!'
				})
 		});
		
 	});
 })

//로드맵 구독
function roadmap_subscribe(){
	var roadNum = $("#roamdMapNum").val();
	var userNum = $("#userNum").val();
	
	if(userNum == "" || userNum == null){		
			Swal.fire({
				  type: 'success',
				  title: '로그인창으로 이동합니다',
				  showCancelButton: false,
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: '확인!'
				}).then((result)=>{
					if(result.value){
						location.href="beforeRoadSub?roadnum="+roadNum;		
					}
				})		
		
		//location.href="beforeRoadSub?roadnum="+roadNum;			
	}
	else{
	
	var param = {roadnum : roadNum, usernum : userNum}
	
	$.ajax({
		url: "roadMapJoin",
		type: "POST",
		data:param,
		success:function(data){			
			if(data.res==true){		
			window.location.reload();//이렇게 새로고침을 넣어야 하는건지 잘 모르겠음!!!
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }		
	});
	
	}
} 
 
 //로드맵 구독 취소 
 function roadmap_unsubscribe(){

	var roadNum = $("#roamdMapNum").val();
	var userNum = $("#userNum").val();
	
	var param = {roadnum : roadNum, usernum : userNum}	
	
	$.ajax({
		url: "roadUnsubscribe",
		type: "POST",
		data: param,
		success:function(data){

			if(data.res==true){
			window.location.reload();
			}
		},
		error:function(){
			
		}
	});
	
 }
</script>



<script type="text/javascript" src="resources/js/template/LectureList.js"></script>
<!-- clipBoard -->
<script src="resources/js/clipboard/dist/clipboard.min.js"></script>

<!--footer  -->
<jsp:include page="../footer/Footer.jsp"></jsp:include>
</body>
</html>