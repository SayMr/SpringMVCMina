package com.laolaoke.minademo.controller;

import java.util.Iterator;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laolaoke.minademo.minaserver.MessageHandler;

@Controller
@RequestMapping(value="helloMina")
public class HelloMinaController {

	@RequestMapping(value="/hello")
	public void helloMina() {
		
	}
	
	@RequestMapping(value="/push", method=RequestMethod.POST)
	public void pushMessage(String message) {
		for (Iterator<IoSession> iterator = MessageHandler.sessions.iterator(); iterator.hasNext();) {
			IoSession session = (IoSession) iterator.next();
			session.write(message);
		}
	}
}
