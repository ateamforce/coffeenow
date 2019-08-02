var mainTable = $('#mainCategoriesTableCFN'); // Main datatable
var newOrUpdateButton = $("#newOrUpdateMainTableRowButtonCFN"); // new or update button for main datatable

// close the tools sidebar on clicking outside of it and reset the form
actionLayer.on("click", function(e){
	$("#pixieWrapper").addClass("hidden");
	$("#addImageCFN i").removeClass("fa-check");
	$("#addImageCFN i").addClass("fa-info-circle");
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
	// reset the description
	$("#itemDescriptionCFN").val("");
	// reset the productcategories
	$('#itemProductsCategoriesCFN').multiselect('deselectAll', false);
	$('#itemProductsCategoriesCFN').multiselect('updateButtonText');
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
			let productToUpdate = data;
			// set hidden id
			$("#itemIdCFN").val(productToUpdate["id"]);
			// set title
			$("#itemTitleCFN").val(productToUpdate["title"]);
			// set description
			$("#itemDescriptionCFN").val(productToUpdate["description"]);
			// set any extracategories/productcategories it may have
			let categoriesCount = productToUpdate["productcategoriesList"].length;
			if ( categoriesCount ) {
				let idArray = [];
				// get all category ids to an array
				for(var i = 0; i < categoriesCount; i++){
					idArray.push(productToUpdate["productcategoriesList"][i].id);
				}
				// make the selection
				$('#itemProductsCategoriesCFN').multiselect('select', idArray);
			}
			
		}
	});
	
}

// function that forwards a yesNo delete message/action
function deleteRow(rowId){
	yesNo(mainTable.attr("data-cfn-delUrl") + rowId, true, language_JSON[locale]["deleteInform"]);
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
			{ "width": "300px", "targets": "descriptionHeaderCFN" },
			{ "width": "100px", "targets": "imageHeaderCFN" },
			{ "width": "100px", "targets": "optionsHeaderCFN" },
			{ "width": "70px", "targets": 3 }
		]
	});
	
});

// Handle pixie opening
// handle the clicking of the "open/close tools sidebar"
$(document).on("click", "#addImageCFN", function(e){
	
	$("#pixieWrapper").removeClass("hidden");
	
	//initialize pixie
	var pixie = new Pixie({
		googleFontsApiKey: 'AIzaSyBGFXVtyLNycSUNkdPTFuSHMffVs1KAtHw',
		tools: {
			crop: {
				replaceDefault: true,
				items: ['1:1']
			}
		},
		ui: {
			theme: 'light',
			mode: 'overlay',
			showExportPanel: false,
			openImageDialog: {show: true}
		},
		onClose: function() {
			$("#pixieWrapper").html("<pixie-editor></pixie-editor>");
		},
		onSave: function(data, name) {
			$("#itemImageCFN").val(data);
			$("#addImageCFN i").removeClass("fa-info-circle");
			$("#addImageCFN i").addClass("fa-check");
			pixie.close();
		}
		
	});

});