package com.terapico.naf;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.Action;
import test.Field;
import test.Form;
import test.MethodIndex;
import test.MathmaticalTool;
import test.ServiceBeanTest;

import com.sun.org.apache.xml.internal.serialize.Printer;
import com.terapico.naf.parameter.ParameterManager;

public class InvokeServlet extends HttpServlet {
	
	
	
	
	InvokeHelper helper;
	@Override
	public void init() throws ServletException {
		try {
			helper=new InvokeHelper();
		} catch (UnknownHostException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Logger log = Logger.getLogger(InvokeServlet.class.getName());
	/*
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object test = new ServiceBeanTest();
		BaseInvokeResult result =helper.getResult(test,request, response);
		response.setCharacterEncoding("GBK");		
		response.setContentType("text/html; encoding=GBK");
		response.addHeader("Cache-Control", "no-cache, must-revalidate");
		request.setAttribute("result", result.getActualResult());
		this.dispatchView(request, response,result);
	}

	protected void dispatchView(HttpServletRequest request, HttpServletResponse response,BaseInvokeResult result) throws ServletException, IOException {
		RequestDispatcher view = getRenderView(request, result);
		view. include(request, response);

	}
	protected void logInfo(String message)
	{
		log.log(Level.INFO, message);
		
	}
	protected RequestDispatcher getRenderView(HttpServletRequest request, BaseInvokeResult result) throws MalformedURLException
	{
		if(!result.isGenericResult()){
			return getSimpleRenderView(request,result.getActualResult());
		}
		
		return request.getRequestDispatcher("/"+result.getRenderKey()+".jsp");
		
	}
	protected RequestDispatcher getSimpleRenderView(HttpServletRequest request, Object object) throws MalformedURLException {

		Class temp = object.getClass();
		while (temp != null) {
			String jsp = "/" + temp.getName() + ".jsp";
			logInfo("trying to find: "+jsp);
			URL url = getServletContext().getResource(jsp);
			if (url != null) {
				return request.getRequestDispatcher(jsp);
			}
			temp = temp.getSuperclass();
		}
		return request.getRequestDispatcher("/java.lang.Object.jsp");// should
																		// not
																		// go
																		// here

	}
	


}
