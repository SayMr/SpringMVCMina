package com.laolaoke.minademo.minaserver;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class MyKeepAliveMessageFactory implements  KeepAliveMessageFactory{
	
	private final Logger LOG = Logger.getLogger(MyKeepAliveMessageFactory.class);
	
    /** ���������� */  
    private static final String HEARTBEATREQUEST = "ping";  
    private static final String HEARTBEATRESPONSE = "pong"; 

	public Object getRequest(IoSession session) {
         return HEARTBEATREQUEST;
	}

	public Object getResponse(IoSession session, Object request) {
        return HEARTBEATRESPONSE;
	}

	public boolean isRequest(IoSession session, Object message) {
         if (message.equals(HEARTBEATREQUEST)){
        	 LOG.warn("������������Ϣ: " + message); 
             return true; 
         }
         return false;
	}

	public boolean isResponse(IoSession session, Object message) {
      if(message.equals(HEARTBEATRESPONSE)) {
    	  LOG.warn("��Ӧ��������Ϣ: " + message);  
    	  return true;
      }  
		return false;
	}

}
