// Toggle the side navigation with cookie
$("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
	$("body").toggleClass("sidebar-toggled");
	$(".sidebar").toggleClass("toggled");
	if ($(".sidebar").hasClass("toggled")) {
		$('.sidebar .collapse').collapse('hide');
		Cookies.set('adminSidebarToggled', '1');
	}
	else {
		Cookies.expire('adminSidebarToggled'); 
	}
});