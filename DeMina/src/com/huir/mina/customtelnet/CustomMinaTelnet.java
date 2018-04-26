package com.huir.mina.customtelnet;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.huir.entity.ConnectAPI;
import com.huir.mina.listener.TelnetListener;
import com.huir.mina.service.customcodefactory.CustomCodeFactory;



public class CustomMinaTelnet {
	public static final Logger LOG = Logger.getLogger(CustomMinaTelnet.class);
	public static final int PORT = 7485;
	public static final String ADRESS = "127.0.0.1";
	public static final int SENDHEART=20 ; //五秒发送一次心跳包
	private IoConnector connector = null;
	private IoSession session = null;
	private static CustomMinaTelnet telnet;
	
	public static final CustomMinaTelnet getInstance() {
		if(null == telnet) {
			telnet = new CustomMinaTelnet();
		}
		return telnet;
	}
	
	public IoConnector init() {
		KeepAliveMessageFactory factory = new HreatBeat();
        KeepAliveFilter heartBeat = new KeepAliveFilter(factory,
                IdleStatus.BOTH_IDLE);
        /** 是否回发 */
        heartBeat.setForwardEvent(true);
        /** 发送频率 */
        heartBeat.setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler.LOG);
        heartBeat.setRequestInterval(SENDHEART);
		connector= new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.setHandler(new CustomTelnetHandler());
		connector.setDefaultRemoteAddress(new InetSocketAddress(ADRESS, PORT));
		connector.getFilterChain().addLast("code", new  ProtocolCodecFilter(new CustomCodeFactory()));
		connector.getFilterChain().addLast("heartbeat", heartBeat);
		// 添加重连监听  
		connector.addListener(new TelnetListener() {  
				    @Override  
				    public void sessionDestroyed(IoSession arg0) throws Exception {  
		    			for (;;) {  
				            try {  
				                Thread.sleep(3000);
				                System.out.println("开始重连服务器");  
				                ConnectFuture future = connector.connect(new InetSocketAddress(ADRESS, PORT));
				                InetSocketAddress adress = (InetSocketAddress) connector.getDefaultRemoteAddress();
				                future.awaitUninterruptibly();// 等待连接创建成功  
		                        session = future.getSession();
				                if (session.isConnected()) {  
				                	LOG.info("重连成功!!   ip[" +adress.getHostName() +"]   host[" + adress.getPort()+"]");
				                    break;  
				                }  
				            } catch (Exception ex) {  
				            	LOG.info("重连服务器登录失败,3秒再连接一次");  
				            }  
				        } 
				    }  
				});
		return connector;
	};
	
	public void connection() {
		while (true) {
			try {
				IoConnector connector = init();
				ConnectFuture future = connector.connect(); //异步连接
				future.awaitUninterruptibly();
				IoSession session = future.getSession();
				if(session.isConnected()) {
					LOG.info("客户端连接成功.....");
					String sendMsg = "{\"Request\":\"Islogin\",\"Root\":\"{\\\"User_ID\\\":1,\\\"User_Name\\\":null,\\\"CUser_HuaName\\\":null,\\\"CUser_PWD\\\":null,\\\"Token\\\":null,\\\"LoginType\\\":0,\\\"StrErr\\\":\\\"29ac2566e3078e87e3097d3822e50d7\\\"}\",\"Parameter\":null,\"Token\":\"a173850c55004309929a6a643927dd2d\",\"Querycount\":0,\"Number\":null,\"Type\":null}";
					int length = sendMsg.length();
					String msg = ConnectAPI.SENDMSG_REQ+";"+sendMsg+";"+length;
					session.write(msg);
				    break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public CustomMinaTelnet() {
		connection();
	}
	
	
	/*public boolean connection() {
		try {
			IoConnector connector = init();
			ConnectFuture future = connector.connect(); //异步连接
			
			future.awaitUninterruptibly();
			IoSession session = future.getSession();
			
			String sendMsg = "Telnet:发送请求连接消息";
			int length = sendMsg.length();
			String msg = ConnectAPI.SENDMSG_REQ+";"+sendMsg+";"+length;
			session.write(msg);
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		    connector.dispose();
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}*/

	
	public static void main(String[] args) {
		/*boolean flag = new CustomMinaTelnet().connection();
		if(flag) {
			LOG.info("发送创建连接......");
		}else {
			LOG.info("发送创建连接失败......");
		}*/
		getInstance();
	}
}
