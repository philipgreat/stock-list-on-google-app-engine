package com.terapico.naf.parameter;

import java.lang.reflect.Type;

public class PersistantParameter extends Parameter {
	private String type;
	private String persistantId;
	public String getPersistantId() {
		return persistantId;
	}
	public void setPersistantId(String id) {
		persistantId = id;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public static PersistantParameter newInstance(Type type,Object inputParameter) {
		PersistantParameter parameter = new PersistantParameter();
		parameter.setLastUsedTime(new java.util.Date());
		parameter.setValue(inputParameter);
		parameter.setUsedCount(1);
		Class clazz=(Class)type;
		parameter.setType(clazz.getName());		
		return parameter;
	}
	
}
