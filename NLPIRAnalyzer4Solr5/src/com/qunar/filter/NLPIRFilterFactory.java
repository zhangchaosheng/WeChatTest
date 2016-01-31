package com.qunar.filter;

import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

public class NLPIRFilterFactory extends TokenizerFactory {

	public NLPIRFilterFactory(Map<String, String> args) {
		super(args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Tokenizer create(AttributeFactory arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
