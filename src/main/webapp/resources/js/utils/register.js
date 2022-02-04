	var emailChk = false;
	var idChk = false;
	var pass_chk = false;
	var pass_cfm = false;
	
	var widgetId1;
	

	
	var onloadCallback = function(){
		widgetId1 = grecaptcha.render('example1', {
	        'sitekey' : '6LfLH7EUAAAAAPJ3xrEZt88OjmhZpV-NeGjq0YU3',
	        'theme' : 'light'
	    });
	}
	
	function sendEmail(){

		var user_email = $("input[name=user_email]").val();
		if(user_email == null || user_email == ""){
			alert("이메일을 입력해 주세요");								
		}else{
			$.ajax({
				url:"sendEmail.do",
				type:"POST",
				data:"email="+user_email,
		
				success:function(data){
					alert("이메일 전송 완료")
					$("input[name=code]").change(function(){
						if($("input[name=code]").val()==data){
							$("div[id=confirm]").html('');
							emailChk = true;
						}else{
							$("div[id=confirm]").html('');
							$("div[id=confirm]").html('인증실패 다시 쓰세요');
							$("div[id=confirm]").val("");
							emailChk = false;
						}
					});
				},
				error:function(){
					
				}
			});
		}
		
	}
	
	function sendEmailPw(){
		var user_id = $("input[name=user_id]").val();
		var user_email = $("input[name=user_email]").val();
		var param = {userId : user_id, userEmail : user_email}
		if(user_email == null || user_email == ""){
			alert("이메일을 입력해 주세요");								
		}else{
			$.ajax({
				url:"sendEmailPw.do",
				type:"POST",
				data: param,
				success:function(data){
					alert("이메일 전송 완료")
					$("input[name=code]").change(function(){
						if($("input[name=code]").val()==data){
							$("div[id=confirm]").html('');
							emailChk = true;
														
						}else{
							$("div[id=confirm]").html('');
							$("div[id=confirm]").html('전송실패 다시 쓰세요');
							$("div[id=confirm]").val("");
							emailChk = false;
						}
					});
				},
				error:function(){
					
				}
			});
		}
		
	}
	
	function ranNumChk(){
		var ranNumPass = $("input[name=ranNum]").val();
		
		$.ajax({
			url:"emailNumCheck.do",
			type:"POST",
			data:"ranNumPass=" + $.trim(ranNumPass),
	        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			success:function(data){
				if(ranNumPass == null || ranNumPass == ""){
					
					$("div[id=confirm]").html('');
					$("div[id=confirm]").html('<b>인증번호를 입력해 주세요</b>');
					$("div[id=confirm]").val("");
				}else{
					console.log(data.ranChk);
					if(data.ranChk != true){
						ranNumChk = false;
						$("div[id=confirm]").html('');
						$("div[id=confirm]").html('<b style="color:red;">인증번호 확인 실패</b>');
						$("div[id=confirm]").val("");
					}else{
						ranNumChk = true;
						$("div[id=confirm]").html('');
						$("div[id=confirm]").html('<b style="color:green;">인증번호 확인 완료</b>');
						$("div[id=confirm]").val("");
					}
				}
			},
			error:function(){
				alert("이메일체크 에러")
			}
		})
	}
	
	function id_check(){
		
		var user_id = $("input[name=user_id]").val();
		if(user_id == null || user_id == ""){
			alert("아이디를 입력하세요");
		}else{
		
			$.ajax({
				url:"idChk.do",
				type:"POST",
				data : "id="+user_id,
				success:function(data){
					if(data.idChk != true){
						idChk = false;
						
						$(".result .msg").text('이미 사용중인 아이디입니다.');
						$(".result .msg").attr("style", "color:#f00")
						$(".result .msg").val("");
					}else{
						idChk = true;
						
						 $(".result .msg").text("사용 가능한 아이디입니다.");
						 $(".result .msg").attr("style", "color:#00f");
						 $(".result .msg").val("");
					}
				},
				error:function(){
					alert("아이디 확인 실패");
				}
			});
		}
	}
	
