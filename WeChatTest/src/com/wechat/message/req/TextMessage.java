package com.wechat.message.req;

/**
 * 文本消息
 * @author chaosheng.zhang
 *
 */
public class TextMessage extends BaseMessage {

	public TextMessage() {
		// TODO Auto-generated constructor stub
	}
	
	// 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  

}
