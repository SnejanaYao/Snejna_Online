package com.huir.mina.customtelnet;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.huir.entity.MinaMsg;

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
		MinaMsg msg = (MinaMsg)message;
		LOG.info("messageReceived 客户端接收到的数据 ----->"+msg.getMsgbody());
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.info("服务器断开连接");
	/*	while(true) {
			Thread.sleep(3000);   
			IoConnector connector = new CustomMinaTelnet().init();
            ConnectFuture future = connector.connect(new InetSocketAddress(ADRESS, PORT));
            InetSocketAddress adress = (InetSocketAddress) connector.getDefaultRemoteAddress();
            future.awaitUninterruptibly();// 等待连接创建成功    
            session = future.getSession();// 获取会话    
            if(session.isConnected()){    
				LOG.info("重新连接成功..... ip: " + adress.getHostName() +"    "+adress.getPort() );
				break;
			}else {
				LOG.info("网络有点小异常.......");
			}
		}*/
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
