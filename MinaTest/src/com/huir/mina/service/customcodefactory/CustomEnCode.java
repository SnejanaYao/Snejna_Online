package com.huir.mina.service.customcodefactory;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CustomEnCode extends ProtocolEncoderAdapter {
	public static final Charset charset=Charset.forName("utf-8");

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		/*IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        CharsetEncoder ce = charset.newEncoder();
        String str = message.toString();
        session.setAttribute("HEART",str);
        Logger.getLogger(CustomEnCode.class).info(str + "  ---------------> " +session.getAttribute("HEART"));
		String[] body = str.split(";");
		String type =  body[0];
		String msgBody = body[1];
		String length = body[2];
		int msgType = Integer.parseInt(type);
		int msgLength = Integer.parseInt(length);
	    buf.putInt(msgType);
       	buf.putInt(msgLength);
        buf.putString(msgBody,ce);
        buf.flip();*/
       /* out.write(message);*/
	}
	
	

}
