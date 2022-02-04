$(function(){
	
		$('.summernote').summernote({
			height: 200,
			width: 600,
			  codemirror: { 
				  theme: 'monokai'
		   }
		})
		
		$('.note-editor').addClass('col-sm-10 col-md-10')
		
		$('.review-reply-btn').hide()
		$('.reply-div').hide()
		
		$('.ReviewReply').on('click', function() {
			if($(this).text() == '답글') {
				$(this).parents('.btn-rows').next().toggle()
			}
		})
		
	})
