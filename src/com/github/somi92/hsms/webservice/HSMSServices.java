package com.github.somi92.hsms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface HSMSServices {

	@WebMethod public String helloWorld(String name);
	@WebMethod public HSMS[] listAllActions();
	@WebMethod public HSMS[] listActionsByPriority(int priority);
}
