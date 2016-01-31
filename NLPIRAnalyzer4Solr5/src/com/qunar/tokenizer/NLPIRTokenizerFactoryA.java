package com.qunar.tokenizer;

import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

/**
 * 
 * @author chaosheng.zhang 功能：封装中科院分词NLPIR并以插件形式集成到solr5中，以供搜索和索引使用
 *         说明：Solr分词插件工厂在进行搜索和索引的时候仅会被调用一次
 *         （在analysis页面进行分词测试的时候，每次都会调用create方法），且调用的是内部create方法
 *         ，此方法会生成一个分词器（Tokenizer）；之后再进行分词的时候调用的则是Tokenizer的reset()方法，且无需传入任何参数，
 *         Tokenizer类自动将需要进行分词的Reader置换到内置变量input中
 *         ；因此所有的自定义操作，我们只需覆盖原来的reset方法即可；
 *         Tokenizer的reset方法会默认调用increamentToken方法，并将分词结果一次迭代出来，然后再调用其end()方法，最后
 *         调用其close()方法； 调用方法顺序如下： NLPIRTokenizerFactory.create() ->
 *         NLPIRTokenizer.reset() -> NLPIRTokenizer.increamentToken() ->
 *         NLPIRTokenizer.end() -> NLPIRTokenizer.close()
 * 
 */
public class NLPIRTokenizerFactoryA extends TokenizerFactory {
	public NLPIRTokenizerFactoryA(Map<String, String> args) {
		super(args);
	}

	@Override
	public Tokenizer create(AttributeFactory arg0) {
		// TODO Auto-generated method stub
		System.out.println("1. NLPIRTokenizerFactory.create");
		return new NLPIRTokenizerA();
	}
	
}
