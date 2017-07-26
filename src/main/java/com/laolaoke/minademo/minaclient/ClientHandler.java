package com.laolaoke.minademo.minaclient;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter{
	private final Logger LOG = Logger.getLogger(ClientHandler.class);  
	 
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LOG.warn("客户端收到消息:" + message);
		if (message.toString().equals("ping")) {
			LOG.warn("收到心跳包");
			session.write("pong");
		}
		LOG.warn(session);
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
	}
}
