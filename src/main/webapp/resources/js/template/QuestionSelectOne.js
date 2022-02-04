$(document).ready(function() {
	
	$('.summernote').summernote({
		height: 300,
		  codemirror: { 
			  theme: 'monokai'
	   }
	})
	
	$('.update-complete-btn').hide()
	
	$('.update-btn').on('click', function() {
		$(this).parents('div.question_content').children('.question-content').hide()
		$(this).hide()
		$(this).parents().children('.update-complete-btn').show()
	})
	
	$('.cancel-btn').on('click', function() {
		$(this).parent().hide()
		$(this).parents('div.question_content').children('.question-content').show()
		$(this).parents('div.update-btn-div').children('.update-btn').show()
	})
	
	$('.student_question_panel:first-child').prepend('<h3>질문 제목</h3>')
	$('.student_question_panel:first-child p:last-child').prepend('<h3>질문 내용</h3>')
  		
})