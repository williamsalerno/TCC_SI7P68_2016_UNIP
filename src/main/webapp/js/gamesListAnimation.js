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
	
	function getParameterByName(name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	
	var idIndex = getParameterByName("id");
	if(idIndex !== null && idIndex !== ""){
		$("#id"+idIndex).show();
	}
	
	var idCollapse = getParameterByName("id");
	if(idCollapse !== null && idCollapse !== ""){
		$("#"+idCollapse).addClass('in');
	}
});
