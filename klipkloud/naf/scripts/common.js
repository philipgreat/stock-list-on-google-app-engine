   function  fillResult(reqURI, container)
    {
    	
    	var request = $.ajax({
			url : reqURI,
			type : "GET",
			dataType : "html"
		});

		request.done(function(msg) {
			$(container).html(msg);
		});

		request.fail(function(jqXHR, textStatus) {
			//alert("Request failed: " + textStatus);
			$(container).html("failed: "+textStatus)
		});    	
    }