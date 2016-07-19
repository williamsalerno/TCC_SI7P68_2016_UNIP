$(document).ready(function() {
	var first;
	var last;
	$(".thumbnailGame").click(function() {

		first = $(this).attr('id');

		$('#' + last).hide();
		$('#' + first).fadeIn();

		$(".thumbnailGame").removeClass('clickedThumb');
		$(this).addClass('clickedThumb');

		last = $('#' + first).attr('id');

	});
});
