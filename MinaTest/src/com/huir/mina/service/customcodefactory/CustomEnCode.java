package com.huir.mina.service.customcodefactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.huir.entity.MinaMsg;

public class CustomEnCode extends ProtocolEncoderAdapter {
	public static final Charset charset=Charset.forName("utf-8");

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        CharsetEncoder ce = charset.newEncoder();
        MinaMsg msg =  (MinaMsg)message;
       	IoBuffer io = buf.putInt(msg.getMsgType());
       	Logger.getLogger(CustomCodeFactory.class).info("     ------>  " + io.toString());
       	buf.putInt(msg.getLength());
       	Logger.getLogger(CustomCodeFactory.class).info("     ------>  " + msg.getMsgbody().length());
        buf.putString(msg.getMsgbody(),ce);
        buf.flip();
        out.write(buf);
	}
	
	

}
