package com.wechat.message.req;

/**
 * 语音消息
 * @author chaosheng.zhang
 *
 */
public class VoiceMessage extends BaseMessage {

	public VoiceMessage() {
		// TODO Auto-generated constructor stub
	}
	
	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}
