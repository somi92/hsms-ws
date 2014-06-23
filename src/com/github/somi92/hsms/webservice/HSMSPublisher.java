package com.github.somi92.hsms.webservice;

import javax.xml.ws.Endpoint;

public class HSMSPublisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:8080/ws/hsms",new HSMSWebService());  
	}

}
