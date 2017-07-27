package com.laolaoke.minademo.minaserver;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class MyKeepAliveMessageFactory implements  KeepAliveMessageFactory{
	
	private final Logger LOG = Logger.getLogger(MyKeepAliveMessageFactory.class);
	
	/** 心跳包内容 */  
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
        	 LOG.warn("请求心跳包信息: " + message); 
             return true; 
         }
         return false;
	}

	public boolean isResponse(IoSession session, Object message) {
      if(message.equals(HEARTBEATRESPONSE)) {
    	  LOG.warn("响应心跳包信息: " + message);  
    	  return true;
      }  
		return false;
	}

}
