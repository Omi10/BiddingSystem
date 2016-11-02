$(document).ready(function() {
     $.get("user/1/bids/resultsOut", function(data, status) 
     {
		$.each(data, function(i, item)
		{					
			tr = $('<tr/>');
			tr.append("<td>" + (i + 1) + "</td>");
			tr.append("<td>" + data[i].item + "</td>");
			tr.append("<td>" + data[i].description + "</td>");
			tr.append("<td>" + data[i].startBidValue + "</td>");
			tr.append("<td>" + data[i].bidEndTime + "</td>"); 
			//tr.append("<td> <input disabled='disabled 'id="+ "oldBid_"+data[i].bidId+ " type=\"text\" value="+ data[i].bidValue +"> </td>");
			tr.append("<td>" + data[i].bidValue + "</td>");
//			tr.append("<td> <input id="+ "newBid_"+data[i].bidId+ " type=\"number\"> </td>");																
			
			tr.append($('<button/>', 
			{
						text: 'See Results',
						class :'btn-primary',
			            id: "Bid Results"+data[i].bidId,
			            val: data[i].bidId ,
					    click: function() 
					    {   
					    	$.get('items/'+data[i].itemId+'/bids/result',function(resp,status)
					    	{
					    		 if(resp.resultOut==true)
					    	     {
					    			 if(resp.winner==null)
					    				 resp.winner="----";
						    		 bootbox.alert({
						    			 message: "<html>Results of "+data[i].item+"</body> <table border='true' text-align='center' width='100%'> <tr> <th> Total Bids </th> <th> " +
								    	    		"Winner name </th>  <th> Final BidAmount </th> </tr> <tr> <td>"
								    	            +resp.totalBids +"</td> <td>"+ resp.winner +"</td> <td> "
								    	            +resp.finalBidAmount+ "</td></tr>",
								    	    size: 'medium'
								    	});
					    	     }
					    		 else
					    		 {
					    			 bootbox.alert({
								    	    message: "<html></body> <h3>Result is not out yet </h3> </body></html>",
								    	    size: 'small'
								    	});
					    		 }
					    		 
					    	});
					    	$("#bidWinner"+data[i].itemId).prop("disabled", true);
					    }
			})); 
			
			tr.append($('<button/>', 
					{
								text: 'Check all Bids',
								class :'btn-info',
					            id: "Bids List"+data[i].itemId,
					            val: data[i].itemId ,
							    click: function() 
							    {   
							    	$.get('items/'+data[i].itemId+'/bids',function(resp,status)
							    	{
							    		 var history="<html></body> <table border='true' text-align='center' width='100%'> " +
							    		        "<caption>Bids on  <b>"+ resp[0].item+ "</caption>"+
							    		 		"<tr> <th> S.No.</th> <th> Bid Value </th> <th> Bid Time </th> " +
						    	    		"  <th> Made By </th> "
							    		 for(j=0;j<resp.length;j++)
							    		 {
							    			 history+="<tr><td>"+ (j+1) +"</td>"+
							    			 "<td>"+ resp[j].bidValue +"</td>"+
							    			 "<td>"+ resp[j].bidTime +"</td>" +
							    			 "<td>"+ resp[j].madeBy +"</td>";
							    		 }
							    		 history+="</table></body></html>";
							    		 bootbox.alert({
									    	    message: history,
									    	    size: 'medium'
									    	});
							    		 
							    	});
							    }
					}));
			
			$('#itemsTable').append(tr);
	    });      
	});
});	