package com.terapico.naf.parameter;

import java.util.Date;

public class Parameter {
	public Parameter(){
		
	}
	private Date lastUsedTime;
	private Object value;
	private int  usedCount;
	public Date getLastUsedTime() {
		return lastUsedTime;
	}
	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}
	public void increaseUsedCount()
	{
		this.usedCount=this.usedCount+1;		
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this.getValue()==null){
			return false;
		}
		if(!(obj instanceof Parameter)){
			return false;
		}
		Parameter param=(Parameter)obj;
		// TODO Auto-generated method stub
		return this.getValue().equals(param.getValue());
	}
	public static Parameter newInstance(Object inputParameter) {
		Parameter parameter = new Parameter();
		parameter.setLastUsedTime(new java.util.Date());
		parameter.setValue(inputParameter);
		parameter.setUsedCount(1);
		return parameter;
	}
	
}
