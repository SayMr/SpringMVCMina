package com.laolaoke.minademo.minaclient;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.laolaoke.minademo.minaserver.MessageCodecFactory;

/**
 * 基于mina框架的客户端
 * @author zhuyintao
 * @date 2017年7月26日下午4:27:38
 */
public class ClientTest {
	public static void main(String[] args) throws InterruptedException {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new MessageCodecFactory()));
		connector.setHandler(new ClientHandler());
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 8888));
		cf.awaitUninterruptibly();
		cf.getSession().write("hello,mina");
	}
}
