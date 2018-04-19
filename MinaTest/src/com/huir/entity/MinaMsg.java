package com.huir.entity;

public class MinaMsg {
	private String msgbody;
	private int msgType;
	private int length;
	public MinaMsg(int msgType,String msgbody,int length) {
		this.msgbody = msgbody;
		this.msgType = msgType;
		this.length = length;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public MinaMsg() {
	}
	
	public String getMsgbody() {
		return msgbody;
	}
	public void setMsgbody(String msgbody) {
		this.msgbody = msgbody;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	
}
