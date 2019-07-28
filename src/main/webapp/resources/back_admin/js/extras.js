var mainTable = $('#mainCategoriesTableCFN'); // Main datatable
var newOrUpdateButton = $("#newOrUpdateMainTableRowButtonCFN"); // new or update button for main datatable

// close the tools sidebar on clicking outside of it and reset the form
actionLayer.on("click", function(e){
	toolsSidebar.removeClass("expandedCFN");
	actionLayer.addClass("hidden");
	toolsSidebarSwitch.removeClass("open");
	newOrUpdateButton.removeClass("btn-warning");
	newOrUpdateButton.addClass("btn-success");
	newOrUpdateButton.html('<span class="icon text-white"><i class="fas fa-edit"></i></span><span class="text">'+ language_JSON[locale]["insert"] +'</span>');
	
	// reset the hidden id
	$("#itemIdCFN").val("0");
	// reset the title
	$("#itemTitleCFN").val("");
	// reset the extrascategories
	$('#itemExtrasCategoriesCFN').multiselect('deselectAll', false);
	$('#itemExtrasCategoriesCFN').multiselect('updateButtonText');
});

// function that loads a row into the update form
function load(rowId){
	toolsSidebarSwitch.addClass("open");
	newOrUpdateButton.removeClass("btn-success");
	newOrUpdateButton.addClass("btn-warning");
	newOrUpdateButton.html('<span class="icon text-white"><i class="fas fa-edit"></i></span><span class="text">'+ language_JSON[locale]["update"] +'</span>');
	actionLayer.removeClass("hidden");
	toolsSidebar.addClass("expandedCFN");
	
	// make ajax call to get row's needed data
	$.ajax({
		type: 'POST',
		url: mainTable.attr("data-cfn-getOneUrl") + rowId,
		error: function error(data) {
			inform(language_JSON[locale]["somethingWentWrong"]);
		},
		success: function success(data) {
			let extraToUpdate = data;
			// set hidden id
			$("#itemIdCFN").val(extraToUpdate["id"]);
			// set title
			$("#itemTitleCFN").val(extraToUpdate["title"]);
			// set any extracategories/productcategories it may have
			let categoriesCount = extraToUpdate["extracategoriesList"].length;
			if ( categoriesCount ) {
				let idArray = [];
				// get all category ids to an array
				for(var i = 0; i < categoriesCount; i++){
					idArray.push(extraToUpdate["extracategoriesList"][i].id);
				}
				// make the selection
				$('#itemExtrasCategoriesCFN').multiselect('select', idArray);
			}
			
		}
	});
	
}

// function that forwards a yesNo delete message/action
function deleteRow(rowId){
	
	let doDelete = true;
	
	// TODO: find a beter way to do this. possibly need to access the data from the "table" variable
	mainTable.find('tr[role="row"]').each(function(){
		if (($(this).find(".rowIdCFN").html() != rowId) &&  ($(this).find(".parentIdCFN").html() == rowId)) {
			doDelete = false;
			return false;
		 }
	});
	
	if (doDelete) {
		yesNo(mainTable.attr("data-cfn-delUrl") + rowId, true, language_JSON[locale]["deleteInform"]);
	}
	else {
		inform(language_JSON[locale]["cannotDeleteParentWithChildren"]);
	}
}

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
				targets: "titleHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 3, 
				targets: "optionsHeaderCFN" ,
				
			},
			{ 
				responsivePriority: 4, 
				targets: "imageHeaderCFN" ,
				
			},
			{ "width": "20px", "targets": "idHeaderCFN" },
			{ "width": "300px", "targets": "titleHeaderCFN" },
			{ "width": "100px", "targets": "imageHeaderCFN" },
			{ "width": "100px", "targets": "optionsHeaderCFN" },
			{ "width": "70px", "targets": 2 }
		]
	});
	
});