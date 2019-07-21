var currentUrl = window.location.href;
var mainModal = $("#logoutModal"); // main modal for messages
var mainTable = $('#mainCategoriesTableCFN'); // Main datatable
var newOrUpdateButton = $("#newOrUpdateMainTableRowButtonCFN"); // new or update button for main datatable
var deleteButton = $("#deleteMainTableRowButtonCFN"); // delete button for main datatable
var newOrUpdateItemFormCFN = $("#newOrUpdateItemFormCFN"); // form for adding a new main item

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

// function to ask user yes or no with a modal for an action
function yesNo(
	yesUrl, 
	openModal = false, 
	headerMessage = "", 
	bodyMessage = language_JSON[locale]["areYouSure"], 
	yesButtonText = language_JSON[locale]["yes"], 
	noButtonText = language_JSON[locale]["no"]
)
{
	mainModal.find(".modal-content")
	.html(
		'<div class="modal-header"><h5 class="modal-title" id="logoutModalLabel">'
		+ headerMessage +
		'</h5><button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div><div class="modal-body">'
		+ bodyMessage +
		'</div><div class="modal-footer"><form id="yesNoFormCFN" action="'
		+ yesUrl +
		'"><button type="button" class="btn btn-secondary no"><span class="text-white">'
		+ noButtonText +
		'</span></button><button type="button" class="btn btn-primary yes"><span class="text-white">'
		+ yesButtonText +
		'</span></button></form></div>'
	);
	if ( openModal ) mainModal.modal('show');
}
// handling yes click of a yesNoFormCFN, generated by function yesNo
$(document).on("click", "#yesNoFormCFN .yes", function(e){
	$("#yesNoFormCFN").submit();
});
// handling no click of a yesNoFormCFN, generated by function yesNo
$(document).on("click", "#yesNoFormCFN .no", function(e){
	mainModal.modal('hide');
	$("#yesNoFormCFN").remove();
});

// injecting logout form in modal on click
$(document).on("click", "#userMenuLogoutCNF", function(e){
	
	e.preventDefault;
	yesNo(
		$(this).attr("href"), 
		false, 
		language_JSON[locale]["logoutAsk"],
		language_JSON[locale]["logoutInfo"],
		language_JSON[locale]["logout"],
		language_JSON[locale]["cancel"]
	)
	
});

// function that decides if a row is applicable for deletion before trying to delete it
function deleteRow(rowId){
	
	var doDelete = true;
	
	mainTable.find('tr[role="row"]').each(function(){
		if (($(this).find(".rowIdCFN").html() != rowId) &&  ($(this).find(".parentIdCFN").html() == rowId)) {
			doDelete = false;
			return false;
		}
	});
	
	if ( doDelete ){
		yesNo("administrator/dashboard/productcategories/delete/" + rowId, true, language_JSON[locale]["deleteInform"]);
	}
	
}

$(document).ready(function() {
	
	// productcategories/extrascategories DATATABLE	needs specific order of columns :
	// 1. id 
	// 2. parent id
	// 3. title
	// anything else after that
	var table = mainTable.DataTable({
		orderFixed: [1, 'asc'],
		select: {
			style: 'single'
		},
		info: false,
		responsive: true,
        rowGroup: {
			startRender: function ( rows, group ) {
				var ids = rows.data().pluck(0);
				var titles = rows.data().pluck(2);
				var title = "";
				var i = rows.data().length;
				for(i; i >= 0; i-- ){
					if( ids[i] == group ) title = titles[i];
				}
				return $('<tr/>')
                    .append( '<td colspan="'+rows.columns().count()+'">'+group+' - '+title+'</td>' );
			},
			endRender: null,
            dataSrc: 1
        },
		columnDefs: [
			{ 
				responsivePriority: 1, 
				targets: "titleHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 2, 
				targets: "imageHeaderCFN" ,
				
			},
			{ "width": "20px", "targets": 0 },
			{ "width": "50px", "targets": 1 },
			{ "width": "50%", "targets": 2 }
		]
	});
	
	// ON DATATABLE ROW SELECT
	table.on( 'select', function( event, data, type, indexes ) {
		
		let selectedRow = table[ type ]( indexes ).nodes().to$();
		let selectedRowId = selectedRow.find(".rowIdCFN").html();
		let doDelete = true;
		
		// TODO: find a beter way to do this. possibly need to access the data from the "table" variable
		mainTable.find('tr[role="row"]').each(function(){
			if (($(this).find(".rowIdCFN").html() != selectedRowId) &&  ($(this).find(".parentIdCFN").html() == selectedRowId)) {
				doDelete = false;
				return false;
			}
		});
		
		if (doDelete) {
			deleteButton.removeAttr("disabled");
			deleteButton.removeClass("hidden");
			deleteButton.attr("onclick", "deleteRow("+ selectedRowId +")");
		}
		
		newOrUpdateButton.removeClass("btn-primary");
		newOrUpdateButton.addClass("btn-success");
		newOrUpdateButton.html('<span class="icon text-white"><i class="fas fa-edit"></i></span><span class="text">'+ language_JSON[locale]["update"] +'</span>');
		
	});
	
	// ON DATATABLE ROW DESELECT
	table.on( 'deselect', function( event, data, type, indexes ) {
		
		deleteButton.attr("disabled","");
		deleteButton.addClass("hidden");
		deleteButton.removeAttr("onclick");
		
		newOrUpdateButton.removeClass("btn-success");
		newOrUpdateButton.addClass("btn-primary");
		newOrUpdateButton.html('<span class="icon text-white"><i class="fas fa-plus"></i></span><span class="text">'+ language_JSON[locale]["insert"] +'</span>');
		
	});
	
	$('.multipleSelectCFN_JS').multiselect({
		buttonClass: 'btn btn-secondary btn-sm',
		maxHeight: 200,
        includeSelectAllOption: true,
		enableFiltering: true
	});
	
});

$(window).on('load', function(){
	$("#contentPreLoaderCFN").remove();
});