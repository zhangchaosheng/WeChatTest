package com.qunar.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.qunar.analyzer.NLPIRAnalyzer;

public class TestAnalyzer {

	public TestAnalyzer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Analyzer analyzer = new NLPIRAnalyzer();
		String text = "不知道要是三五十万我被拴着我也乐意";
		Reader reader = new StringReader(text);
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream(null, reader);
			ts.reset();
			while (ts.incrementToken()) {
				CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
				System.out.println(ta.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
