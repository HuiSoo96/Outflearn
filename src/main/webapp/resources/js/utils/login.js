//function loginProcess() {
//    var _id = $("#loginId").val()
//    var _pw = $("#loginPw").val()
//
//    if (_id == null || _id == "") {
//        alert("ID를 확인해 주세요")
//    } else if (_pw == null || _pw == "") {
//        alert("PW를 확인해 주세요")
//    } else {
//        $.ajax({
//            url: "loginDo",
//            type: "post",
//            data: "id=" + _id + "&pw=" + _pw,
//            success: function (msg) {
//                if (msg.loginChk == true) {
//                    location.href = '/Outflearn/';
//                } else {
//                    $('#loginchk').html("ID or PW 확인!")
//                }
//            },
//            error: function () {
//                alert("통신 실패")
//            }
//        })
//    }
//
//}
		
$("#loginform").submit(function(){
	
	var ID = $("input[name=loginId]").val();
	var PW = $("input[name=loginPw]").val();
	

	
	if(ID=="" || ID==null){	
		Swal.fire({
			  type: 'error',
			  title: '아이드를 입력해주세요'
			   
			})
//		alert("아이디를 입력해주세요");
		return false;
	}else if(PW=="" || PW==null){
		Swal.fire({
			  type: 'error',
			  title: '비밀번호를 입력해주세요'
			   
			})
//		alert("비밀번호를 입력해주세요");
		return false;
	}else {
		return true;
	}

	
});
