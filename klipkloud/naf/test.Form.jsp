<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=GBK"%>

<script type="text/javascript">
	$(function() {
		var action="";
		
		$(".submit").click(function(){
			action=$(this).attr("action");
		});
		
		$(":input").dblclick(function(){		
			//alert("sdfsdf");	
			//$( ".parameters" ).toggle();
			
			var pos = $(this).position();

		    // .outerWidth() takes into account border and padding.
		   
		    //show the menu directly over the placeholder
		    
		    if(!$("#parameters").is(":visible")){
		    	// var width = $(this).outerWidth();
				var internalType=$(this).attr("datatype");		    
		    	fillResult("/naf/suggestParameter/"+internalType+"/","#parameters");
		    }
		    
		    $("#parameters").css({
		        position: "absolute",
		        top: pos.top + pos.height+"px",
		        left: (pos.left) + "px"
		    }).toggle();
			
		
		});
		
		$( window ).resize(function() {
			 // $( "#log" ).append( "<div>Handler for .resize() called.</div>" );
			var currentTargetId= $("#parameters").attr("targetInputId");
			console.log(currentTargetId)	;
			var pos = $("#"+currentTargetId).position();
			 $("#parameters").css({
			        position: "absolute",
			        top: pos.top + pos.height+"px",
			        left: (pos.left) + "px"
			   });
		});
	
		
		var cache = {};

		$(".mainform").submit(function(event) {
			
			if(action==""){
				return;
			}
			$(":input").attr("disabled", true);
			event.preventDefault();
			var parameters = "";
			$("input").each(function() {
				if ($(this).attr("append") == "true") {
					parameters += encodeURIComponent($(this).val()) + "/";
				}				
			});
			var reqURI = "/naf/" + action + "/" + parameters;
			fillResult(reqURI,"#content");

		});
		//var currentTargetId="";
		 $( "form input:text" ).focus(function() {
			 
			 //currentTargetId=$(this).attr("id");
			 //console.log("form input:text clicked: "+currentTargetId)	;
			 $("#parameters").attr("targetInputId",$(this).attr("id"));
			 
		});
		
		//


	});
		
	$(document).ready(function() {
		 $("form:not(.filter) :input:visible:enabled:first").focus();
	
	});	
		
</script>



<style>
.form {
	font-size: 20px;
}
#parameters {
	display: none;
	border: 1px solid grey; 
	width: 400px;
	height: 400px;
	overfollow:auto;
	padding-left: 20px;
	padding-right: 20px;
}
</style>

<div class="form">
	<form class="mainform">
		<c:forEach var="field" items="${result.fields}" varStatus="status">
			<lable>${field.name}</lable>
			<input id="fl${status.index}"  type="text" append="true"  datatype="${field.typeExpr}" autocomplete="off"/>
		</c:forEach>
		
		<c:forEach var="action" items="${result.actions}">
			<input type="submit" class="submit" value="${action.name }" action="${action.name }"  />
		</c:forEach>
		
	</form>


</div>

<div id="parameters" > Parameters </div>


