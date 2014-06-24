package com.github.somi92.hsms.webservice;

import javax.xml.ws.Endpoint;

public class HSMSPublisher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:9090/ws/hsms",new HSMSWebService());  
//		HSMSWebService ws = new HSMSWebService();
//		ws.listAllActions();
//		System.out.println();
//		ws.listActionsByPriority(3);
//		System.out.println(ws.test(1));
	}

}
