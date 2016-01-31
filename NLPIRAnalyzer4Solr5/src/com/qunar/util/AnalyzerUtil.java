package com.qunar.util;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class AnalyzerUtil {
	public String getTerms(TokenStream tokenStream){
		StringBuffer sb = new StringBuffer();
		try {
//			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
//			    int startOffset = offsetAttribute.startOffset();
//			    int endOffset = offsetAttribute.endOffset();
			    String term = charTermAttribute.toString();
//			    System.out.println(startOffset + ":" + endOffset + "\t" + term);
			    sb.append(term + " ");
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sb.toString().trim();
	}
}
