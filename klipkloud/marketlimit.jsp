<%@ page isELIgnored="false" %>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	 <div>
       <table border="1">
	   <tr>
	   <th>��ͣ(<a href="http://zhidao.baidu.com/question/38235054.html" target="_blank">?</a>)��${marketlimit.limitedUp}</th>
	   <th>��ͣ(<a href="http://zhidao.baidu.com/question/38235054.html" target="_blank">?</a>)��${marketlimit.limitedDown}</th>
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
	<div>���ֻ��ϵĶ�ά�������һ��<br>
	<img src="images/marketurl.png" alt="��ͣ��ά��"/> 
	</div>
	<div>��ҳ��������Դ��<a href="http://quotes.money.163.com/#HS" target="_blank">���׻�����������</a></div>

