package com.qunar.config;

public class ConfigNlpir {
	public String NLPIR_LIBRARY_PATH = null;
	public String NLPIR_DATA_PATH = null;
	public String NLPIR_USRDIC_PATH = null;

	public ConfigNlpir() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith("Windows")) {
			NLPIR_LIBRARY_PATH = "D:/workspace/NLPIRAnalyzer4Solr5/lib/win64/NLPIR";
			NLPIR_DATA_PATH = "D:/workspace/NLPIRAnalyzer4Solr5";
			NLPIR_USRDIC_PATH = "D:/workspace/NLPIRAnalyzer4Solr5/Data/userdict.txt";
		} else {
			NLPIR_LIBRARY_PATH = "/alidata1/solr/lib/linux64/libNLPIR.so";
			NLPIR_DATA_PATH = "/alidata1/solr";
			NLPIR_USRDIC_PATH = "/alidata1/solr/Data/userdict.txt";
		}
	}

}
