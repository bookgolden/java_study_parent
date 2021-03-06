package com.lucene.index;

import org.apache.lucene.analysis.*;
import org.apache.lucene.util.Version;

import java.io.Reader;
import java.util.Set;

public class MyStopAnalyzer extends Analyzer {
	@SuppressWarnings("rawtypes")
	private Set stops;
	@SuppressWarnings("unchecked")
	public MyStopAnalyzer(String[]sws) {
		//会自动将字符串数组转换为Set
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		//将原有的停用词加入到现在的停用词
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}
	
	public MyStopAnalyzer() {
		//获取原有的停用词
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		//为这个分词器设定过滤链和Tokenizer
		return new StopFilter(Version.LUCENE_35,
			   new LowerCaseFilter(Version.LUCENE_35,
			   new LetterTokenizer(Version.LUCENE_35,reader)), stops);
	}

}