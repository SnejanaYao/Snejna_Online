package com.huir.mina.service;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServiceHandler implements IoHandler {
	public static final Logger LOG = Logger.getLogger(ServiceHandler.class);
	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {

	}

	@Override
	public void messageReceived(IoSession session, Object messsage) throws Exception {
		String msg = messsage.toString();
		String[] phoneMsg = msg.split(";");
		String sendPhone = phoneMsg[0];
		String receviePhone = phoneMsg[1];
		String msgBody = phoneMsg[2];
		
		LOG.info("服务端接收到的数据 -----> " + sendPhone+"   "+receviePhone +"  " +msgBody);
		if("out".equals(messsage)) {
			session.close();
		}
		session.write("服务端接收到 "+sendPhone +"发送的消息");
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
