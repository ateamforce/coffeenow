var mainTable = $('#mainCategoriesTableCFN'); // Main datatable

// function that forwards a yesNo delete message/action
function deleteRow(rowId){
	yesNo(mainTable.attr("data-cfn-delUrl") + rowId, true, language_JSON[locale]["deleteInform"]);
}

function selectThis(e, className){
	$(e).closest('form').find('.' + className).prop("checked", true).trigger("click");
}

$(document).on("click", ".accountStatusToggleCFN", function(e){
	var target = $(e.target);
	target.closest("form").trigger("submit");
});

$(document).ready(function() {
	
	// productcategories/extrascategories DATATABLE	(id always first)
	var table = mainTable.DataTable({
		"language": {
            "url": language_JSON[locale]["dataTableLanguageURL"]
        },
		info: false,
		responsive: true,
		columnDefs: [
			{ 
				responsivePriority: 1, 
				targets: "idHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 2, 
				targets: "nameHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 3, 
				targets: "statusHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 4, 
				targets: "optionsHeaderCFN" ,
				
			},
			{ "width": "20px", "targets": "idHeaderCFN" },
			{ "width": "100px", "targets": "statusHeaderCFN" },
			{ "width": "100px", "targets": "optionsHeaderCFN" }
		]
	});
	
});