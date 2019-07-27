var mainTable = $('#mainCategoriesTableCFN'); // Main datatable
var newOrUpdateButton = $("#newOrUpdateMainTableRowButtonCFN"); // new or update button for main datatable
var savedParentList = $("#itemParentCFN").html(); //  to be used for saving the state of the original parent category selection dropdown

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
	// reset the parent
	$("#itemParentCFN").html(savedParentList);
	// reset the extracategories/productcategories
	$('#itemProductsCategoriesCFN').multiselect('deselectAll', false);
	$('#itemProductsCategoriesCFN').multiselect('updateButtonText');
	// reset the products/extras
	$('#itemExtrasCFN').multiselect('deselectAll', false);
	$('#itemExtrasCFN').multiselect('updateButtonText');
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
			let categoryToUpdate = data;
			// set hidden id
			$("#itemIdCFN").val(categoryToUpdate["id"]);
			// set title
			$("#itemTitleCFN").val(categoryToUpdate["title"]);
			// set parent if it is a child
			if ( categoryToUpdate["parent"] != categoryToUpdate["id"] ) {
				$("#itemParentCFN option").each(function(){
					if ( $(this).val() == categoryToUpdate["parent"] ) {
						$(this).prop("selected", true);
						return false;
					}
				});
			}
			else {
				$("#itemParentCFN option").each(function(){
					if ( $(this).val() == categoryToUpdate["id"] ) {
						$(this).remove();
						return false;
					}
				});
			}
			// set any extracategories/productcategories it may have
			let categoriesCount = categoryToUpdate["productcategoriesList"].length;
			if ( categoriesCount ) {
				let idArray = [];
				// get all category ids to an array
				for(var i = 0; i < categoriesCount; i++){
					idArray.push(categoryToUpdate["productcategoriesList"][i].id);
				}
				// make the selection
				$('#itemProductsCategoriesCFN').multiselect('select', idArray);
			}
			// set any products/extras it may have
			let itemCount = categoryToUpdate["extrasList"].length;
			if ( itemCount ) {
				let idArray = [];
				// get all item ids to an array
				for(var i = 0; i < itemCount; i++){
					idArray.push(categoryToUpdate["extrasList"][i].id);
				}
				// make the selection
				$('#itemExtrasCFN').multiselect('select', idArray);
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
		orderFixed: [1, 'asc'],
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
                    .append( '<td colspan="'+rows.columns().count()+'"><span class="pad-right-30">'+group+'</span>'+title+'</td>' );
			},
			endRender: null,
            dataSrc: 1
        },
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
			{ "width": "70px", "targets": 3 },// extrasCategories or productCategories
			{ "width": "70px", "targets": 4 },// products or extras
			{ "width": "100px", "targets": "imageHeaderCFN" },
			{ "width": "100px", "targets": "optionsHeaderCFN" }
		]
	});
	
});