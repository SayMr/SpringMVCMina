package com.laolaoke.minademo.minaserver;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaProtocoHandler extends IoHandlerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void messageReceived(IoSession session, Object message) {
		String msg = (String) message;
		System.out.println("Server received: " + msg);
		session.write("Server Send: "+ msg);
	}
	
	public void sessionIdle(IoSession session, IdleStatus status) {
		session.closeNow();
	}
	
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.warn("Unexpected exception.", cause);
		session.closeNow();
	}
}
