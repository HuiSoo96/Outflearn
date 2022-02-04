function main() {

	(function () {

	      $(window).scroll(function (event) {
	         if ($(window).scrollTop() < 120) {
	            $('#menu').css({
	               'position': 'relative',
	               'margin': '0'
	            });
	            $('#header').css({'position': 'relative', 'top': '0'})
	         } else {
	            $('#menu').css({
	               'position': 'fixed',
	               'z-index': '9999'
	            });
	         }
	      });

		'use strict';

		$('a.page-scroll').click(
			function () {
				if (location.pathname.replace(/^\//, '') == this.pathname
					.replace(/^\//, '')
					&& location.hostname == this.hostname) {
					var target = $(this.hash);
					target = target.length ? target : $('[name='
						+ this.hash.slice(1) + ']');
					if (target.length) {
						$('html,body').animate({
							scrollTop: target.offset().top - 50
						}, 900);
						return false;
					}
				}
			});

		$('body').scrollspy({
			target: '.navbar-default',
			offset: 80
		});

		// Hide nav on click
		$(".navbar-nav li a").click(function (event) {
			// check if window is small enough so dropdown is created
			var toggle = $(".navbar-toggle").is(":visible");
			if (toggle) {
				$(".navbar-collapse").collapse('hide');
			}
		});

		// Nivo Lightbox 
		$('.portfolio-item a').nivoLightbox({
			effect: 'slideDown',
			keyboardNav: true,
		});

	}());

	$('.dropdown-menu a.dropdown-toggle').on(
		'click',
		function (e) {
			if (!$(this).next().hasClass('show')) {
				$(this).parents('.dropdown-menu').first().find('.show')
					.removeClass("show");
			}
			var $subMenu = $(this).next(".dropdown-menu");
			$subMenu.toggleClass('show');

			return false;
		}
	);
	
	$('div.item:nth-child(1)').addClass('active')
}

main();