function idChkPw(){
		
		var user_id = $("input[name=user_id]").val();
		if(user_id == null || user_id == ""){
			alert("아이디를 입력하세요");
		}else{
			$.ajax({
				url:"idChk.do",
				type:"POST",
				data : "id="+user_id,
				
				success:function(data){
					if(data.idChk != true){
						var idChk = false;
						
						$(".result .msg").text('등록된 아이디 입니다.');
						$(".result .msg").attr("style", "color:#00f");
						$(".result .msg").val("");
					}else{
						
						 $(".result .msg").text("아이디가 없습니다.");
						 $(".result .msg").attr("style", "color:#f00");
						 $(".result .msg").val("");
					}
				},
				error:function(){
					alert("아이디 확인 실패");
				}
			});
		}
	}
	
	function email_Chk(){
		
		var user_email = $("input[name=user_email]").val();
		if(user_email==null|| user_email==""){
			$("div[id=emailDupChk]").html('');
			$("div[id=emailDupChk]").html('이메일을 입력해주세요.');
			$("div[id=emailDupChk]").attr("style","color:green")
			$("div[id=emailDupChk]").val("");
		}else{
			$.ajax({
				url:"emailChk.do",
				type:"POST",
				data:"user_email="+user_email,
				success:function(data){
					 
						if(data.emailChk != true){
						emailChk = false;
							$("div[id=emailDupChk]").html('');
							$("div[id=emailDupChk]").html('사용할 수 없는 메일입니다.');
							$("div[id=emailDupChk]").attr("style","color:red")
							$("div[id=emailDupChk]").val("");
					    } else if(data.emailChk == true) {
					    emailChk = true;
					    	$("div[id=emailDupChk]").html('');
					    	$("div[id=emailDupChk]").html('사용 가능한 메일입니다.');
							$("div[id=emailDupChk]").attr("style","color:blue");
							$("div[id=emailDupChk]").val("");
					    	
					    }
					
					
				}, error : function(){
					alert("이메일 유효성 확인 실패")
				}
			
			})
		}
	
	}
	
function emailChkPw(){
		
		var user_email = $("input[name=user_email]").val();
		if(user_email==null|| user_email==""){
			emailChk = false;
			$("div[id=emailDupChk]").html('');
			$("div[id=emailDupChk]").html('이메일을 입력해주세요.');
			$("div[id=emailDupChk]").attr("style","color:green")
			$("div[id=emailDupChk]").val("");
		}else{
			$.ajax({
				url:"emailChk.do",
				type:"POST",
				data:"user_email="+user_email,
				success:function(data){
					 
						if(data.emailChk != true){
						emailChk = false;
							$("div[id=emailDupChk]").html('');
							$("div[id=emailDupChk]").html('이메일 확인되었습니다.');
							$("div[id=emailDupChk]").attr("style","color:blue");
							$("div[id=emailDupChk]").val("");
					    } else if(data.emailChk == true) {
					    emailChk = true;
					    	$("div[id=emailDupChk]").html('');
					    	$("div[id=emailDupChk]").html('등록되지 않은 메일입니다.');
					    	$("div[id=emailDupChk]").attr("style","color:red");
					    	$("div[id=emailDupChk]").val("");
					    	
					    }
					
					
				}, error : function(){
					alert("이메일 유효성 확인 실패")
				}
			
			})
		}
	
	}
	
	function pass_check(){
		var length = $("input[name=user_pw]").val().length;
		if(length<8){
			$("div[id=pass_check]").html('');
			$("div[id=pass_check]").html('비밀번호 8자리이상 입력해 주세요');
			$("div[id=pass_check]").val("");
			pass_chk = false;
		}else{
			$("div[id=pass_check]").html('');
			pass_chk = true;
		}
	}
	
	function pass_confirm(){
		var once = $("input[name=user_pw]").val();
		var twice = $("input[name=user_pw02]").val();
		if(once != twice){
			$("div[id=pass_confirm]").html('');
			$("div[id=pass_confirm]").html('비밀번호 같지 않습니다.');
			$("div[id=pass_confirm]").val("");
			pass_cfm = false;
		}else{
			$("div[id=pass_confirm]").html('');
			$("div[id=pass_confirm]").html('비밀번호 같음 확인');
			$("div[id=pass_confirm]").val("");
			pass_cfm = true;
		}
	}
	
	function formsubmit(){
		if(grecaptcha.getResponse(widgetId1) != null && grecaptcha.getResponse(widgetId1) != "" && emailChk == true &&	idChk == true && pass_chk == true && pass_cfm == true){
			alert("회원가입 완료!");
			return true;
		}
		alert("다시 확인해 주세요!")
		return false;
	}