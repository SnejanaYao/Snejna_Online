package com.huir.mina.customtelnet;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.huir.entity.ConnectAPI;

public class HreatBeat implements KeepAliveMessageFactory {
	public static final Logger LOG = Logger.getLogger(HreatBeat.class);
	public static final String HEARTREQUEST ="0x11";
	public static final String HEARTRESPONES = "0X22";
	
	/**
	 * 返回给服务端的心跳包数据 return 返回结果才是服务端收到的心跳包数据
	 */
	@Override
	public Object getRequest(IoSession session) {
		String msg = "客户端发送心跳包";
		String send = ConnectAPI.HEARTBEAT_REQ+";"+msg+";"+msg.length();
		session.write(send);
		LOG.info("客户端主动发送心跳包");
		return send;
	}

	/**
	 * 接收到的服务端数据包
	 */
	@Override
	public Object getResponse(IoSession session, Object message) {
		return null;
	}

	/**
	 * 判断是否是服务端发来的数据包
	 */
	@Override
	public boolean isRequest(IoSession session, Object message) {
		String msg = message.toString();
	     if(msg.startsWith(ConnectAPI.SENDMSG_REP+"")){
         LOG.info("接收到服务端心数据包引发心跳事件                 心跳数据包是》》" + message);
         return true;
     }
         return false;
	}

	/**
	 * 判断发送信息是否是
	 */
	@Override
	public boolean isResponse(IoSession session, Object message) {
		String msg = message.toString();
		if(msg.startsWith(ConnectAPI.HEARTBEAT_REQ+"")) {
			LOG.info("客户端发送数据包中引发心跳事件: " + message);
			return true;
		}
		return false;
	}

}
