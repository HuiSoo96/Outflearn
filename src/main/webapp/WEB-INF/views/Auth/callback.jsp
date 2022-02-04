<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
<!-- (1) LoginWithNaverId Javscript SDK -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>


<!-- (2) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
<script>
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "{xsVfuuGnIVuwdJhzN_0M}",
			callbackUrl: "{http://localhost:8787/Outflearn/naverCallback}",
			isPopup: false,
			callbackHandle: true
		}
	);
	naverLogin.init();

	/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
	window.addEventListener('load', function () {
		naverLogin.getLoginStatus(function (status) {
			
			
			if (status) {
				
				var uniqId = naverLogin.user.getId();
				uniqId +="@n";
				console.log(uniqId);
				
				$.ajax({
    				url: "idChk.do",
    				method:"POST",
    				data: "id="+uniqId,
    				success: function(data){
    					
    					if(data.idChk==true){
    						alert("회원가입을 진행합니다");
    						
    						var param = {"id":uniqId , "idCheck":data.idChk}
    						
    						$.ajax({
    							url: "snsLogin",
    							method: "POST",
    							data: param,
    							success: function(){
    								window.location.href = 'http://localhost:8787/Outflearn/'
    							}  
    						});
    						
    						
    						
    					}else{
    						
    						var param = {"id":uniqId , "idCheck":data.idChk}
    						
    						$.ajax({
    							url: "snsLogin",
    							method: "POST",
    							data: param,
    							success: function(){
    								window.location.href = 'http://localhost:8787/Outflearn/'
    							}  
    						});  
    						
    					}
    				}
					
				});
				
			} 
			else {
				console.log("callback 처리에 실패하였습니다.");
			}
		});
			
	});
	

</script>

</body>
</html>