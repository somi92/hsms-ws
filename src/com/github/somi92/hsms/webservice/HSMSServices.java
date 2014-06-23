package com.github.somi92.hsms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface HSMSServices {

	@WebMethod public String helloWorld(String name);
}
