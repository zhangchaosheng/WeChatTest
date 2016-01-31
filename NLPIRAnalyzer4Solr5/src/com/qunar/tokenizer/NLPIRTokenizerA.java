package com.qunar.tokenizer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.AttributeFactory;

import com.qunar.util.NLPIRUtil.NLPIRInterface.result_t;
import com.qunar.util.WordSegmenter;

public class NLPIRTokenizerA extends Tokenizer {
	// 词汇单元文本属性
	private final CharTermAttribute termAtt;
	// 词汇单元位移属性
	private final OffsetAttribute offsetAtt;
	// 词汇单元分类属性
	private final TypeAttribute typeAtt;
	// 记录最后一个词元的结束位置
	private int endPosition;
	// 记录词元的开始位置
	private int beginPosition;

	// NLPIR分词器的实现
	private WordSegmenter WordSegmenter;

	public NLPIRTokenizerA() {
		super();
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
		WordSegmenter = new WordSegmenter();
//		System.out.println("NLPIRTokenizerA.input");
	}

	public NLPIRTokenizerA(AttributeFactory factory) {
		super(factory);
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
		WordSegmenter = new WordSegmenter();
//		System.out.println("NLPIRTokenizerA.factory.input");
	}

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		// 清除所有的词元属性
//		System.out.println("NLPIRTokenizerA.incrementToken");
		clearAttributes();
		result_t next = WordSegmenter.next();// 获取下一个词元对象
		String word = "";// 词元内容
		if (next != null) {
			word = new String(WordSegmenter.paragraph.getBytes(), next.start,
					next.length);
//			System.out.println("WORD=" + word + "\tStart=" + next.start
//			+ "\tLength=" + next.length);
			// 设置词元文本
			termAtt.copyBuffer(word.toCharArray(), 0, word.length());
			// 设置词元长度
			termAtt.setLength(word.length());
			// 记录当前分词的开始位置
			beginPosition = endPosition;
			// 记录当前分词的结束位置
			endPosition = beginPosition + word.length();
			// 设置词元位移
			offsetAtt.setOffset(beginPosition, endPosition);

			// 记录词元分类
			String type = new String(next.sPOS).trim();
			typeAtt.setType(type);
			// 返会true告知还有下个词元
			return true;
		}
		return false;
	}

	/*
	 * 本次测试用
	 */
	public void reset(Reader input) throws IOException {
		super.reset();
		this.beginPosition = 0;
		this.endPosition = 0;
		WordSegmenter.reset(input);
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		this.beginPosition = 0;
		this.endPosition = 0;
//		System.out.println("2. NLPIRTokenizer.reset");
		WordSegmenter.reset(input);
	}

	@Override
	public void end() throws IOException {
		// set final offset
		super.end();
//		System.out.println("4. NLPIRTokenizer.end");
//		System.out.println("------------------------------");
		int finalOffset = correctOffset(this.endPosition);
		offsetAtt.setOffset(finalOffset, finalOffset);
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		super.close();
	}
}
