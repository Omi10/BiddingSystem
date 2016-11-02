$('#searchButton').click(function(){
     $.get("/items/filter",{categoryId: $('#categorySelector').val() }).done( function(data, status) 
     {
		$.each(data, function(i, item)
		{					
			tr = $('<tr/>');
			tr.append("<td>" + (i + 1) + "</td>");
			tr.append("<td>" + data[i].name + "</td>");
			//tr.append("<td>" + data[i].category + "</td>");
			tr.append("<td>" + data[i].description + "</td>");
			tr.append("<td>" + data[i].startBidAmount + "</td>");
			tr.append("<td>" + data[i].bidEndTime + "</td>");
	        tr.append("<td> <input id="+"bidValueTextBox"+data[i].itemId+ " type=\"number\"> </td>");	
	        //tr.append("<button type='button' class='btn btn-info btn-sm' data-toggle='modal' data-target='#myModal' id="+data[i].itemId+">"+"Open Modal"+"</button>");
			   
	        tr.append($('<button/>', 
			{
				text: 'Send My Bid', 
	            id: "bidButton"+data[i].itemId,
	            val: data[i].itemId ,
			    click: function() 
			    {   
			    	console.log($("#bidValueTextBox"+$(this).val()).val());
					    $.ajax(
					    { 
					    	   url : '/items/'+data[i].itemId+'/bids',
						       type : 'POST',
						       contentType : 'application/json',
						       data : JSON.stringify
						       ({
							       "bidderId": 1,
							       "bidValue": $("#bidValueTextBox"+$(this).val()).val()  
						       }),
								dataType : 'json',
								success : function (response)
							    {
									bootbox.alert({
							    	    message: " Bid Logged for the item "+data[i].name,
							    	    size: 'small'
							    	});
									//alert("Hello "+ $("#bidValueTextBox"+data[i].itemId).val());
									$("#bidValueTextBox"+data[i].itemId).prop("disabled", true);
									$("#bidButton"+data[i].itemId).prop("disabled", true);
									//$("#bidValueTextBox"+data[i].itemId).prop("disabled", true);
							    },
							    error: function (xhr, ajaxOptions, thrownError) {
							    	bootbox.alert({
							    	    message: "Sorry Bid Unsucessful as bid Amount is lower than  bid initial amount",
							    	    size: 'small'
							    	});
							    	$("#bidValueTextBox"+data[i].itemId).val('');
							    }	    
					    });
				    //$("#bidValueTextBox"+$(this).val()).prop("disabled", true);
				    //$("#bidValueTextBox"+$(this).val()).val('');
	                //$(this).prop("disabled", true);
					                 
			    }
			}));             
			$('#itemsTable').append(tr);
	    });      
	});
   });