package com.qunar.test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.TokenizerFactory;

import com.qunar.tokenizer.NLPIRTokenizerA;
import com.qunar.tokenizer.NLPIRTokenizerFactory;

public class TestMainA {

	public static void main(String[] args) {
		String[] words = { "去哪儿网", "携程旅游", "小米之家" };
		try {
			Map<String, String> map = new HashMap<String, String>();
			TokenizerFactory tkf = new NLPIRTokenizerFactory(map);
			NLPIRTokenizerA tk = (NLPIRTokenizerA) tkf.create();
			
			for (int i = 0; i < 3; i++) {
				StringReader inputText = new StringReader(words[i]);
				tk.reset(inputText);
				StringBuffer sb = new StringBuffer();
				CharTermAttribute charTermAttribute = tk
						.getAttribute(CharTermAttribute.class);
				while (tk.incrementToken()) {
					String term = charTermAttribute.toString();
					sb.append(term + "/");
				}
				System.out.println(sb.toString());
				System.out.println("--------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
