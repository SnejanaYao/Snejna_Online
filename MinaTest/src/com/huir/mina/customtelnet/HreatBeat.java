package com.huir.mina.customtelnet;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class HreatBeat implements KeepAliveMessageFactory {
	public static final Logger LOG = Logger.getLogger(HreatBeat.class);
	public static final String HEARTREQUEST ="0x11";
	public static final String HEARTRESPONES = "0X22";
	@Override
	public Object getRequest(IoSession session) {
		LOG.info("请求预设信息");
		return HEARTREQUEST;
	}

	@Override
	public Object getResponse(IoSession session, Object message) {
		LOG.info("响应预设信息");
		return HEARTRESPONES;
	}

	@Override
	public boolean isRequest(IoSession session, Object message) {
		String request = message.toString();
		LOG.info("请求到的信息 ------>" +message.toString());
		if(message.equals(HEARTREQUEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isResponse(IoSession session, Object message) {
		String respones = message.toString();
		LOG.info("响应到的消息 ------>" +message.toString());
		if(message.equals(HEARTRESPONES)) {
			return true;
		}
		return false;
	}

}
