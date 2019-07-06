package com.java.design.proxy.impl;

import com.java.design.proxy.StaticProxyInterface;

public class WeddingCompany implements StaticProxyInterface {

	private StaticProxyInterface marryInterface;

	public WeddingCompany(StaticProxyInterface marryInterface) {
		this.marryInterface = marryInterface;
	}

	@Override
	public void marry() {
		System.out.println("婚庆公司...做结婚前工作...节目彩排...礼物购买...可以开始结婚了");
		marryInterface.marry();
		System.out.println("结婚完毕");
	}
}