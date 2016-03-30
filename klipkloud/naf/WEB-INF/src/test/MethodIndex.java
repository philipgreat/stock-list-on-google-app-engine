package test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodIndex {

	private List<Method> methodList;


	public List<Method> getList(Method[] methods) {
		if(this.methodList==null){
			this.methodList=new ArrayList<Method>();
		}
		for(Method method:methods){
			if(isIgnoreMethod(method)){
				continue;
			}
			this.methodList.add(method);
		}
				
		return methodList;
	}
	public List<Method> getList(Class clazz) {
		
		Method[] methods = clazz.getMethods();			
		return getList(methods);
	}
	final static String []IGNORE_METHOS={"equals","getClass","hashCode","notify","notifyAll","toString","wait",};
	protected static boolean isIgnoreMethod(Method method)
	{
		
		String name=method.getName();
		int index=Arrays.binarySearch(IGNORE_METHOS, name);
		if(index<0){
			return false;			
		}	
		return true;
		
	}
	public List<Method> getItems() {
		return methodList;
	}
	
}
