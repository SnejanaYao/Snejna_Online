package com.huir.mina.service.customcodefactory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CustomCodeFactory implements ProtocolCodecFactory{
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return new CustomDeCode();
	}
	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return new CustomEnCode();
	}
}
