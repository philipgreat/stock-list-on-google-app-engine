package com.terapico.naf;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terapico.naf.parameter.Parameter;
import com.terapico.naf.parameter.ParameterManager;
import com.terapico.naf.parameter.PersistantParameterManager;

import test.Action;
import test.Field;
import test.Form;
import test.MethodIndex;
public class InvokeHelper {
	
	ParameterManager manager;
	
	public  InvokeHelper() throws UnknownHostException
	{
		manager=new PersistantParameterManager();
		
	}
	
	protected  static MethodIndex getIndex(Class clazz){
		
		Method[] methods =getAllAccessiableMethods(clazz);		
		MethodIndex index=new MethodIndex();
		index.getList(methods);		
		return index;	
		
	}

	
	public static Method[] getAllAccessiableMethods(Class clazz)
	{
		
		MethodIndex index=new MethodIndex();	
		int length=index.getList(clazz).size();
		return index.getList(clazz).toArray((Method[])Array.newInstance(Method.class,length));
		
	}
	public static Method findMethod(Object object,String name){
		
		Class clazz = object.getClass();
		Method[] methods = getAllAccessiableMethods(clazz);		
		for (Method method : methods) {
			if (!method.getName().equalsIgnoreCase(name)) {
				continue;
			}
			return method;
		}
		return null;
	}
	protected BaseInvokeResult getResult(Object test, HttpServletRequest request, HttpServletResponse response) {

		
		try {
			FriendlyURI furi = FriendlyURI.parse(request.getRequestURI(), 2);			
			if (furi.getServiceName().equals("index")) {
				return InvokeResult.createInstance(getIndex(test.getClass()),null,null);
			}
			if (furi.getServiceName().equals("suggestParameter")) {
				return BaseInvokeResult.createInstance(this.getSuggestedParameter(furi.getParameter()),"Parameter$List");
			}
			if (furi.getServiceName().equals("allParameters")) {
				return BaseInvokeResult.createInstance(this.getAllParameters(),"Type_ParameterList$Map");
			}
			Method method=findMethod(test,furi.getServiceName());
			if(method==null){				
				return InvokeResult.createInstance(
						new Exception(test.getClass().getName()+"."+furi.getServiceName()+"(): 该方法没有实现!"),null,null);
			}
			Type[] methodParameterTypes=method.getGenericParameterTypes();
			if(method.getGenericParameterTypes().length>furi.getParameterLength()){				
				
				Form form=new Form();
				
				List<String> nameList=ExpressionBeanTool.getParameterNames(method);
				
				for(int i=0;i<methodParameterTypes.length;i++){
					Field field=new Field();
					String name=nameList.get(i);
					field.setLabel(name);
					field.setName(name);
					field.setType(methodParameterTypes[i]);
					form.addField(field);
				}		
			
				form.addAction(new Action(method.getName()));
				return InvokeResult.createInstance(form,null,null);
				
			}
			Object []parameters=ExpressionBeanTool.getParameters(method.getGenericParameterTypes(), furi.getParameter());
			manager.saveParameters(methodParameterTypes,parameters);
			Object actualResult=method.invoke(test, parameters);
			return  InvokeResult.createInstance(actualResult,method,parameters);

		} catch (InvocationTargetException exception) {
			return  InvokeResult.createInstance(exception.getCause(),null,null);
		}catch (Throwable throwable) {
			return  InvokeResult.createInstance(throwable,null,null);
		}

	
	}

	private List<Parameter> getSuggestedParameter(String[] parameter) throws Exception {
		// TODO Auto-generated method stub
		Type type=ExpressionBeanTool.getTypeFromString(parameter[0]);		
		List<Parameter> parameterGroup= manager.getParametersByType(type);
		if(parameterGroup==null){			
			return new ArrayList<Parameter> ();			
		}
		return parameterGroup;
	}
	
	private Map<Type, List<Parameter>> getAllParameters() throws Exception{
		// TODO Auto-generated method stub
		
		return manager.getParameters();
		
	}
	
	
	
	
	
	
}
