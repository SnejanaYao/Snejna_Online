package com.huir.mina.customtelnet;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.huir.entity.ConnectAPI;

public class CustomTelnetHandler extends IoHandlerAdapter {
	public static final Logger LOG = Logger.getLogger(CustomTelnetHandler.class);
	public static final int PORT = 7485;
	public static final String ADRESS = "127.0.0.1";
	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		/*
		 * 自定义消息接收
		 * String str = message.toString();
		String[] body = str.split(";");
		String type =  body[0];
		int msgType = Integer.parseInt(type);
		LOG.info(msgType);
		if(msgType == ConnectAPI.SENDMSG_REP) {
			String msgBody = body[1];
			String length = body[2];
			int msgLength = Integer.parseInt(length);
			LOG.info("messageReceived：   客户端端接收到的数据 -----> " +msgBody);
		}else if(msgType == ConnectAPI.HEARTBEAT_REP) {
			LOG.info("================响应心跳包===============" );//这里不输出猜测是 进入到了 heatBeat的seesion中所以没有进入handler
		}*/
		LOG.info(message.toString());
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.info("断开连接");
	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		LOG.info("-客户端与服务端连接[空闲] - " + status.toString());  
	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {

	}

}
