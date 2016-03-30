package com.terapico.naf.parameter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ParameterManager {
	Map<Type, List<Parameter>> parameters;

	public Map<Type, List<Parameter>> getParameters() throws Exception {
		if(parameters==null){			
			parameters=new HashMap<Type, List<Parameter>> ();
		}
		return parameters;
	}

	public void setParameters(Map<Type, List<Parameter>> parameters) {
		this.parameters = parameters;
	}

	public List<Parameter> getParametersByType(Type clazz) throws Exception {
		if(parameters==null){
			return new ArrayList<Parameter>();
		}
		return parameters.get(clazz);
	}

	public void saveParameters(Object[] inputParameters)throws Exception {
		
		if(parameters==null){			
			parameters=new HashMap<Type, List<Parameter>> ();
		}
		
		for (Object object : inputParameters) {
			if (object == null) {
				continue;
			}
			
			List<Parameter> parameterGroup = parameters.get(object.getClass());
			Parameter parameter = Parameter.newInstance(object);
			if (parameterGroup == null) {
				parameterGroup = new ArrayList<Parameter>();
				parameterGroup.add(parameter);
				parameters.put(object.getClass(), parameterGroup);
				continue;
			}

			if (parameterGroup.indexOf(parameter)>=0) {
				//add count;
				int index=parameterGroup.indexOf(parameter);
				Parameter existingParameter=parameterGroup.get(index);
				existingParameter.increaseUsedCount();
				continue;
			}
			parameterGroup.add(parameter);			
		}
	}
	

	
	public void saveParameters(Type[] types,Object[] inputParameters) throws Exception{
		
		if(parameters==null){			
			parameters=new HashMap<Type, List<Parameter>> ();
		}
		
		
		for(int i=0;i<types.length;i++){
			
			
			Object object=inputParameters[i];
			if (object == null) {
				continue;
			}
			if("".equals(object.toString())){
				continue;
			}
			
			Type type=types[i];
			List<Parameter> parameterGroup = parameters.get(type);
			Parameter parameter = Parameter.newInstance(object);
			if (parameterGroup == null) {
				parameterGroup = new Stack<Parameter>();
				parameterGroup.add(parameter);
				parameters.put(type, parameterGroup);
				continue;
			}

			if (parameterGroup.indexOf(parameter)>=0) {
				//add count;
				int index=parameterGroup.indexOf(parameter);
				Parameter existingParameter=parameterGroup.get(index);
				existingParameter.increaseUsedCount();
				continue;
			}
			parameterGroup.add(parameter);			
			
		}
		

	}
	

}
