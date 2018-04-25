package com.huir.mina.service.customcodefactory;


import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


import org.apache.mina.filter.codec.CumulativeProtocolDecoder;

public class CustomDeCode extends CumulativeProtocolDecoder {
	public static final Logger LOG = Logger.getLogger(CustomDeCode.class);
	public static final Charset charset = Charset.forName("UTF-8");
	public static final CharsetDecoder cd = charset.newDecoder();
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if(in.remaining() < 4) {
			LOG.info("数据包异常！" );
			return false;
		}
		int type = in.getInt();
		int position = in.position();
		int limit = in.limit();
		int capacity = in.capacity();
		//LOG.info("信息  position  "+position +"  limit   "+limit + " capaticy     "+capacity);
		in.mark();
		
		if(type >10) {
			int length = in.getInt();
			//LOG.info("消息体长度(length)为  ------>  " + length);
			if(in.remaining() < length){
				in.reset();
				return false;
			}
			int rea = in.remaining();
			//LOG.info("limit - postion 的运算结果(bodylength)为  ------>  " + (limit - in.position()) + "读取了消息体长度字段后,buffer中剩余的长度(remaining) "+ rea);
			String msgbody = in.getString(cd);
			//LOG.info("msg  ------>  " + msgbody);
			String minaMsg = type+";"+msgbody+";"+length;
			out.write(minaMsg);
			if(in.remaining() >0) {
				LOG.info("数据包过长..........");
				return true;
			}
		}else {
			LOG.info("数据包错误  " );
			return false;
		}
		return false;
	}
}
