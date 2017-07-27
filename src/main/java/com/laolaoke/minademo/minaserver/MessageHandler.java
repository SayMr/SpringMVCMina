package com.laolaoke.minademo.minaserver;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSONObject;

public class MessageHandler extends IoHandlerAdapter {

	private final int IDLE = 2;//单位秒

	private final Logger LOG = Logger.getLogger(MessageHandler.class);

	public static Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());

	public static ConcurrentHashMap<Long, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<Long, IoSession>();

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.closeOnFlush();
		LOG.warn("session occured exception, so close it." + cause.getMessage());
		LOG.warn(cause.toString());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
		LOG.warn("客户端" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "连接成功！");
		session.setAttribute("type", message);
		String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
		session.setAttribute("ip", remoteAddress);
		LOG.warn("服务器收到的消息是：" + str);
		if(message.toString().startsWith("{\"companyId\"")) {
			String companyId = JSONObject.parseObject(message.toString()).getString("companyId");
			session.setAttribute("companyId", companyId);
		} else {
			session.write("welcome by he");			
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LOG.warn("messageSent:" + message);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOG.warn("remote client [" + session.getRemoteAddress().toString() + "] connected.");
		sessions.add(session);
		Long time = System.currentTimeMillis();
		session.setAttribute("id", time);
		sessionsConcurrentHashMap.put(time, session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.warn("sessionClosed.");
		session.closeOnFlush();
		sessions.remove(session);
		sessionsConcurrentHashMap.remove(session.getAttribute("id"));
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		LOG.warn("session idle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOG.warn("sessionOpened.");
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLE);
	}

}
