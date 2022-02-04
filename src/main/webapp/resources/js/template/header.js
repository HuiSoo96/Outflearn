$(document).ready(function () {
	
	$('#menu').css({
		'position': 'relative',
		'margin-bottom': '10px'
	})
    $(window).scroll(function (event) {
       if ($(window).scrollTop() < 50) {
          $('#menu').css({
             'position': 'relative',
             'margin-bottom': '10px'
          });
          $('#header').css({'position': 'relative', 'top': '0'})
       } else {
          $('#menu').css({
             'position': 'fixed',
             'margin-bottom': '10px',
             'z-index': '1000'
          });
       }
    });
	
	$('.server_list').hide()
	
	$('.server, .fa-chevron-right').mouseover(function(){
		$(this).children('.server_list').show()
	})
	
	$('.server, .fa-chevron-right').mouseleave(function(){
		$('.server_list').hide()
	})
	
	$('.bs-btn').on('click', function() {
		$('.collapse').collapse('toggle')
	})
})

