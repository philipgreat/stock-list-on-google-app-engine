<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=GBK"%>
<style>
.message {
	font-size: 20px;
}

.paramlist {
	font-size: 20px;
	text-align: left;
	overflow-x: hidden;
}
</style>

<script type="text/javascript">
	$(function() {
		
		
		$(".parameterItem").click(function() {			
		
			var currentTargetId= $("#parameters").attr("targetInputId");
			console.log(currentTargetId)	;
			$("#"+currentTargetId).val($(this).html());
			
			$("#parameters").toggle();
			
		});
		

		//

	});
	
	$(document).ready(function() {

		
		
	
	});
	
	
</script>

Parameter from Previous Input
	<hr />

	<div class="paramlist">
		<c:forEach var="item" items="${result}">
		
			<c:if test="${not empty item.value}">
			<a href="#" class="parameterItem" title="used ${item.usedCount} times">${item.value}</a><br/>
			</c:if>

		</c:forEach>
	</div>


