package com.qunar.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qunar.config.ConfigNlpir;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

/**
 * 使用JNA调用NLPIR的DLL动态链库工具类
 * 
 * @author Ricky
 * @since 2014/07/01
 */
public class NLPIRUtil {

	public NLPIRUtil() {
		// TODO Auto-generated constructor stub
	}

	// 使用Logback进行调试
	private static final Logger logger = LoggerFactory
			.getLogger(NLPIRUtil.class);
	private static final ConfigNlpir cfg = new ConfigNlpir();

	public interface NLPIRInterface extends Library {
		NLPIRInterface INSTANCE = (NLPIRInterface) Native.loadLibrary(
				cfg.NLPIR_LIBRARY_PATH, NLPIRInterface.class);

		/**
		 * 获取系统最后出错信息
		 * 
		 * @since 2014/02/27
		 * @return
		 */
		public String NLPIR_GetLastErrorMsg();

		/**
		 * 文本分析
		 * 
		 * @since 2003/12/22
		 * @param sParagraph
		 *            文本信息
		 * @param bPOStagged
		 *            　词性标注　0　不标注　1标注
		 * @return　String 返回文本分析过后的字符串
		 */
		public String NLPIR_ParagraphProcess(String sParagraph, int bPOStagged);

		/**
		 * 初始化分词工具
		 * 
		 * @since 2013/06/08
		 * @param sDataPath
		 *            　分词工具语料库文件夹Data父目录
		 * @param encode
		 *            编码,如下 GBK编码 0 UTF-8 1 BIG5 2 GBK和繁体 3
		 * @param sLicenceCode
		 *            授权代码　　默认为0
		 * @return boolean 初始化成功返回true，初始化失败返回false
		 */
		public boolean NLPIR_Init(String sDataPath, int encode,
				String sLicenceCode);

		/**
		 * 从字符串中提取关键词
		 * 
		 * @since 2012/11/12
		 * @param sLine
		 *            文本信息
		 * @param nMaxKeyLimit
		 *            最大关键词长度
		 * @param bWeightOut
		 *            是否输入权重信息 0 不输出权重信息 1输出权重信息
		 * @return String 返回结果字符串
		 */
		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);

		/**
		 * 退出分词工具，释放缓存
		 * 
		 * @since 2002-08-06
		 * @return boolean 退出成功返回true，退出失败返回false
		 */
		public boolean NLPIR_Exit();

		/**
		 * 高级文件分析(可以标注分词位置)
		 * 
		 * @since 2006-10-26
		 * @param sParagraph
		 *            文本信息
		 * @param pResultCount
		 *            指向某一分词的游标
		 * @param useDict
		 *            是否使用用户自定义语料
		 * @return
		 */
		public result_t NLPIR_ParagraphProcessA(String sParagraph,
				IntByReference pResultCount, boolean useDict);

		/**
		 * 文本分析后返回的词汇总数
		 * 
		 * @param sParagraph
		 *            　文本信息
		 * @return　int 词汇总数
		 */
		public int NLPIR_GetParagraphProcessAWordCount(String sParagraph);

		/**
		 * 文件分析
		 * 
		 * @param sSourceFilename
		 *            　需要分析的文件路径
		 * @param sResultFilename
		 *            　文件分析后存放分析结果的文件路径
		 * @param bPOStagged
		 *            词性标注　0不标注　1标注
		 * @return　boolean 分析成功返回　true 分析失败返回false
		 */
		public double NLPIR_FileProcess(String sSourceFilename,
				String sResultFilename, int bPOStagged);

		/**
		 * 导入外部用户词典文件　文件中词典格式为　每个词汇占一行，每行为词汇和词性中间用空格隔开　例：好觉 n
		 * 
		 * @param sFilename
		 *            　外部用户词典路径
		 * @return　返回导入成功的词汇总数
		 */
		public int NLPIR_ImportUserDict(String sFilename);

		/**
		 * 动态加载词汇
		 * 
		 * @param sWord
		 *            　词汇，格式为词汇和词性中间用空格隔开
		 * @return　int 返回1　加载成功　返回0　加载失败
		 */
		public int NLPIR_AddUserWord(String sWord);

		/**
		 * 保存用户自定义导入字典
		 * 
		 * @return　int 保存成功　1 　　保存失败　2
		 */
		public int NLPIR_SaveTheUsrDic();

