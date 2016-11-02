$(document).ready(function() {
	
	 $.get("categories" ,function(data, status)
	 {
		var cat;
	    $.each(data, function (i, item) 
		{
			cat += "<option value="+data[i].id+">"+ data[i].category+ "</option>";
		}); 
	    $('#categorySelector').append(cat);
	 });
	 
	 $(function() {
         $("input,select,textarea").not("[type=submit]").jqBootstrapValidation();
     });
	
   $("#uploadForm").submit(function() {
	   
	 $.ajax({
	       url : 'items',
	       type : 'POST',
	       contentType : 'application/json',
	       data : JSON.stringify({
		       "name": document.getElementById("uploadForm").elements.namedItem("itemName").value,
		       "startBidAmount": document.getElementById("uploadForm").elements.namedItem("startBidAmount").value,
		       "description": document.getElementById("uploadForm").elements.namedItem("description").value,
		       "bidEndTime" : document.getElementById("uploadForm").elements.namedItem("bidEndTime").value.toString(),
		       "categoryId" : document.getElementById("uploadForm").elements.namedItem("categorySelector").value
		       }),
	       dataType : 'json',
	       success: function(data)
	       {
	    	   bootbox.alert({
		    	    message: " Item uploaded successfully",
		    	    size: 'small'
		    	});
	       }
	       
	       });
     });
});