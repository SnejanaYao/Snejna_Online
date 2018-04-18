package com.huir.mina.service.codefactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class EnCode implements ProtocolEncoder {
	public static final Charset charset=Charset.forName("utf-8");
	
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        CharsetEncoder ce = charset.newEncoder();
        buf.putString(message.toString(),ce);
        buf.put((byte) '\r');
        buf.put((byte) '\n');
        buf.flip();
        out.write(buf);
	}

	
	@Override
	public void dispose(IoSession arg0) throws Exception {
		
	}

}