		/**
		 * 删除核心语料库中的词汇
		 * 
		 * @param sWord
		 *            　词汇
		 * @return　int　若库中没有该词汇返回-1
		 */
		public int NLPIR_DelUsrWord(String sWord);

		/**
		 * 获取分词概率
		 * 
		 * @param sWord
		 *            词语
		 * @return
		 */
		public double NLPIR_GetUniProb(String sWord);

		/**
		 * 判断词汇是否在核心库中
		 * 
		 * @param sWord
		 *            　词汇
		 * @return boolean 若存在返回　true　否则返回false
		 */
		public boolean NLPIR_IsWord(String sWord);

		/**
		 * 从文本文件中提取关键词
		 * 
		 * @param sFilename
		 *            文本文件路径
		 * @param nMaxKeyLimit
		 *            最大关键词长度
		 * @param bWeightOut
		 *            是否输入权重信息
		 * @return 返回结果字符串
		 */
		public String NLPIR_GetFileKeyWords(String sFilename, int nMaxKeyLimit,
				boolean bWeightOut);

		/**
		 * 从字符串中发现新词
		 * 
		 * @param sLine
		 *            文本信息
		 * @param nMaxKeyLimit
		 *            最大关键词长度
		 * @param bWeightOut
		 *            是否输入权重信息
		 * @return 返回结果字符串
		 */
		public String NLPIR_GetNewWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);

		/**
		 * 从文本文件中发现新词
		 * 
		 * @param sFilename
		 *            文本文件路径
		 * @param nMaxKeyLimit
		 *            最大关键词长度
		 * @param bWeightOut
		 *            是否输入权重信息
		 * @return 返回结果字符串
		 */
		public String NLPIR_GetFileNewWords(String sFilename, int nMaxKeyLimit,
				boolean bWeightOut);

		/**
		 * 把文本信息转成唯一的数字标识
		 * 
		 * @param sLine
		 *            　文本信息
		 * @return long 数据标识
		 */
		public long NLPIR_FingerPrint(String sLine);

		/**
		 * 设置词性标注映射
		 * 
		 * @param nPOSmap
		 *            　词性标注映射类型 计算所一级标注集 1 计算所二级标注集 0 北大二级标注集 2 北大一级标注集 3
		 * @return int 设置成功返回1设置失败返回0
		 */
		public int NLPIR_SetPOSmap(int nPOSmap);

		/**
		 * 分词结果实体
		 * 
		 * @author Ricky
		 */
		public class result_t extends Structure {
			public int start;// 词元在输入句子中的开始位置
			public int length;// 词元长度
			public byte[] sPOS = new byte[40];// 词元词性
			public int iPOS;// 词性标注的编号
			public int word_ID;// 该词的内部ID号，如果是未登录词，设成0或者-1
			public int word_type; // 区分用户词典;1，是用户词典中的词；0，非用户词典中的词
			public int weight;// word weight,read weight

			public result_t() {
			}

			protected List<?> getFieldOrder() {
				return Arrays.asList("start", "length", "sPOS", "iPOS",
						"word_ID", "word_type", "weight");
			}

			public result_t(int start, int length, byte sPOS[], int iPOS,
					int word_ID, int word_type, int weight) {
				super();
				this.start = start;
				this.length = length;
				if ((sPOS.length != this.sPOS.length))
					throw new IllegalArgumentException("Wrong array size !");
				this.sPOS = sPOS;
				this.iPOS = iPOS;
				this.word_ID = word_ID;
				this.word_type = word_type;
				this.weight = weight;
			}

			public static class ByReference extends result_t implements
					Structure.ByReference {
			};

			public static class ByValue extends result_t implements
					Structure.ByValue {
			};
		}
	}

	static {
		NLPIR_Init();
	}

	public static boolean NLPIR_Init() {
		// 初始化中科院分词器
		String encode = "";
		// if(XMLUtil.DEFAULT_CODING == 0){
		// encode = "GBK";
		// }else if(XMLUtil.DEFAULT_CODING == 1){
		// encode = "UTF-8";
		// }else if(XMLUtil.DEFAULT_CODING == 2){
		// encode = "BIG5";
		// }else{
		// encode = "GBK编码-包含繁体字";
		// }
		logger.debug("初始化中科院分词，分词工具语料库Data文件夹的父目录路径:{}", cfg.NLPIR_DATA_PATH);
		 logger.debug("初始化中科院分词，分词工具默认编码:{}   {}", "UTF-8", encode);
		// XMLUtil.DEFAULT_CODING,encode);
		boolean initStatus = NLPIRInterface.INSTANCE.NLPIR_Init(
				cfg.NLPIR_DATA_PATH, 1, "0");
		if (initStatus == true) {
			long start_time = System.currentTimeMillis();
//			NLPIRInterface.INSTANCE.NLPIR_ImportUserDict(cfg.NLPIR_USRDIC_PATH);
			System.out.println("Load UserDict Ustime=" + (System.currentTimeMillis() - start_time) / 1000 + " s");
			logger.debug("NLPIR加载用户词典=" + (System.currentTimeMillis() - start_time) / 1000 + " s");
			logger.debug("NLPIR中科院分词器========初始化成功");
		} else {
			logger.error("NLPIR中科院分词器========初始化失败");
		}
		System.out.println("NLPIR Init...");
		return initStatus;
	}

	public static boolean NLPIR_Exit() {
		// 初始化中科院分词器
		boolean exitStatus = NLPIRInterface.INSTANCE.NLPIR_Exit();
		if (exitStatus == true) {
			logger.debug("NLPIR中科院分词器========释放资源成功");
		} else {
			logger.error("NLPIR中科院分词器========初始化失败");
		}
		return exitStatus;
	}

	/**
	 * 文本分析
	 * 
	 * @param paragraph
	 *            文本信息
	 * @param bPOStagged
	 *            　词性标注　0　不标注　1标注
	 * @return　String 返回文本分析过后的字符串
	 */
	public static String NLPIR_ParagraphProcess(String paragraph, int bPOStagged) {
		String output = NLPIRInterface.INSTANCE.NLPIR_ParagraphProcess(
				paragraph, bPOStagged);
		return output;
	}

	/**
	 * 文本处理
	 * 
	 * @param paragraph
	 *            处理内容
	 * @return　result_t[]
	 */
	public static NLPIRInterface.result_t[] NLPIR_ParagraphProcessA(
			String paragraph) throws Exception{

		IntByReference pResultCount = new IntByReference();
		NLPIRInterface.result_t results = NLPIRInterface.INSTANCE
				.NLPIR_ParagraphProcessA(paragraph, pResultCount, true);
		
		if(pResultCount.getValue() < 1){
			return null;
		}
		NLPIRInterface.result_t[] resultArray = (NLPIRInterface.result_t[]) results
				.toArray(pResultCount.getValue());
		
		return resultArray;
	}

	/**
	 * 文本分析后返回的词汇总数
	 * 
	 * @param sParagraph
	 *            　文本信息
	 * @return　int 词汇总数
	 */
	public static int NLPIR_GetParagraphProcessAWordCount(String sParagraph) {
		return NLPIRInterface.INSTANCE
				.NLPIR_GetParagraphProcessAWordCount(sParagraph);

	}

	/**
	 * 文件分析
	 * 
	 * @param sSourceFilename
	 *            　需要分析的文件路径
	 * @param sResultFilename
	 *            　文件分析后存放分析结果的文件路径
	 * @param bPOStagged
	 *            词性标注　0不标注　1标注
	 * @return　boolean 分析成功返回　true 分析失败返回false
	 */
	public static double NLPIR_FileProcess(String sSourceFilename,
			String sResultFilename, int bPOStagged) {
		return NLPIRInterface.INSTANCE.NLPIR_FileProcess(sSourceFilename,
				sResultFilename, bPOStagged);
	}

	/**
	 * 导入外部用户词典文件　文件中词典格式为　每个词汇占一行，每行为词汇和词性中间用空格隔开　例：好觉 n
	 * 
	 * @param sFilename
	 *            　外部用户词典路径
	 * @return　返回导入成功的词汇总数
	 */
	public static int NLPIR_ImportUserDict(String sFilename) {
		return NLPIRInterface.INSTANCE.NLPIR_ImportUserDict(sFilename);

	}

	/**
	 * 动态加载词汇
	 * 
	 * @param sWord
	 *            　词汇，格式为词汇和词性中间用空格隔开
	 * @return　int 返回1　加载成功　返回0　加载失败
	 */
	public static int NLPIR_AddUserWord(String sWord) {
		return NLPIRInterface.INSTANCE.NLPIR_AddUserWord(sWord);

	}

	/**
	 * 保存用户自定义导入字典
	 * 
	 * @return　int 保存成功　1 　　保存失败　2
	 */
	public static int NLPIR_SaveTheUsrDic() {
		return NLPIRInterface.INSTANCE.NLPIR_SaveTheUsrDic();
	}

	/**
	 * 删除核心语料库中的词汇
	 * 
	 * @param sWord
	 *            　词汇
	 * @return　int　若库中没有该词汇返回-1
	 */
	public static int NLPIR_DelUsrWord(String sWord) {
		return NLPIRInterface.INSTANCE.NLPIR_DelUsrWord(sWord);

	}

	/**
	 * 获取分词概率
	 * 
	 * @param sWord
	 *            词语
	 * @return
	 */
	public static double NLPIR_GetUniProb(String sWord) {
		return NLPIRInterface.INSTANCE.NLPIR_GetUniProb(sWord);

	}

	/**
	 * 判断词汇是否在核心库中
	 * 
	 * @param sWord
	 *            　词汇
	 * @return boolean 若存在返回　true　否则返回false
	 */
	public static boolean NLPIR_IsWord(String sWord) {
		return NLPIRInterface.INSTANCE.NLPIR_IsWord(sWord);
	}

	/**
	 * 从字符串中提取关键词
	 * 
	 * @param sLine
	 *            文本信息
	 * @param nMaxKeyLimit
	 *            最大关键词长度
	 * @param bWeightOut
	 *            是否输入权重信息
	 * @return String 返回结果字符串
	 */
	public static String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
			boolean bWeightOut) {
		return NLPIRInterface.INSTANCE.NLPIR_GetKeyWords(sLine, nMaxKeyLimit,
				bWeightOut);

	}

	/**
	 * 从文本文件中提取关键词
	 * 
	 * @param sFilename
	 *            文本文件路径
	 * @param nMaxKeyLimit
	 *            最大关键词个数
	 * @param bWeightOut
	 *            是否输入权重信息
	 * @return 返回结果字符串
	 */
	public static String NLPIR_GetFileKeyWords(String sFilename,
			int nMaxKeyLimit, boolean bWeightOut) {
		return NLPIRInterface.INSTANCE.NLPIR_GetFileKeyWords(sFilename,
				nMaxKeyLimit, bWeightOut);

	}

	/**
	 * 从字符串中发现新词
	 * 
	 * @param sLine
	 *            文本信息
	 * @param nMaxKeyLimit
	 *            最大关键词个数
	 * @param bWeightOut
	 *            是否输入权重信息
	 * @return 返回结果字符串
	 */
	public static String NLPIR_GetNewWords(String sLine, int nMaxKeyLimit,
			boolean bWeightOut) {
		return NLPIRInterface.INSTANCE.NLPIR_GetNewWords(sLine, nMaxKeyLimit,
				bWeightOut);

	}

	/**
	 * 从文本文件中发现新词
	 * 
	 * @param sFilename
	 *            文本文件路径
	 * @param nMaxKeyLimit
	 *            最大关键词个数
	 * @param bWeightOut
	 *            是否输入权重信息
	 * @return 返回结果字符串
	 */
	public static String NLPIR_GetFileNewWords(String sFilename,
			int nMaxKeyLimit, boolean bWeightOut) {
		return NLPIRInterface.INSTANCE.NLPIR_GetFileNewWords(sFilename,
				nMaxKeyLimit, bWeightOut);

	}

	/**
	 * 设置词性标注映射
	 * 
	 * @param nPOSmap
	 *            　词性标注映射类型 计算所一级标注集 1 计算所二级标注集 0 北大二级标注集 2 北大一级标注集 3
	 * @return int 设置成功返回1设置失败返回0
	 */
	public static int NLPIR_SetPOSmap(int nPOSmap) {
		int posMap = NLPIRInterface.INSTANCE.NLPIR_SetPOSmap(nPOSmap);
		return posMap;
	}

	/**
	 * 获取系统最后出错信息
	 * 
	 * @since 2014/02/27
	 * @return
	 */
	public static String NLPIR_GetLastErrorMsg() {
		return NLPIRInterface.INSTANCE.NLPIR_GetLastErrorMsg();
	}

	/**
	 * 把文本信息转成唯一的数字标识
	 * 
	 * @param sLine
	 *            　文本信息
	 * @return long 数据标识
	 */
	public static long NLPIR_FingerPrint(String sLine) {
		return NLPIRInterface.INSTANCE.NLPIR_FingerPrint(sLine);
	}

}
