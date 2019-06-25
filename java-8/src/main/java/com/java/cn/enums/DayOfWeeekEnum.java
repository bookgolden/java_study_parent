package com.java.cn.enums;

import lombok.Getter;

@Getter
public enum DayOfWeeekEnum {
	MONDAY("周一", 1), TUESDAY("周二", 2), WEDNESDAY("周三", 3), THURSDAY("周四", 4), FRIDAY("周五", 5), SATURDAY("周六",
			6), SUNDAY("周日", 7);

	private String alias;
	private int value;

	DayOfWeeekEnum(String alias, int value) {
		this.alias = alias;
		this.value = value;
	}

	public static DayOfWeeekEnum getEnumByValue(int value) {
		for (DayOfWeeekEnum vs : DayOfWeeekEnum.values()) {
			if (vs.value == value) {
				return vs;
			}
		}
		return null;
	}
}