package test;

public class CustomException extends Exception {

	public  CustomException(String message)
	{
		super(message);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "���ƻ���ʧ�ܣ�"+super.getMessage();
	}
	
}
