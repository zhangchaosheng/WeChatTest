package com.qunar.util;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;






import com.qunar.util.NLPIRUtil.NLPIRInterface.result_t;

public class WordSegmenter {

	// 缓冲区大小
	private final static int BUFFER_SIZE = 4096;
	// 缓冲字符数据
	private char[] buffer = new char[BUFFER_SIZE];// 初始化缓冲区;
	// 未处理文本
	public String paragraph;
	// 结果列表
	private result_t[] results;
	private LinkedList<result_t> resultList = new LinkedList<result_t>();
	
	public WordSegmenter() {
		// TODO Auto-generated constructor stub
	}

	public synchronized result_t next() {
		return resultList.pollFirst();
	}

	// 读取字符流中信息，并以字符串形式输出
	public String fillBuffer(Reader reader) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		int size = 0;
		try {
			while ((size = reader.read(buffer, 0, BUFFER_SIZE)) != -1) {
				String str = new String(buffer, 0, size);
				sb.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

//	public void reset() {
//		resultList.clear();
//	}
	
	public void reset(Reader reader) {
//		System.out.println("3. WordSegmenter.reset");
		resultList.clear();
		try {
			paragraph = fillBuffer(reader);
			results = NLPIRUtil.NLPIR_ParagraphProcessA(paragraph);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(results != null){
			for (result_t t : results) {
				resultList.add(t);
			}
		}
	}
	
	public void reset_self(Reader reader) {
		result_t rt = null;
		try {
			paragraph = fillBuffer(reader);
			String tagger_result = NLPIRUtil.NLPIR_ParagraphProcess(paragraph, 0);
			if(tagger_result == null){
				return;
			}else{
				String[] words = tagger_result.split("\\s+");
				int start = 0;
				for(String w : words){
					rt = new result_t();
					byte[] bt = w.getBytes();
					rt.start = start;
					rt.length = bt.length;
					resultList.add(rt);
					start += bt.length;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
