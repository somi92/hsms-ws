package com.github.somi92.hsms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface="com.github.somi92.hsms.webservice.HSMSServices")
public class HSMSWebService implements HSMSServices {

	@Override
	@WebMethod
	public String helloWorld(String name) {
		// TODO Auto-generated method stub
		return "Hello "+name+"!";
	}

	
}
