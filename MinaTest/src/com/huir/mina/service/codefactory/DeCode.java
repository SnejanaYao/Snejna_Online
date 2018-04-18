package com.huir.mina.service.codefactory;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class DeCode implements ProtocolDecoder {
	public static final Logger LOG = Logger.getLogger(DeCode.class);
	private Charset charset =Charset.forName("UTF-8");
	IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		  while (in.hasRemaining()) {
	            byte b = in.get();
	            buf.put(b);
	            if (b == '\n') {
	                buf.flip();
	                byte[] msg = new byte[buf.limit()];
	                buf.get(msg);
	                String message =new String(msg, charset);
	                //解码成功，把buf重置
	                buf = IoBuffer.allocate(100).setAutoExpand(true);
	                out.write(message);
	               break;
	            }

	        }
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
