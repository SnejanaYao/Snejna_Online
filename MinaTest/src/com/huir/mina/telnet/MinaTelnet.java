package com.huir.mina.telnet;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.huir.mina.service.codefactory.CodeFactory;

public class MinaTelnet {
	public static final Logger LOG = Logger.getLogger(MinaTelnet.class);
	public static final int PORT = 7485;
	public static final String ADRESS = "127.0.0.1";
	IoConnector connector = null;
	public MinaTelnet() {
		connector= new NioSocketConnector();
		connector.setConnectTimeout(30000);
		connector.getFilterChain().addLast("code", new  ProtocolCodecFilter(new CodeFactory()));
		connector.setHandler(new TelnetHandler());
		ConnectFuture future = connector.connect(new InetSocketAddress(ADRESS, PORT));
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
		String sendPhone = "13681803609"; // 当前发送人的手机号码
        String receivePhone = "13721427169"; // 接收人手机号码
        String message = "测试发送短信，这个是短信信息哦，当然长度是有限制的哦....";
        String msg = sendPhone  + ";" +receivePhone +";" + message;
		session.write(msg);
		session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
	    connector.dispose();
	}
	
	public static void main(String[] args) {
		new MinaTelnet();
	}
}
