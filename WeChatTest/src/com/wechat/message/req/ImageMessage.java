package com.wechat.message.req;

/**
 * 图片消息
 * @author chaosheng.zhang
 *
 */
public class ImageMessage extends BaseMessage {

	public ImageMessage() {
		// TODO Auto-generated constructor stub
	}
	
    // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    } 

}
