package com.wechat.message.resp;

import com.wechat.model.Music;

/**
 * 音乐消息
 * 
 * @author chaosheng.zhang
 * 
 */
public class MusicMessage extends BaseMessage {

	public MusicMessage() {
		// TODO Auto-generated constructor stub
	}

	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
