var email_chk = false;

var pwSameChk = false;

function pwChk(){
	
	var user_pw = $("input[name=now_pw]").val();
	if(user_pw==null|| user_pw==""){
		pwChk = false;
		$("div[id=currentPwChk]").html('비밀번호를 입력해주세요.');
		$("div[id=currentPwChk]").attr("style","color:r")
	}else{
		$.ajax({
			url:"currentPwChk.do",
			type:"POST",
			data:"user_pw="+user_pw,
			success:function(data){
				 
					if(data.pwChk != true){
					pwChk = false;
						$("div[id=currentPwChk]").html('비밀번호 확인되었습니다.');
						$("div[id=currentPwChk]").attr("style","color:blue")
				    } else if(data.pwChk == true) {
				    pwChk = true;
				    	$("div[id=currentPwChk]").html('비밀번호가 잘못되었습니다.');
				    	$("div[id=currentPwChk]").attr("style","color:red")
				    	
				    }
				
				
			}, error : function(){
				alert("비밀번호 유효성 확인 실패")
			}
		
		})
	}

}
$(function(){ // 새 비밀번호와 다시 입력한 새 비밀번호가 같은지 검사, 8자리 검사
		$("#pw_success").hide();
		$("#pw_fail").hide();
        $("input").keyup(function(){
        	var nowPw=$("#now_pw").val();
        	var newPw=$("#new_pw").val();
            var newPwChk=$("#new_pw_chk").val();
            if(nowPw==null||nowPw==""||newPw==null||newPw==""||newPwChk==null||newPwChk==""){
            	$("div[id=currentPwChk]").html('비밀번호를 입력해주세요.');
        		$("div[id=currentPwChk]").attr("style","color:red")
        		 pwSameChk = false;
            }
            
            if(newPw.length < 8 && newPwChk.length < 8){
            	$("#pass_check").show();
            	$("#pass_check").html("비밀번호 8자리이상 ");
            	 pwSameChk = false;
            } else {
            	$("#pass_check").hide();
            }
            if(newPw != "" || newPwChk != ""){
                if(newPw == newPwChk){
                    $("#pw_success").show();
                    $("#pw_fail").hide();
                    $("#pw_change_btn").removeAttr("disabled");
                    pwSameChk = true;
                    
                }else{
                    $("#pw_success").hide();
                    $("#pw_fail").show();
                    $("#pw_change_btn").attr("disabled", "disabled");
                    pwSameChk = false;
                }    
            }
        });

    	// 이메일 입력방식 선택
    	$("#select_email").change(function(){
    		$("#select_email option:selected").each(function(){
    			
    			if($(this).val() == "1") { // 직접입력인 경우
    				$("#email2").val(""); // 값 초기화
    				$("#email2").prop("readonly", false); //활성화
    			} else { // 직접입력이 아닌 경우
    				$("#email2").val($(this).text()); // 선택된 값으로 입력
    				$("#email2").prop("readonly", true); //비활성화
    			}
    		});
    	});
	});
	
	function pw_change() { // 기존 비밀번호와 새 비밀번호가 같은지 체크
		var now_pw = $("#now_pw").val();
		var new_pw = $("#new_pw").val();
		var new_pw_chk = $("#new_pw_chk").val();
		if(now_pw != "" && new_pw_chk != "" && now_pw != null && new_pw_chk != null) {
			if(now_pw == new_pw_chk) {
				alert("기존 비밀번호와 같습니다.");
			} else { // 기존 비밀번호와 새 비밀번호가 다르면 업데이트하는 db로 submit한다.
				if(new_pw_chk.length <= 7 && new_pw_chk.length <= 7)
					alert("암호가 8자리 이하인 곳이 있습니다.");
				 pwSameChk = false;
			}
			
		} else {
			alert("빈칸이 있습니다.");
		}
	}
	
	
	
	function withdraw() {
		var user_id = $("input[name=id]").val();
		var result = confirm("탈퇴하시겠습니까?"); 
		if(result) { 
			$("form").prop("action","../../withdraw.do?user_id="+user_id);
			$("form").submit();
		} else { 
			
		}
	}
	
	function formsubmit(){
		if((pwSameChk != null && pwSameChk == true)){
			alert("회원정보 수정 완료!");
			return true;
		} else if (pwSameChk == false) {
			alert("회원정보 수정 실패!");
			return false;
		} 
	}
	
	