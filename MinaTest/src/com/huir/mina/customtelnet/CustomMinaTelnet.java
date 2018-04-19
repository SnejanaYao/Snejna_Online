package com.huir.mina.customtelnet;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.huir.entity.ConnectAPI;
import com.huir.entity.MinaMsg;
import com.huir.mina.service.customcodefactory.CustomCodeFactory;

public class CustomMinaTelnet {
	public static final Logger LOG = Logger.getLogger(CustomMinaTelnet.class);
	public static final int PORT = 7485;
	public static final String ADRESS = "127.0.0.1";
	public static final int SENDHEART=5 ; //五秒发送一次心跳包
	IoConnector connector = null;
	
	public IoConnector init() {
		KeepAliveMessageFactory factory = new HreatBeat();
        KeepAliveFilter heartBeat = new KeepAliveFilter(factory,
                IdleStatus.BOTH_IDLE);
        /** 是否回发 */
        heartBeat.setForwardEvent(true);
        /** 发送频率 */
        heartBeat.setRequestInterval(15);
        heartBeat.setForwardEvent(true);//设置是否forward到下一个filter    
		connector= new NioSocketConnector();
		connector.setConnectTimeout(30000);
		connector.setHandler(new CustomTelnetHandler());
		connector.setDefaultRemoteAddress(new InetSocketAddress(ADRESS, PORT));
		connector.getFilterChain().addLast("code", new  ProtocolCodecFilter(new CustomCodeFactory()));
		connector.getFilterChain().addLast("heartbeat", heartBeat);
		return connector;
	};
	
	public boolean connection() {
		try {
			IoConnector connector = init();
			ConnectFuture future = connector.connect(); //异步连接
			
			future.awaitUninterruptibly();
			IoSession session = future.getSession();
			
			String sendMsg = "Telnet:发送请求连接消息";
			int length = sendMsg.length();
			String msg = ConnectAPI.SENDMSG_REP+";"+sendMsg+";"+length;
			session.write("");
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		    connector.dispose();
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public static void main(String[] args) {
		boolean flag = new CustomMinaTelnet().connection();
		if(flag) {
			LOG.info("发送创建连接......");
		}else {
			LOG.info("发送创建连接失败......");
		}
	}
}
