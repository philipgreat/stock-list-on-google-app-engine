<%@ page isELIgnored="false" %>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	 <div>
       <table border="1">
	   <tr>
	   <th>涨停(<a href="http://zhidao.baidu.com/question/38235054.html" target="_blank">?</a>)：${marketlimit.limitedUp}</th>
	   <th>跌停(<a href="http://zhidao.baidu.com/question/38235054.html" target="_blank">?</a>)：${marketlimit.limitedDown}</th>
	   </tr>
	   
	   <tr>
	   <td>
	   
	   <c:forEach var="record" items="${marketlimit.limitedUpList}">
            ${record}<br/>
        </c:forEach> 
	   </td><td>
	   
	   
	   <c:forEach var="record" items="${marketlimit.limitedDownList}">
            ${record}<br/>
        </c:forEach>

	   </td>
	   </tr>	   
	   </table>
	</div>
	<div>用手机上的二维码软件照一下<br>
	<img src="images/marketurl.png" alt="涨停二维码"/> 
	</div>
	<div>本页面数据来源于<a href="http://quotes.money.163.com/#HS" target="_blank">网易沪深行情中心</a></div>

