package com.java.cn;

import com.java.cn.bean.Data;

public interface FetcherCallback {
	
	void onData(Data data) throws Exception;

	void onError(Throwable cause);
}
