				
					//<![CDATA[
				    // 사용할 앱의 JavaScript 키를 설정해 주세요.
              	    Kakao.init('85e61dfa4e850de4478ab8c6df1a737a');
				    // 카카오 로그인 버튼을 생성합니다.
				    Kakao.Auth.createLoginButton({
				      container: '#kakao-login-btn',
				      success: function(authObj) {

				    	  Kakao.API.request({
				    		 url: '/v1/user/me',
				    		 success: function(res){
				    			 
				    			res.id += "@k";	

				    			
				    			$.ajax({
				    				url: "idChk.do",
				    				data: "id="+res.id,
				    				success: function(data){
				    				
				    					if(data.idChk==true){ //비회원이면 가입 후 로그인
				    						
				    						alert("회원가입이 진행됩니다");
				    						
				    						
				    						 var kakaoid = res.id;
				    						 var idChk = true;
				    						 
				    						 document.write('<form action="/Outflearn/snsLogin" id="sub_form" method="post">'+
				    				                        '<input type="hidden" name="id" value="'+kakaoid+'">'+
//				    				                        '<input type="hidden" name="'+${_csrf.parameterName}+'" value="'+${_csrf.token }+'">'+
				    				                        '<input type="hidden" name="idCheck" value="'+idChk+'">'+
				    						   				'</form>');
				    				         document.getElementById("sub_form").submit();				    						 
				    						 
				    						
				    					}else{ //회원이면 바로 로그인
				    						 var kakaoid = res.id;
				    						 var idChk = false;
				    						 
				    						 
				    						 document.write('<form action="/Outflearn/snsLogin" id="sub_form" method="post">'+
				    				                        '<input type="hidden" name="id" value="'+kakaoid+'">'+
//				    				                        '<input type="hidden" name="'+${_csrf.parameterName}+'" value="'+${_csrf.token }+'">'+
				    				                        '<input type="hidden" name="idCheck" value="'+idChk+'">'+
				    						   				'</form>');
				    				         document.getElementById("sub_form").submit();					    						
				    						
				    						
				    					}
				    				}
				    			});
				    		 },
				    		 fail: function(error){
				    			 alert(JSON.stringify(err));
				    		 }
				    	  });
				      },
				      fail: function(err) {
				         alert(JSON.stringify(err));
				      }
				    });
				  //]]>