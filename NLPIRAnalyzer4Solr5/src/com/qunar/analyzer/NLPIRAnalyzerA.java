package com.qunar.analyzer;

import org.apache.lucene.analysis.Analyzer;

import com.qunar.tokenizer.NLPIRTokenizerA;

public class NLPIRAnalyzerA extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// TODO Auto-generated method stub
		return new TokenStreamComponents(new NLPIRTokenizerA());
	}
	
}
