package com.huir.android.entity;

public class Msg {
   private String msg;
   private int  type;
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
   
   public Msg() {
	// TODO Auto-generated constructor stub
}
   public Msg(String msg,int type) {
	   this.msg =msg;
	   this.type =type;
   }

}
