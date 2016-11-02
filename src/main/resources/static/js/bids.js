$(document).ready(function() {
     $.get("user/1/bids", function(data, status) 
     {
		$.each(data, function(i, item)
		{					
			tr = $('<tr/>');
			tr.append("<td>" + (i + 1) + "</td>");
			tr.append("<td>" + data[i].item + "</td>");
			tr.append("<td>" + data[i].description + "</td>");
			tr.append("<td>" + data[i].startBidValue + "</td>");
			tr.append("<td>" + data[i].bidEndTime + "</td>"); 
			tr.append("<td>" + data[i].bidTime + "</td>"); 
			//tr.append("<td> <input disabled='disabled 'id="+ "oldBid_"+data[i].bidId+ " type=\"text\" value="+ data[i].bidValue +"> </td>");
			tr.append("<td>" + data[i].bidValue + "</td>");
			tr.append("<td> <input id="+ "newBid_"+data[i].bidId+ " type='number'  step='1' pattern='[0-9]' min='0'> </td>");	
			
			tr.append($('<button/>', 
			{
				text: 'UPDATE', 
				class :'btn-primary',
			    id: "updateBid_"+ data[i].bidId,
			    val : data[i].bidId,
			    click: function() 
			    {   
				    $.ajax(
				    {
						url : 'bids/'+data[i].bidId,
						type : 'PATCH',
						contentType : 'application/json',
						data : JSON.stringify({
						"bidId": data[i].bidId,
						"bidValue": Math.floor($("#newBid_"+$(this).val()).val())  //this denotes current buttonand taking its default value
						 }),
						 dataType : 'json',
						 success : function (response)
						    {
								bootbox.alert({
						    	    message: " Bid Updated for the item "+data[i].item,
						    	    size: 'small'
						    	});
								//$("#oldBid_"+data[i].bidId).val($("#newBid_"+val).val());
						    },
						    error: function (xhr, ajaxOptions, thrownError) {
						    	bootbox.alert({
						    	    message: "Sorry Bid Update Unsucessful either Bid Amount is lower than Bid Initial amount or Bid Time is over",
						    	    size: 'small'
						    	});
						    }	    
		            });
				    $("#newBid_"+data[i].bidId).val('');
					//$("#newBid_"+$(this).val()).prop("disabled", true);
					//$(this).prop("disabled", true);
					                 
			    }
			}));  
			
			
			tr.append($('<button/>', 
					{
						text: 'DELETE', 
						class :'btn-info',
					    id: "deleteBid_"+ data[i].bidId,
					    val : data[i].bidId,
					    click: function() 
					    {   
						    $.ajax(
						    {
								url : 'bids/'+data[i].bidId,
								type : 'DELETE',
								contentType : 'application/json',
								success : function (response)
								    {
										bootbox.alert({
								    	    message: " Bid Deleted for the item "+data[i].item,
								    	    size: 'small'
								    	});
										//$("#oldBid_"+data[i].bidId).val($("#newBid_"+val).val());
								    },
								    error: function (xhr, ajaxOptions, thrownError) {
								    	bootbox.alert({
								    	    message: "Sorry Unable to delete  the bid as Bid Results are out",
								    	    size: 'small'
								    	});
								    }	    
				            });
						    $("#newBid_"+data[i].bidId).val('');
							//$("#newBid_"+$(this).val()).prop("disabled", true);
							//$(this).prop("disabled", true);
							                 
					    }
					}));
			
			$('#itemsTable').append(tr);
	    });      
	});
});	