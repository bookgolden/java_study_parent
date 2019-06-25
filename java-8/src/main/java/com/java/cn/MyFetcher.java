package com.java.cn;

import com.java.cn.bean.Data;
import com.java.cn.interfaces.Fetcher;

public class MyFetcher implements Fetcher {

	final Data data;

	public MyFetcher(Data data) {
		this.data = data;
	}

	@Override
	public void fetchData(FetcherCallback callback) {
		try {
			callback.onData(data);
		} catch (Exception e) {
			callback.onError(e);
		}
	}
}
