package com.huir.mina.customservice;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.huir.mina.service.customcodefactory.CustomCodeFactory;

public class CustomMinaService {
	public static final Logger LOG = Logger.getLogger(CustomMinaService.class);
	public static final int PORT = 7485;
	IoAcceptor acceptor = null;
	
	public boolean connection() {
		try {
			acceptor = new NioSocketAcceptor();
			acceptor.getFilterChain().addLast("code", new ProtocolCodecFilter(new CustomCodeFactory()));
			acceptor.getSessionConfig().setReadBufferSize(1024);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			acceptor.setHandler(new  CustomServiceHandler());
			acceptor.bind(new InetSocketAddress(PORT));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	public static void main(String[] args) {
		boolean bo = new CustomMinaService().connection();
		if(bo) {
			LOG.info("初始化成功......");
		}else {
			LOG.info("初始化失败......");
		}
	}
}
