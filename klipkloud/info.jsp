<%@ page import="java.nio.charset.*" %>
<%@ page import="java.util.*" %>
<%
	
	int index=0;
	response.setContentType("text/plain");
	Enumeration enumeration = request.getHeaderNames();
	while (enumeration.hasMoreElements()) {            
            String name = (String) enumeration.nextElement();           
            String value = request.getHeader(name);
            Enumeration values = request.getHeaders(name);
            while (values.hasMoreElements()) {
                value = (String) values.nextElement();
                out.println(name+":\t\t" + value );            
            }
    }
	
	out.println("--------------------------------env-------------------------------------------");
	Map<String, String> map = System.getenv();
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        String temp = "";
        while (iterator.hasNext()) {
            temp = (String) iterator.next();
            out.println( "\t" + temp.toLowerCase() + "="
                    + map.get(temp));
        }
	out.println("-----------------------------------properties----------------------------------------");
	Properties properties = System.getProperties();
        Set<Object> pSet = properties.keySet();
        Iterator<Object> pIterator = pSet.iterator();
        index = 1;
        while (pIterator.hasNext()) {
            temp = (String) pIterator.next();
            out.println( "\t" + temp + "=" + properties.getProperty(temp));
        }
	out.println("--------------------------------------charset-------------------------------------");	
	set = Charset.availableCharsets().keySet();
        iterator = set.iterator();
        index=1;
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            out.println( "\t" + string);
        }
	
%>
