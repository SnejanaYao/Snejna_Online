package com.huir.mina.customservice;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.huir.entity.ConnectAPI;
import com.huir.entity.MinaMsg;

public class CustomServiceHandler extends IoHandlerAdapter {
	public static final Logger LOG = Logger.getLogger(CustomServiceHandler.class);
	public static final int PORT = 7485;
	public static final String ADRESS = "127.0.0.1";
	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {

	}

	@Override
	public void messageReceived(IoSession session, Object messsage) throws Exception {
		/*
		 * 自定义消息的接收
		 * 
		 * String str = messsage.toString();
		String[] body = str.split(";");
		String type =  body[0];
		int msgType = Integer.parseInt(type);
		if(msgType == ConnectAPI.SENDMSG_REQ) {
			String msgBody = body[1];
			String length = body[2];
			int msgLength = Integer.parseInt(length);
			MinaMsg msg = new MinaMsg(msgType, msgBody, msgLength);
			LOG.info("messageReceived：   服务端接收到的数据 -----> " +msg.getMsgbody());
			String news = "Service:连接成功";
			String recevie = ConnectAPI.SENDMSG_REP+";"+news+";"+news.length();
			session.write(recevie);
		}else if(msgType == ConnectAPI.HEARTBEAT_REQ) {
			LOG.info("================接收到心跳包===============" );
			String send = "服务端接收到了心跳包";
			String msg = ConnectAPI.HEARTBEAT_REP+";"+send+";"+send.length();
			session.write(msg);
		}*/
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
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {

	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {

	}

}
