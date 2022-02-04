$(document).ready(function() {
   
   $('#level').collapse('show')
   $('#charged').collapse('show')
   
   $('#side_1').on('click', function () {

      if ($('#side_1 > i').attr('class') == 'fas fa-chevron-right') {
         $('#side_1 i').attr('class', 'fas fa-chevron-down')
         $('#side_1_menu').collapse('toggle')
      } else {
         $('#side_1 i').attr('class', 'fas fa-chevron-right')
         $('#side_1_menu').collapse('toggle')
      }
   })

   $('#side_2').on('click', function () {

      if ($('#side_2 > i').attr('class') == 'fas fa-chevron-right') {
         $('#side_2 i').attr('class', 'fas fa-chevron-down')
         $('#side_2_menu').collapse('toggle')
      } else {
         $('#side_2 i').attr('class', 'fas fa-chevron-right')
         $('#side_2_menu').collapse('toggle')
      }
   })

   $('#side_3').on('click', function () {
      if ($('#side_3 > i').attr('class') == 'fas fa-chevron-right') {
         $('#side_3 i').attr('class', 'fas fa-chevron-down')
         $('#side_3_menu').collapse('toggle')
      } else {
         $('#side_3 i').attr('class', 'fas fa-chevron-right')
         $('#side_3_menu').collapse('toggle')
      }
   })
   
   $('#level_btn').on('click', function () {
      if ($('#level_btn > i').attr('class') == 'fas fa-chevron-right') {
         $('#level_btn i').attr('class', 'fas fa-chevron-down')
         $('#level').collapse('toggle')
      } else {
         $('#level_btn i').attr('class', 'fas fa-chevron-right')
         $('#level').collapse('toggle')
      }
   })
   
   $('#charged_btn').on('click', function () {
      if ($('#charged_btn > i').attr('class') == 'fas fa-chevron-right') {
         $('#charged_btn i').attr('class', 'fas fa-chevron-down')
         $('#charged').collapse('toggle')
      } else {
         $('#charged_btn i').attr('class', 'fas fa-chevron-right')
         $('#charged').collapse('toggle')
      }
   })
   
   $('.sm_category').on('click', function() {
      console.log($(this).siblings())
      $(this).siblings().css({'content': ''})
   })
})