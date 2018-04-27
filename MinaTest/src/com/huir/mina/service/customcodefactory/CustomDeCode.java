package com.huir.mina.service.customcodefactory;


import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


import org.apache.mina.filter.codec.CumulativeProtocolDecoder;

public class CustomDeCode extends CumulativeProtocolDecoder {
	public static final Logger LOG = Logger.getLogger(CustomDeCode.class);
	public static final Charset charset = Charset.forName("UTF-8");
	public static final CharsetDecoder cd = charset.newDecoder();
	private String msg =null;
	private int limit; 
	private int len;
	private byte bo;
	private int str_length;
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		/*// byte数组 取值
		int position = in.position();
		int limit = in.limit();
		int capacity = in.capacity();
		byte[] temp = new byte[limit];
		in.get(temp);
		int positions = in.position();
		byte by = temp[0];
		int length = temp[1];
		byte[]  len_bts= Arrays.copyOfRange(temp, 2, 2+length);
		String str_len = new String(len_bts,"UTF-8");
		int len = Integer.parseInt(str_len);
		if(by+length+len>temp.length) {
			
		}
		byte[]  new_bts= Arrays.copyOfRange(temp, 2+length, temp.length);
		String msg = new String(new_bts,"UTF-8");
		LOG.info("信息  position  "+position +"  limit   "+limit + " capaticy     "+capacity   + "   lenth  " +len  +"   positions  "  + positions + "  remaning " +(in.remaining()));
		LOG.info("msg1  -------->   "  + msg);
		return false;*/
		
		
		
		limit = in.limit();
		int capacity = in.capacity();
		in.mark();
		if(in.remaining() < 0) {
			LOG.info("数据包异常！" );
			return false;
		}
		bo= in.get();
		str_length = in.get();
		byte[] temp = new  byte[in.remaining()];
		in.get(temp);
		byte[]  len_bts= Arrays.copyOfRange(temp, 0, str_length);
		String str_len = new String(len_bts,"UTF-8");
		len = Integer.parseInt(str_len);
		if(bo + str_length+len > limit|| bo <0) {
			in.reset();
			return false;
		}
		int position = in.position();
		byte[]  str_bts= Arrays.copyOfRange(temp, str_length,temp.length);
		msg = new String(str_bts, "UTF-8"); 
		LOG.info("信息  position  "+position +"  limit   "+limit + " capaticy     "+capacity + "    byte   " + bo  + "    length  " + str_length +"      msg    "  + msg + "  length  " + len );
		return false;
	}
}
