package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceBeanTest {
	
	
	public Date getCurrentTime() throws Exception{				
		return new CurrentTime();
	}
	
	public Date getException() throws Exception{	
		if(true){
			throw new Exception("exception!");			
		}
		return new CurrentTime();
	}
	public PlainText getHeader(URI uri) throws Exception{				
		PlainText text=new PlainText();
		
		URLConnection conn = uri.toURL(). openConnection();
	 
		//get all headers
		Map<String, List<String>> map = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			if(entry.getKey() ==null){
				continue;
			}
			text.append( entry.getKey() +": "+ entry.getValue().get(0));
			text.append("\r\n");
		}
		
		return text;
		
	}
	
	protected String getValueInExpr(String contentType, String prefix)
	{		
		Pattern pattern = Pattern.compile(prefix+"=\\S+(;)?");
		Matcher matcher = pattern.matcher(contentType);
		if(!matcher.find()){
			return null;
		}
		String temp=matcher.group();
		temp=temp.replace(prefix+"=", "");
		temp=temp.replace(";", "");
		return temp;
	}
	protected String getEncoding(String contentType)
	{		
		String encoding=getValueInExpr(contentType,"charset");
		if(encoding!=null){
			return encoding;
		}
		encoding=getValueInExpr(contentType,"encoding");
		if(encoding!=null){
			return encoding;
		}
		return "UTF-8";		
	}
	public int length(String contentType)
	{		
		
		return contentType.length();		
	}
	
	public PlainText getContent(URI uri) throws Exception{				
		PlainText text=new PlainText();
		
		URLConnection conn = uri.toURL().openConnection();
		
		String encoding=null;
		
		//get all headers
		Map<String, List<String>> map = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			if(entry.getKey() ==null){
				continue;
			}
			text.append( entry.getKey() +": "+ entry.getValue().get(0));
			text.append("\r\n");
			if("Content-Type".equalsIgnoreCase(entry.getKey())){
				encoding=getEncoding(entry.getValue().get(0));	
				System.out.println(encoding);
			}
			
			
		}
		BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),encoding));
		String line;
	    while ((line = reader.readLine()) != null) {
	    	text.append(line);
	    	text.append("\r\n");
	    }		
		
		return text;
		
	}
	
	public CalculateResult fetchValue(URI uri, String name, int number) throws Exception{		
		CalculateResult result=new CalculateResult();
		result.setValue(number);
		return result;
	}
	public CalculateResult searchPerson(String name, int age) throws Exception{		
		CalculateResult result=new CalculateResult();
		result.setValue(age);
		return result;
	}
	
	public List<String> testListOfString() throws Exception{		
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		return list ;
	}
	public Map<String,String> testMapOfString() throws Exception{		
		Map<String,String> map=new HashMap<String,String>();
		map.put("k1","v1");
		map.put("k2","v2");
		
		return map ;
	}

	public List<String> testListOfStringArgOne(String name) throws Exception{		
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		return list ;
	}
	
	
	/*
	 * public MessageBox getNomalResult(){
		MessageBox box=new MessageBox();
		box.setMessage("�ɹ���ɣ�");
		return box;		
	}
	public MessageBox getExceptionResult() throws Exception{		
		throw new Exception("ʧ���ˣ�");		
	}
	public MessageBox getCustomExceptionResult() throws Exception{		
		throw new CustomException("ʧ���ˣ�");		
	}
	
	public Form getFormResult() throws Exception{		
		
		Form form=new Form();
		form.addField(new Field("User Name","philip"));
		form.addField(new Field("Password",""));
		form.addAction(new Action("Login"));
		form.addAction(new Action("Forgot Password"));
		return form;
	}
	 * 
	 * 
	 * */
}
