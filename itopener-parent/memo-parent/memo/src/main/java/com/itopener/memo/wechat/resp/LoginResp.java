package com.itopener.memo.wechat.resp;

public class LoginResp {

	/** 用户唯一标识*/
	private String openid;
	
	/** 会话密钥*/
	private String session_key;
	
	/** 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回*/
	private String unionid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
