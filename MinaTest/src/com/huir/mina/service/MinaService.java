package com.huir.mina.service;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.huir.mina.service.codefactory.CodeFactory;

public class MinaService {
	public static final Logger LOG = Logger.getLogger(MinaService.class);
	public static final String ADRESS = "127.0.0.1";
	public static final int PORT = 7485;
	IoAcceptor acceptor = null;
	public MinaService() {
		try {
			acceptor = new NioSocketAcceptor();
			acceptor.getFilterChain().addLast("code", new ProtocolCodecFilter(new CodeFactory()));
			acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			acceptor.getSessionConfig().setReadBufferSize(1024);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			acceptor.setHandler(new  ServiceHandler());
			acceptor.bind(new InetSocketAddress(ADRESS, PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new MinaService();
	}
}
