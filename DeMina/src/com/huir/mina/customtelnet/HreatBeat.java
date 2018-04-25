package com.huir.mina.customtelnet;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.huir.entity.ConnectAPI;

public class HreatBeat implements KeepAliveMessageFactory {
	public static final Logger LOG = Logger.getLogger(HreatBeat.class);
	
	/**
	 * 当读写 停止 到达一定时间时  为了保证存活  在这里发送 心跳包消息(客户端发送)
	 */
	@Override
	public Object getRequest(IoSession session) {
		String msg = "客户端发送心跳包";
		String send = ConnectAPI.HEARTBEAT_REQ+";"+msg+";"+msg.length();
		LOG.info("为了生存 ----客户端主动发送心跳包");
		return send;
	}

	/**
	 * 返回服务端响应的心跳包消息(服务端响应)
	 */
	@Override
	public Object getResponse(IoSession session, Object message) {
		return session.getAttribute("HEART");
	}

	/**
	 * 客户端发送心跳包消息
	 */
	@Override
	public boolean isRequest(IoSession session, Object message) {
		String msg = message.toString();
	     if(msg.startsWith(ConnectAPI.HEARTBEAT_REQ+"")){
         LOG.info("客户端发送心跳包 ------》  " + message);
         return true;
     }
         return false;
	}

	/**
	 * 服务端返回的心跳包消息
	 */
	@Override
	public boolean isResponse(IoSession session, Object message) {
		String msg = message.toString();
		if(msg.startsWith(ConnectAPI.HEARTBEAT_REP+"")) {
			LOG.info("服务端返回心跳 --------》 : " + message);
			return true;
		}
		return false;
	}

}
