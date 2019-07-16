var currentUrl = window.location.href;

// fix language redirect url, which cannot work correctly because of base meta tag
function languageUri(param){
	
	// remove any query params
	if ( currentUrl.indexOf("?") ) {
		let urlParts = currentUrl.split("?");
		currentUrl = urlParts[0];
	}
	
	// check for slash at the end and remove it
	if (currentUrl.charAt(currentUrl.length - 1) == "/") 
		currentUrl = currentUrl.substr(0, currentUrl.length - 1);
	
	// add clicked link href to final url
	currentUrl = currentUrl + param;
	
	window.location = currentUrl;
	
}

$(document).ready(function() {
	
	// DATATABLES initialization (every page has at least one)
	$('.dataTableCFN').DataTable();
	
});
