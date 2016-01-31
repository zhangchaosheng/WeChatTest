package com.qunar.analyzer;

import org.apache.lucene.analysis.Analyzer;

import com.qunar.tokenizer.NLPIRTokenizer;

public class NLPIRAnalyzer extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// TODO Auto-generated method stub
		return new TokenStreamComponents(new NLPIRTokenizer());
	}
	
}
